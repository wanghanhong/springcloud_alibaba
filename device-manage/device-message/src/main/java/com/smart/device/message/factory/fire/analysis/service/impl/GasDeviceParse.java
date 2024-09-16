package com.smart.device.message.factory.fire.analysis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TAlarmSmoke;
import com.smart.device.common.message.entity.THeartSmoke;
import com.smart.device.common.message.entity.TStrategyAlarm;
import com.smart.device.message.common.DeviceMessageConstants;
import com.smart.device.message.feign.DeviceBaseFeignClient;
import com.smart.device.message.feign.DeviceInstallFeignClient;
import com.smart.device.message.data.service.TAlarmSmokeService;
import com.smart.device.message.data.service.THeartSmokeService;
import com.smart.device.message.data.service.TStrategyAlarmService;
import com.smart.device.message.factory.fire.analysis.service.FireDeviceParse;
import com.smart.device.message.parse.fire.DeviceBase.DeviceBaseStandard;
import com.smart.device.message.parse.fire.ParseContext;
import com.smart.device.message.parse.fire.gas.wanlin.entity.GasEntity;
import com.smart.device.message.parse.fire.gas.wanlin.strategy.WanlinGasParseStrategy;
import com.smart.device.message.parse.fire.gas.wanlin.strategy.WanlinGasStandard;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class GasDeviceParse implements FireDeviceParse {

    @Resource
    private WanlinGasParseStrategy wanlinGasParseStrategy;
    @Resource
    private WanlinGasStandard wanlinGasStandard;
    @Resource
    private TAlarmSmokeService tAlarmSmokeService;
    @Resource
    private THeartSmokeService tHeartSmokeService;
    @Resource
    private DeviceBaseStandard deviceBaseStandard;
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
        ParseContext<GasEntity> context = new ParseContext<>(wanlinGasParseStrategy);
        GasEntity entity = context.parseEntity(data);
        TAlarmSmoke alarm = wanlinGasStandard.changeToAlarm(entity);
        THeartSmoke heart = wanlinGasStandard.changeToHeart(entity);
        if(alarm.getImei() != null){
            String d_imei_alamr_ = DeviceMessageConstants.d_imei_alamr_+alarm.getImei();
            String exist = (String)redisTemplate.opsForValue().get(d_imei_alamr_);
            if (exist  !=null && "1".equals(exist)){
            }else{
                DeviceBaseVo baseVo = deviceBaseFeignClient.selectDeviceSmokeByImei(alarm.getImei());
                log.info("万林NB气感--基础数据-" + JSONObject.toJSONString(baseVo));
                if(baseVo != null){
                    TStrategyAlarm strategyAlarm = tStrategyAlarmService.queryStrategyByTypeAndCode(baseVo.getDeviceType(),alarm.getEventCode());
                    log.info("万林-报警-" + JSONObject.toJSONString(strategyAlarm));
                    if(strategyAlarm != null && strategyAlarm.getStrategyType() >= 0){
                        deviceBaseStandard.changeAlarmAttri(alarm,baseVo);
                        tAlarmSmokeService.smokeAdd(alarm);
                        // 报警才有策略，心跳直接跳过。
                        DeviceCompanyVo companyVo = deviceInstallFeignClient.smokePerSonByDeviceId(baseVo.getId());
                        tStrategyAlarmService.getParamAndMQSend(companyVo,baseVo.getDeviceType(),alarm.getEventCode());
                    }
                    deviceBaseStandard.changeHeartAttri(heart,baseVo);
                    tHeartSmokeService.smokeAdd(heart);
                }
                redisTemplate.opsForValue().set(d_imei_alamr_,"1",90, TimeUnit.SECONDS);
            }

        }
        return "气感设备解析完成";
    }


}
