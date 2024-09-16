package com.smart.device.access.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.access.entity.TDeviceUserinfoType;

public interface TDeviceUserinfoTypeService extends IService<TDeviceUserinfoType> {

    TDeviceUserinfoType getUserinfoType(String partId);



}
