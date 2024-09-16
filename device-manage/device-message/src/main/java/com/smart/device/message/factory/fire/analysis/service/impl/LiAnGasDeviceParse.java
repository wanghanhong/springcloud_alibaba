package com.smart.device.message.factory.fire.analysis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.*;
import com.smart.device.message.common.DeviceMessageConstants;
import com.smart.device.message.data.service.*;
import com.smart.device.message.factory.fire.analysis.service.FireDeviceParse;
import com.smart.device.message.feign.DeviceBaseFeignClient;
import com.smart.device.message.feign.DeviceInstallFeignClient;
import com.smart.device.message.parse.fire.ParseContext;
import com.smart.device.message.parse.fire.gas.lian.entity.GasLiAnEntity;
import com.smart.device.message.parse.fire.gas.lian.strategy.DeviceGasStandard;
import com.smart.device.message.parse.fire.gas.lian.strategy.LiAnGasParseStrategy;
import com.smart.device.message.parse.fire.gas.lian.strategy.LiAnGasStandard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@Component
public class LiAnGasDeviceParse implements FireDeviceParse {

    @Resource
    private LiAnGasParseStrategy liAnGasParseStrategy;
    @Resource
    private TAlarmSmokeService tAlarmSmokeService;
    @Resource
    private THeartSmokeService tHeartSmokeService;
    @Resource
    private DeviceGasStandard deviceGasStandard;
    @Resource
    private LiAnGasStandard liAnGasStandard;
    @Resource
    private DeviceBaseFeignClient deviceBaseFeignClient;
    @Resource
    private TStrategyAlarmService tStrategyAlarmService;
    @Resource
    private DeviceInstallFeignClient deviceInstallFeignClient;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     *
     * {"deviceCode":"869162052818257","productId":"15017088","IMEI":"869162052818257","protocol":"LWM2M", "payload":{"valve_state":2,"temperature":0,"medium":0,"gas_value":0,"gas_sensor_state":1,"fan_state":2,"battery_voltage":1.1,"battery_value":100}}
     * @param data
     * @return
     */
    @Override
    public String parse(String data) {
        ParseContext<GasLiAnEntity> context = new ParseContext<>(liAnGasParseStrategy);
        GasLiAnEntity entity = context.parseEntity(data);

        TAlarmSmoke alarm = liAnGasStandard.changeToAlarm(entity);
        THeartSmoke heart = liAnGasStandard.changeToHear(entity);
        Long imei = alarm.getImei();
        if(imei != null){
            String d_imei_alamr_ = DeviceMessageConstants.d_imei_alamr_+alarm.getImei();
            String exist = (String)redisTemplate.opsForValue().get(d_imei_alamr_);
            if (exist  !=null && "1".equals(exist)){
            }else{
                DeviceBaseVo baseVo = deviceBaseFeignClient.selectDeviceSmokeByImei(imei);
                log.info("力安 - 气感--基础数据-" + JSONObject.toJSONString(baseVo));
                if(baseVo != null){
                    TStrategyAlarm strategyAlarm = tStrategyAlarmService.queryStrategyByTypeAndCode(baseVo.getDeviceType(),alarm.getEventCode());
                    log.info("力安-报警-" + JSONObject.toJSONString(strategyAlarm));
                    if(strategyAlarm != null && strategyAlarm.getStrategyType() >= 0){
                        // 用deviceBaseStandard中的方法把基础信息复制给报警的表
                        deviceGasStandard.changeAlarmAttri(alarm,baseVo);
                        tAlarmSmokeService.smokeAdd(alarm);
                        // 报警才有策略，心跳直接跳过。
                        Long id = baseVo.getId();
                        DeviceCompanyVo companyVo = deviceInstallFeignClient.smokePerSonByDeviceId(id);
                        // 所有的报警均会走这个方法进行报警消息发送
                        String eventCode = alarm.getEventCode();
                        Integer deviceType = baseVo.getDeviceType();
                        tStrategyAlarmService.getParamAndMQSend(companyVo,deviceType,eventCode);
                    }
                    // 用deviceBaseStandard中的方法把基础信息复制给心跳的表
                    deviceGasStandard.changeHeartAttri(heart , baseVo);
                    tHeartSmokeService.smokeAdd(heart);
                }
                tHeartSmokeService.updateSmokeStatusAll(alarm.getImei(),baseVo.getId());
                redisTemplate.opsForValue().set(d_imei_alamr_,"1",90, TimeUnit.SECONDS);
            }

        }
        return "力安气感设备解析完成";
    }
}
