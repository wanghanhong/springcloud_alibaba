package com.smart.device.access.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.access.mapper.TDeviceUserinfoTypeMapper;
import com.smart.device.access.service.TDeviceUserinfoTypeService;
import com.smart.device.common.access.entity.TDeviceUserinfoType;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TDeviceUserinfoTypeImpl extends ServiceImpl<TDeviceUserinfoTypeMapper, TDeviceUserinfoType> implements TDeviceUserinfoTypeService {

    @Override
    public TDeviceUserinfoType getUserinfoType(String partId) {
        return baseMapper.selectOne(new LambdaQueryWrapper<TDeviceUserinfoType>().eq(TDeviceUserinfoType::getPartId, partId));
    }

    public static void main(String[] args) {
        Date date = new Date(Long.parseLong("1608691642951"));
        System.out.println(date);
        System.out.println(12);
    }

}
