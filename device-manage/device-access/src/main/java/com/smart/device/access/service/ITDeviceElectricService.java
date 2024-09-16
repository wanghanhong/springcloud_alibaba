package com.smart.device.access.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.access.entity.TDeviceElectric;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;

import java.util.List;

/**
* @author 
*/
public interface ITDeviceElectricService extends IService<TDeviceElectric> {

    TDeviceElectric deviceElectricUpdate(TDeviceElectric entity);
    // 更改设定报警值
    int updateDBElectric(TDeviceElectric entity);

    DeviceBaseVo selectDeviceElectric(Long id,Long imei);

    List<DeviceBaseVo> selectElectricAll(Integer type);

}
