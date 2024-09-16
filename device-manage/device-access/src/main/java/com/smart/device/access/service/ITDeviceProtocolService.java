package com.smart.device.access.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.access.entity.TDeviceProtocol;
import com.smart.device.common.access.entity.TDeviceProtocol;

import java.util.List;

/**
* @author 
*/
public interface ITDeviceProtocolService extends IService<TDeviceProtocol> {
    
    List<TDeviceProtocol> queryProtocolList();

}
