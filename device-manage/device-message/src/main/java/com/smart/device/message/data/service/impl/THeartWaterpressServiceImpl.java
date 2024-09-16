package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.access.entity.TDeviceWaterpress;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.access.entity.vo.DeviceRedisVo;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.constant.RedisConst;
import com.smart.device.common.install.entity.TManagerWaterpress;
import com.smart.device.common.install.entity.vo.SdDeviceVo;
import com.smart.device.common.message.entity.THeartWaterpress;
import com.smart.device.message.data.entity.vo.HeartVo;
import com.smart.device.message.data.entity.vo.THeartWaterpressVo;
import com.smart.device.message.data.mapper.THeartWaterpressMapper;
import com.smart.device.message.feign.DeviceBaseFeignClient;
import com.smart.device.message.data.service.THeartWaterpressService;
import com.smart.device.message.feign.DeviceInstallFeignClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author
 */
@Service
public class THeartWaterpressServiceImpl extends ServiceImpl<THeartWaterpressMapper, THeartWaterpress> implements THeartWaterpressService {

    @Resource
    private THeartWaterpressMapper tHeartWaterpressMapper;
    @Resource
    private DeviceBaseFeignClient deviceBaseFeignClient;
    @Resource
    private DeviceInstallFeignClient deviceInstallFeignClient;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public IPage<THeartWaterpress> waterpressList(Page page, HeartVo vo) {
        return null;
    }

    @Override
    public Long waterpressAdd(THeartWaterpress tHeartWaterpress) {
        IdWorker idWorker = new IdWorker(0,0);
        tHeartWaterpress.setId(idWorker.nextId());
        this.save(tHeartWaterpress);
        return tHeartWaterpress.getId();
    }

    @Override
    public THeartWaterpressVo selectWaterpressLimit(HeartVo vo){
        THeartWaterpressVo res = new THeartWaterpressVo();
        if(vo.getImei() != null ){
            DeviceBaseVo baseVo = deviceBaseFeignClient.selectDeviceWaterpressByImei(vo.getImei());
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
                res.setDeviceState(baseVo.getDeviceState());
                res.setDeviceStateName(baseVo.getDeviceStateName());
                res.setFinalTime(baseVo.getFinalTime());
                THeartWaterpress deviceentity = tHeartWaterpressMapper.selectWaterpressLimit(vo);
                if(deviceentity != null){
                    res.setSignalStrength(deviceentity.getSignalStrength());
                    res.setContent(deviceentity.getContent());
                    res.setCreateTime(deviceentity.getCreateTime());

                    res.setSoftVersion(deviceentity.getSoftVersion());
                    res.setHeartTime(deviceentity.getHeartTime());
                    res.setDeviceVolt(deviceentity.getDeviceVolt());
                    res.setElectricQuantity(deviceentity.getElectricQuantity());
                    res.setTemperature(deviceentity.getTemperature());

                    res.setColletUint(deviceentity.getColletUint());
                    res.setColletValue(deviceentity.getColletValue());
                    res.setTransUint(deviceentity.getTransUint());
                    res.setTransValue(deviceentity.getTransValue());
                    res.setLowValue(deviceentity.getLowValue());
                    res.setHighValue(deviceentity.getHighValue());
                    res.setPresentValue(deviceentity.getPresentValue());
                    res.setWptime1(deviceentity.getWptime1());
                    res.setWpdata1(deviceentity.getWpdata1());
                    res.setCreateTime(deviceentity.getCreateTime());
                }
            }
        }
        return res;
    }

    @Override
    public SdDeviceVo selectWaterpressLast(Long deviceId) {
        return tHeartWaterpressMapper.selectWaterpressLast(deviceId);
    }

    @Override
    public void updateWaterpressStatusAll(Long imei,Long deviceId) {
        if(imei != null && deviceId != null){
            String key = RedisConst.CACHE_DEVICE_IMEI+imei;
            DeviceRedisVo redisVo = (DeviceRedisVo)redisTemplate.opsForValue().get(key);
            if(redisVo != null && redisVo.getDeviceState() == 2){
                TDeviceWaterpress vo = new TDeviceWaterpress();
                TManagerWaterpress manager = new TManagerWaterpress();
                vo.setId(deviceId);
                manager.setDeviceId(deviceId);

                vo.setDeviceState(DeviceConstant.DEVICE_STATE_ONLINE);
                vo.setDeviceStateName(DeviceConstant.DEVICE_STATE_ONLINE_NAME);
                manager.setDeviceState(DeviceConstant.DEVICE_STATE_ONLINE);
                manager.setDeviceStateName(DeviceConstant.DEVICE_STATE_ONLINE_NAME);
                deviceBaseFeignClient.deviceWaterpressUpdate(vo);
                deviceInstallFeignClient.updateWaterpressStatus(manager);
            }
        }
    }

}
