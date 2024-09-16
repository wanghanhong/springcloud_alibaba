package com.smart.device.access.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.access.mapper.TUserInfoTypeMapper;
import com.smart.device.access.service.TUserInfoTypeService;
import com.smart.device.common.access.entity.UserInfoType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("tUserinfoTypeServiceImpl")
@Slf4j
class TUserinfoTypeServiceImpl extends ServiceImpl<TUserInfoTypeMapper, UserInfoType> implements TUserInfoTypeService {

    @Resource
    private TUserInfoTypeMapper tUserinfoTypeMapper;

    @Override
    public UserInfoType getDictByDeviceType(String partId) {
        UserInfoType dictByDeviceType = tUserinfoTypeMapper.getDictByDeviceType(partId);
        return dictByDeviceType;
    }
}
