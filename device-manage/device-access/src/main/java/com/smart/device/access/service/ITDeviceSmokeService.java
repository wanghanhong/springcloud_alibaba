package com.smart.device.access.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.access.entity.TDeviceSmoke;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;

import java.util.List;

/**
* @author 
*/
public interface ITDeviceSmokeService extends IService<TDeviceSmoke> {

    TDeviceSmoke deviceSmokeUpdate(TDeviceSmoke entity);
    
    DeviceBaseVo selectDeviceSmoke(Long id,Long imei);

    List<DeviceBaseVo> selectSmokeAll(Integer type);

}
