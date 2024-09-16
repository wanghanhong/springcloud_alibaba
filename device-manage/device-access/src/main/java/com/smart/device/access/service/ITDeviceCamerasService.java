package com.smart.device.access.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.access.entity.TDeviceCameras;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;

/**
 * @author f
 */
public interface ITDeviceCamerasService extends IService<TDeviceCameras> {

    DeviceBaseVo selectDeviceCameras(String sn);

}
