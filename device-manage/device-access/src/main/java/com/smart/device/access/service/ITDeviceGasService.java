package com.smart.device.access.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.access.entity.TDeviceGas;

/**
* @author 
*/
public interface ITDeviceGasService extends IService<TDeviceGas> {

    TDeviceGas deviceGasUpdate(TDeviceGas entity);

}
