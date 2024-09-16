package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.access.entity.TDeviceCameras;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;

/**
 * @author
 */
public interface TCamerasService extends IService<TDeviceCameras> {

    DeviceBaseVo selectCamerasLimit(TDeviceCameras vo);

}
