package com.smart.device.message.factory.fire.analysis.service.impl;

import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TAlarmTemp;
import com.smart.device.common.message.entity.THeartTemp;
import com.smart.device.common.message.entity.TStrategyAlarm;
import com.smart.device.message.common.DeviceMessageConstants;
import com.smart.device.message.feign.DeviceBaseFeignClient;
import com.smart.device.message.feign.DeviceInstallFeignClient;
import com.smart.device.message.data.service.TAlarmTempService;
import com.smart.device.message.data.service.THeartTempService;
import com.smart.device.message.data.service.TStrategyAlarmService;
import com.smart.device.message.factory.fire.analysis.service.FireDeviceParse;
import com.smart.device.message.parse.fire.ParseContext;
import com.smart.device.message.parse.fire.temp.wanlin.entity.TempEntity;
import com.smart.device.message.parse.fire.temp.wanlin.strategy.TempParseStrategy;
import com.smart.device.message.parse.fire.temp.wanlin.strategy.TempStandard;
import org.springframework.data.redis.core.RedisTemplate;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 温湿度计解析类（每类设备对应的具体解析方法）
 * @author jungle
 */
public class WanlinTempParse implements FireDeviceParse {

    @Resource
    private TempParseStrategy tempParseStrategy;
    @Resource
    private TempStandard tempStandar;
    @Resource
    private TAlarmTempService tAlarmTempService;
    @Resource
    private THeartTempService tHeartTempService;
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
        ParseContext<TempEntity> context = new ParseContext<>(tempParseStrategy);
        TempEntity entity = context.parseEntity(data);
        TAlarmTemp alarm = tempStandar.changeToAlarm(entity);
        THeartTemp heart = tempStandar.changeToHeart(entity);
        if(alarm.getImei() != null){
            String d_imei_alamr_ = DeviceMessageConstants.d_imei_alamr_+alarm.getImei();
            String exist = (String)redisTemplate.opsForValue().get(d_imei_alamr_);
            if (exist  !=null && "1".equals(exist)){
            }else{
                DeviceBaseVo baseVo = deviceBaseFeignClient.selectDeviceSmokeByImei(alarm.getImei());
                if(baseVo != null){
                    TStrategyAlarm strategyAlarm = tStrategyAlarmService.queryStrategyByTypeAndCode(baseVo.getDeviceType(),alarm.getEventCode());
                    if(strategyAlarm != null && strategyAlarm.getStrategyType() > 0){
                        tempStandar.changeAlarmAttri(alarm,baseVo);
                        tAlarmTempService.tempAdd(alarm);
                        // 报警才有策略，心跳直接跳过。
                        DeviceCompanyVo companyVo = deviceInstallFeignClient.electricPerSonByDeviceId(baseVo.getId());
                        tStrategyAlarmService.getParamAndMQSend(companyVo,baseVo.getDeviceType(),alarm.getEventCode());
                    }
                    tempStandar.changeHeartAttri(heart,baseVo);
                    tHeartTempService.tempAdd(heart);
                }
                redisTemplate.opsForValue().set(d_imei_alamr_,"1",90, TimeUnit.SECONDS);
            }

        }
        return "温湿度计解析完成";
    }
}
