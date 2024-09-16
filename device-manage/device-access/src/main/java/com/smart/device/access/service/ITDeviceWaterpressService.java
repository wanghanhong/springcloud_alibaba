package com.smart.device.access.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.access.entity.TDeviceWaterpress;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;

import java.util.List;

/**
* @author 
*/
public interface ITDeviceWaterpressService extends IService<TDeviceWaterpress> {

    TDeviceWaterpress deviceWaterpressUpdate(TDeviceWaterpress entity);

    List<TDeviceWaterpress> deviceWaterpresssAll(TDeviceWaterpress vo);

    List<TDeviceWaterpress> deviceLiquidlevelAll(TDeviceWaterpress vo);

    DeviceBaseVo selectDeviceWaterpress(Long id,Long imei);

    List<DeviceBaseVo> selectWaterpressAll(Integer type);

}
