package com.smart.device.access.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.access.mapper.TDeviceFirehostMapper;
import com.smart.device.access.service.ITDeviceFirehostService;
import com.smart.device.common.access.entity.TDeviceFirehost;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 消防主机设备
* @author 
*/
@Service
public class TDeviceFirehostServiceImpl extends ServiceImpl<TDeviceFirehostMapper, TDeviceFirehost> implements ITDeviceFirehostService {

    @Resource
    private TDeviceFirehostMapper tDeviceFirehostMapper;

    @Override
    public TDeviceFirehost devicFirehostUpdate(TDeviceFirehost entity) {
        tDeviceFirehostMapper.updateById(entity);
        return entity;
    }
}
