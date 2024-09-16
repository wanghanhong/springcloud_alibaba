package com.smart.device.message.factory.fire.analysis.service.impl;

import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TAlarmElectric;
import com.smart.device.common.message.entity.THeartElectric;
import com.smart.device.common.message.entity.TStrategyAlarm;
import com.smart.device.message.common.DeviceMessageConstants;
import com.smart.device.message.feign.DeviceBaseFeignClient;
import com.smart.device.message.feign.DeviceInstallFeignClient;
import com.smart.device.message.data.service.TAlarmElectricService;
import com.smart.device.message.data.service.THeartElectricService;
import com.smart.device.message.data.service.TStrategyAlarmService;
import com.smart.device.message.factory.fire.analysis.service.FireDeviceParse;
import com.smart.device.message.parse.fire.ParseContext;
import com.smart.device.message.parse.fire.electric.wanlin.entity.ElectricWanlinEntity;
import com.smart.device.message.parse.fire.electric.wanlin.strategy.ElectricParseStrategy;
import com.smart.device.message.parse.fire.electric.wanlin.strategy.ElectricStandar;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 *  智慧用电设备
 *
 * @author 三多
 * @Time 2020/5/9
 */
@Service
public class ElectricDeviceParse implements FireDeviceParse {

    @Resource
    private ElectricParseStrategy electricParseStrategy;
    @Resource
    private ElectricStandar electricStandar;
    @Resource
    private TAlarmElectricService tAlarmElectricService;
    @Resource
    private THeartElectricService tHeartElectricService;
    @Resource
    private TStrategyAlarmService tStrategyAlarmService;
    @Resource
    private DeviceBaseFeignClient deviceBaseFeignClient;
    @Resource
    private DeviceInstallFeignClient deviceInstallFeignClient;
    @Resource
    private RedisTemplate redisTemplate;



    /**
     * 数据解析方法
     * 1、按照码表解析数据
     * 2、将数据存储到数据库
     * 3、根据解析的数据
     * 4、定义事件
     *
     * @param data
     * @return
     */
    @Override
    public String parse(String data) {
        ParseContext<ElectricWanlinEntity> context = new ParseContext<>(electricParseStrategy);
        ElectricWanlinEntity entity = context.parseEntity(data);
        TAlarmElectric alarm = electricStandar.changeToAlarm(entity);
        THeartElectric heart = electricStandar.changeToHeart(entity);
        // 电力解析是时候直接分两种情况
        if(alarm.getImei() != null){
            String d_imei_alamr_ = DeviceMessageConstants.d_imei_alamr_+alarm.getImei();
            String exist = (String)redisTemplate.opsForValue().get(d_imei_alamr_);
            if (exist  !=null && "1".equals(exist)){
            }else{
                DeviceBaseVo baseVo = deviceBaseFeignClient.selectDeviceElectricByImei(alarm.getImei());
                if(baseVo != null){
                    TStrategyAlarm strategyAlarm = tStrategyAlarmService.queryStrategyByTypeAndCode(baseVo.getDeviceType(),alarm.getEventCode());
                    if(strategyAlarm != null && strategyAlarm.getStrategyType() >= 0){
                        electricStandar.changeAlarmAttri(alarm,baseVo);
                        tAlarmElectricService.electricAdd(alarm);
                    }
                }
                // 报警才有策略，心跳直接跳过。
                DeviceCompanyVo companyVo = deviceInstallFeignClient.electricPerSonByDeviceId(baseVo.getId());
                tStrategyAlarmService.getParamAndMQSend(companyVo,baseVo.getDeviceType(),alarm.getEventCode());
                redisTemplate.opsForValue().set(d_imei_alamr_,"1",90, TimeUnit.SECONDS);
            }

        }
        if(heart.getImei() != null){
            DeviceBaseVo baseVo = deviceBaseFeignClient.selectDeviceElectricByImei(alarm.getImei());
            if(baseVo != null){
                electricStandar.changeHeartAttri(heart,baseVo);
                tHeartElectricService.electricAdd(heart);
            }
        }
        return "智慧用电解析完成";
    }


}
