package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.access.entity.TDeviceElectric;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.access.entity.vo.DeviceRedisVo;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.constant.RedisConst;
import com.smart.device.common.install.entity.TManagerElectric;
import com.smart.device.common.install.entity.vo.SdDeviceVo;
import com.smart.device.common.message.entity.THeartElectric;
import com.smart.device.message.data.entity.vo.HeartVo;
import com.smart.device.message.data.entity.vo.THeartElectricVo;
import com.smart.device.message.data.mapper.THeartElectricMapper;
import com.smart.device.message.feign.DeviceBaseFeignClient;
import com.smart.device.message.data.service.THeartElectricService;
import com.smart.device.message.feign.DeviceInstallFeignClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author
 */
@Service
public class THeartElectricServiceImpl extends ServiceImpl<THeartElectricMapper, THeartElectric> implements THeartElectricService {

    @Resource
    private THeartElectricMapper tHeartElectricMapper;
    @Resource
    private DeviceBaseFeignClient deviceBaseFeignClient;
    @Resource
    private DeviceInstallFeignClient deviceInstallFeignClient;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public IPage<THeartElectric> electricList(Page page, HeartVo vo) {
        return null;
    }

    @Override
    public int electricAdd(THeartElectric tHeartElectric) {
        IdWorker idWorker = new IdWorker(0,0);
        tHeartElectric.setId(idWorker.nextId());
        this.save(tHeartElectric);
        return 0;
    }
    @Override
    public THeartElectricVo selectElectricLimit(HeartVo vo){
        THeartElectricVo res =  new THeartElectricVo();
        if(vo.getImei() != null ){
            DeviceBaseVo baseVo = deviceBaseFeignClient.selectDeviceElectricByImei(vo.getImei());
            if(baseVo != null){
                res.setId(baseVo.getId());
                res.setDeviceId(baseVo.getDeviceId());
                res.setDeviceName(baseVo.getDeviceName());
                res.setDeviceCode(baseVo.getDeviceCode());
                res.setDeviceType(baseVo.getDeviceType());
                res.setParentType(baseVo.getParentType());
                res.setDeviceTypeName(baseVo.getDeviceTypeName());
                res.setDeviceModel(baseVo.getDeviceModel());
                // 将设备状态查询出出来
                res.setAlarmElectric(baseVo.getAlarmElectric());
                res.setAlarmElectricLeakage(baseVo.getAlarmElectricLeakage());
                res.setAlarmTemperature(baseVo.getAlarmTemperature());
                res.setDeviceState(baseVo.getDeviceState());
                res.setDeviceStateName(baseVo.getDeviceStateName());

                THeartElectric deviceentity = tHeartElectricMapper.selectElectricLimit(vo);
                if(deviceentity != null){
                    res.setSignalStrength(deviceentity.getSignalStrength());
                    res.setCurrentA(deviceentity.getCurrentA());
                    res.setCurrentB(deviceentity.getCurrentB());
                    res.setCurrentC(deviceentity.getCurrentC());
                    res.setCurrentN(deviceentity.getCurrentN());
                    res.setTemperatureA(deviceentity.getTemperatureA());
                    res.setTemperatureB(deviceentity.getTemperatureB());
                    res.setTemperatureC(deviceentity.getTemperatureC());
                    res.setTemperatureN(deviceentity.getTemperatureN());
                    res.setAlarmTemperature(deviceentity.getAlarmTemperature());
                    res.setContent(deviceentity.getContent());
                    res.setCreateTime(deviceentity.getCreateTime());
                    res.setCurrentTemperature(deviceentity.getCurrentTemperature());
                    res.setCreateTime(deviceentity.getCreateTime());
                }
            }
        }
        return res;
    }

    @Override
    public SdDeviceVo selectElectricLast(Long deviceId) {
        return tHeartElectricMapper.selectElectricLast(deviceId);
    }

    @Override
    public void updateElectricStatusAll(Long imei,Long deviceId) {
        if(imei != null && deviceId != null){
            String key = RedisConst.CACHE_DEVICE_IMEI + imei;
            DeviceRedisVo redisVo = (DeviceRedisVo)redisTemplate.opsForValue().get(key);
            if(redisVo != null && redisVo.getDeviceState() == 2){
                TDeviceElectric vo = new TDeviceElectric();
                TManagerElectric manager = new TManagerElectric();
                vo.setId(deviceId);
                manager.setDeviceId(deviceId);

                vo.setDeviceState(DeviceConstant.DEVICE_STATE_ONLINE);
                vo.setDeviceStateName(DeviceConstant.DEVICE_STATE_ONLINE_NAME);
                manager.setDeviceState(DeviceConstant.DEVICE_STATE_ONLINE);
                manager.setDeviceStateName(DeviceConstant.DEVICE_STATE_ONLINE_NAME);
                deviceBaseFeignClient.deviceElectricUpdate(vo);
                deviceInstallFeignClient.updateElectricStatus(manager);
            }
        }
    }

}
