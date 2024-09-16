package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.message.entity.THeartTemp;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.message.data.entity.vo.HeartVo;
import com.smart.device.message.data.mapper.THeartTempMapper;
import com.smart.device.message.feign.DeviceBaseFeignClient;
import com.smart.device.message.data.service.THeartTempService;

import javax.annotation.Resource;

/**
 * @author jungle
 */
public class THeartTempServiceImpl extends ServiceImpl<THeartTempMapper, THeartTemp> implements THeartTempService {
    @Resource
    private THeartTempMapper tHeartTempMapper;
    @Resource
    private DeviceBaseFeignClient deviceBaseFeignClient;

    @Override
    public IPage<THeartTemp> tempList(Page page, AlarmVo vo) {
        return null;
    }

    @Override
    public int tempAdd(THeartTemp tHeartTemp) {
        IdWorker idWorker = new IdWorker(0,0);
        tHeartTemp.setId(idWorker.nextId());
        this.save(tHeartTemp);
        return 0;
    }

    @Override
    public THeartTemp selectTempLimit(HeartVo vo) {
        THeartTemp res = new THeartTemp();
        if(vo.getImei() != null ){
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
                res.setDeviceState(baseVo.getDeviceState());
                res.setDeviceStateName(baseVo.getDeviceStateName());
                THeartTemp deviceentity = tHeartTempMapper.selectTempLimit(vo);
                if(deviceentity != null){
                    res.setContent(deviceentity.getContent());
                    res.setCreateTime(deviceentity.getCreateTime());
                    res.setHeartTime(deviceentity.getHeartTime());
                    res.setIccid(deviceentity.getIccid());
                    res.setCreateTime(deviceentity.getCreateTime());
                }
            }
        }
        return res;
    }
}
