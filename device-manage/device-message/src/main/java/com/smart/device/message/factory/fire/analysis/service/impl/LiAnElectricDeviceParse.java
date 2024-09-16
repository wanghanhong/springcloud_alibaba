package com.smart.device.message.factory.fire.analysis.service.impl;

import com.smart.common.utils.IdWorker;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TAlarmElectric;
import com.smart.device.common.message.entity.THeartElectric;
import com.smart.device.common.message.entity.TStrategyAlarm;
import com.smart.device.message.common.DeviceMessageConstants;
import com.smart.device.message.data.mapper.THeartElectricMapper;
import com.smart.device.message.data.service.TAlarmElectricService;
import com.smart.device.message.data.service.THeartElectricService;
import com.smart.device.message.data.service.TStrategyAlarmService;
import com.smart.device.message.factory.fire.analysis.service.FireDeviceParse;
import com.smart.device.message.feign.DeviceBaseFeignClient;
import com.smart.device.message.feign.DeviceInstallFeignClient;
import com.smart.device.message.parse.fire.electric.lian.entity.ElectricLiAnEntity;
import com.smart.device.message.parse.fire.electric.lian.strategy.ElectricLiAnParseStrategy;
import com.smart.device.message.parse.fire.electric.lian.strategy.ElectricLiAnStandar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class LiAnElectricDeviceParse implements FireDeviceParse {

    @Resource
    private ElectricLiAnParseStrategy electricLiAnParseStrategy;
    @Resource
    private ElectricLiAnStandar electricLiAnStandar;
    @Resource
    private TAlarmElectricService tAlarmElectricService;
    @Resource
    private THeartElectricService tHeartElectricService;

    @Resource
    private DeviceBaseFeignClient deviceBaseFeignClient;
    @Resource
    private TStrategyAlarmService tStrategyAlarmService;
    @Resource
    private DeviceInstallFeignClient deviceInstallFeignClient;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private THeartElectricMapper heartElectricMapper;

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
        List<ElectricLiAnEntity> entitys = electricLiAnParseStrategy.assemblyData(data);
        for (int i=0;i<entitys.size();i++){
            ElectricLiAnEntity entity = entitys.get(i);
            TAlarmElectric alarm = electricLiAnStandar.changeToAlarm(entity);
            THeartElectric heart = electricLiAnStandar.changeToHeart(entity);
            if(alarm.getImei() != null || heart.getImei() != null ){
                String d_imei_alamr_ = DeviceMessageConstants.d_imei_alamr_+alarm.getImei()+"_"+entity.getPointId();
                String exist = (String)redisTemplate.opsForValue().get(d_imei_alamr_);
                if (exist  !=null && "1".equals(exist)){
                }else{
                    DeviceBaseVo baseVo = deviceBaseFeignClient.selectDeviceElectricByImei(alarm.getImei());
                    if(baseVo == null){
                        baseVo = deviceBaseFeignClient.selectDeviceElectricByImei(836521L);
                        IdWorker idWorker = new IdWorker(0,0);
                        baseVo.setId(idWorker.nextId());
                        baseVo.setImei(alarm.getImei());
                        baseVo.setDeviceCode(alarm.getImei());
                        heartElectricMapper.insertDeviceElectric(baseVo);
                    }
                    if(baseVo != null){
                        if(alarm.getImei() != null){
                            TStrategyAlarm strategyAlarm = tStrategyAlarmService.queryStrategyByTypeAndCode(baseVo.getDeviceType(),alarm.getEventCode());
                            if(strategyAlarm != null && strategyAlarm.getStrategyType() >= 0){
                                electricLiAnStandar.changeAlarmAttri(alarm,baseVo);
                                tAlarmElectricService.electricAdd(alarm);
                                // 报警才有策略，心跳直接跳过。
                                DeviceCompanyVo companyVo = deviceInstallFeignClient.electricPerSonByDeviceId(baseVo.getId());
                                tStrategyAlarmService.getParamAndMQSend(companyVo,baseVo.getDeviceType(),alarm.getEventCode());
                                log.info("yanan电--"+""+"发动一次报警");
                            }
                        }
                        electricLiAnStandar.changeHeartAttri(heart,baseVo);
                        tHeartElectricService.electricAdd(heart);
                    }
                    tHeartElectricService.updateElectricStatusAll(alarm.getImei(),baseVo.getId());
                    redisTemplate.opsForValue().set(d_imei_alamr_,"1",90, TimeUnit.SECONDS);
                }
            }
        }
        return "力安设备解析完成";
    }





}
