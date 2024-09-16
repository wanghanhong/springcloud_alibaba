package com.smart.device.message.factory.fire.analysis.service.impl;

import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TAlarmWaterpress;
import com.smart.device.common.message.entity.THeartWaterpress;
import com.smart.device.common.message.entity.TStrategyAlarm;
import com.smart.device.message.common.DeviceMessageConstants;
import com.smart.device.message.feign.DeviceBaseFeignClient;
import com.smart.device.message.feign.DeviceInstallFeignClient;
import com.smart.device.message.data.service.TAlarmWaterpressService;
import com.smart.device.message.data.service.THeartWaterpressService;
import com.smart.device.message.data.service.TStrategyAlarmService;
import com.smart.device.message.factory.fire.analysis.service.FireDeviceParse;
import com.smart.device.message.parse.fire.ParseContext;
import com.smart.device.message.parse.fire.waterpress.pressure.wanlin.entity.PressureEntity;
import com.smart.device.message.parse.fire.waterpress.pressure.wanlin.strategy.PressureParseStrategy;
import com.smart.device.message.parse.fire.waterpress.pressure.wanlin.strategy.PressureStandar;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 气感设备解析类（每类设备对应的具体解析方法）
 *
 * @author 三多
 * @Time 2020/5/9
 */
@Service
public class WLWaterpressDeviceParse implements FireDeviceParse {

    @Resource
    private PressureParseStrategy pressureParseStrategy;
    @Resource
    private PressureStandar pressureStandar;
    @Resource
    private TAlarmWaterpressService tAlarmWaterpressService;
    @Resource
    private THeartWaterpressService tHeartWaterpressService;
    @Resource
    private DeviceBaseFeignClient deviceBaseFeignClient;
    @Resource
    private TStrategyAlarmService tStrategyAlarmService;
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
        ParseContext<PressureEntity> context = new ParseContext<>(pressureParseStrategy);
        PressureEntity entity = context.parseEntity(data);

        TAlarmWaterpress alarm = pressureStandar.changeToAlarm(entity);
        THeartWaterpress heart = pressureStandar.changeToTHeart(entity);
        if(alarm.getImei() != null){
            String d_imei_alamr_ = DeviceMessageConstants.d_imei_alamr_+alarm.getImei();
            String exist = (String)redisTemplate.opsForValue().get(d_imei_alamr_);
            if (exist  !=null && "1".equals(exist)){
            }else{
                DeviceBaseVo baseVo = deviceBaseFeignClient.selectDeviceWaterpressByImei(alarm.getImei());
                if(baseVo != null){
                    TStrategyAlarm strategyAlarm = tStrategyAlarmService.queryStrategyByTypeAndCode(baseVo.getDeviceType(),alarm.getEventCode());
                    if(strategyAlarm != null && strategyAlarm.getStrategyType() >= 0){
                        pressureStandar.changeAlarmAttri(alarm,baseVo);
                        tAlarmWaterpressService.waterpressAdd(alarm);
                        // 报警才有策略，心跳直接跳过。
                        DeviceCompanyVo companyVo = deviceInstallFeignClient.waterpressPerSonByDeviceId(baseVo.getId());
                        tStrategyAlarmService.getParamAndMQSend(companyVo,baseVo.getDeviceType(),alarm.getEventCode());
                    }
                    pressureStandar.changeHeartAttri(heart,baseVo);
                    tHeartWaterpressService.waterpressAdd(heart);
                }
                redisTemplate.opsForValue().set(d_imei_alamr_,"1",90, TimeUnit.SECONDS);
            }
        }
        return "水压设备解析完成";
    }


}
