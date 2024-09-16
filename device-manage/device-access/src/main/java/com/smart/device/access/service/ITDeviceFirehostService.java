package com.smart.device.access.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.access.entity.TDeviceFirehost;

/**
* @author 
*/
public interface ITDeviceFirehostService extends IService<TDeviceFirehost> {

    TDeviceFirehost devicFirehostUpdate(TDeviceFirehost entity);

}
