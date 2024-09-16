package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.access.entity.TDeviceSmoke;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.access.entity.vo.DeviceRedisVo;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.constant.RedisConst;
import com.smart.device.common.install.entity.TManagerSmoke;
import com.smart.device.common.install.entity.vo.SdDeviceVo;
import com.smart.device.common.message.entity.THeartSmoke;
import com.smart.device.message.data.entity.vo.HeartVo;
import com.smart.device.message.data.entity.vo.THeartSmokeVo;
import com.smart.device.message.data.mapper.THeartSmokeMapper;
import com.smart.device.message.feign.DeviceBaseFeignClient;
import com.smart.device.message.data.service.THeartSmokeService;
import com.smart.device.message.feign.DeviceInstallFeignClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author
 */
@Service
public class THeartSmokeServiceImpl extends ServiceImpl<THeartSmokeMapper, THeartSmoke> implements THeartSmokeService {

    @Resource
    private THeartSmokeMapper tHeartSmokeMapper;
    @Resource
    private DeviceBaseFeignClient deviceBaseFeignClient;
    @Resource
    private DeviceInstallFeignClient deviceInstallFeignClient;
    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public IPage<THeartSmoke> smokeList(Page page, HeartVo vo) {
        return null;
    }

    @Override
    public int smokeAdd(THeartSmoke tHeartSmoke) {
        IdWorker idWorker = new IdWorker(0,0);
        tHeartSmoke.setId(idWorker.nextId());
        this.save(tHeartSmoke);
        return 0;
    }

    @Override
    public THeartSmokeVo selectSmokeLimit(HeartVo vo){
        if(vo.getImei() != null ){
            THeartSmokeVo res = new THeartSmokeVo();
            DeviceBaseVo baseVo = deviceBaseFeignClient.selectDeviceSmokeByImei(vo.getImei());
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
                if(res.getFinalTime() == null){
                    res.setFinalTime(baseVo.getFinalTime());
                }
                if(res.getHeartInterval() == null){
                    res.setHeartInterval(baseVo.getHeartInterval());
                }
                res.setDeviceState(baseVo.getDeviceState());
                res.setDeviceStateName(baseVo.getDeviceStateName());

                THeartSmoke deviceentity = tHeartSmokeMapper.selectSmokeLimit(vo);
                if(deviceentity != null){
                    res.setSignalStrength(deviceentity.getSignalStrength());
                    res.setContent(deviceentity.getContent());
                    res.setCreateTime(deviceentity.getCreateTime());

                    res.setSoftVersion(deviceentity.getSoftVersion());
                    res.setHeartTime(deviceentity.getHeartTime());
                    res.setDeviceVolt(deviceentity.getDeviceVolt());
                    res.setElectricQuantity(deviceentity.getElectricQuantity());
                    res.setTemperature(deviceentity.getTemperature());
                    res.setSmokeScope(deviceentity.getSmokeScope());
                    res.setSelfDetectRet(deviceentity.getSelfDetectRet());
                    res.setSelfDetectTime(deviceentity.getSelfDetectTime());
                    res.setHeartInterval(deviceentity.getHeartInterval());
                    res.setIccid(deviceentity.getIccid());
                    res.setCreateTime(deviceentity.getCreateTime());
                }
            }
            return res;
        }
        return null;
    }

    @Override
    public SdDeviceVo selectSmokeLast(Long deviceId) {
        return tHeartSmokeMapper.selectSmokeLast(deviceId);
    }

    @Override
    public void updateSmokeStatusAll(Long imei,Long deviceId) {
        if(imei != null && deviceId != null){
            String key = RedisConst.CACHE_DEVICE_IMEI+imei;
            DeviceRedisVo redisVo = (DeviceRedisVo)redisTemplate.opsForValue().get(key);
            // redisVo 空，代表他是正常的 ，非空等于2表明是离线的，需要更改状态
            if(redisVo != null && redisVo.getDeviceState() == 2){
                TDeviceSmoke vo = new TDeviceSmoke();
                TManagerSmoke manager = new TManagerSmoke();
                vo.setId(deviceId);
                manager.setDeviceId(deviceId);

                vo.setDeviceState(DeviceConstant.DEVICE_STATE_ONLINE);
                vo.setDeviceStateName(DeviceConstant.DEVICE_STATE_ONLINE_NAME);
                manager.setDeviceState(DeviceConstant.DEVICE_STATE_ONLINE);
                manager.setDeviceStateName(DeviceConstant.DEVICE_STATE_ONLINE_NAME);
                deviceBaseFeignClient.deviceSmokeUpdate(vo);
                deviceInstallFeignClient.updateSmokeStatus(manager);
            }
        }
    }

}
