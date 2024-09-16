package com.smart.device.access.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.access.entity.UserInfoType;

public interface TUserInfoTypeService extends IService<UserInfoType> {

    UserInfoType getDictByDeviceType(String partId);

}
