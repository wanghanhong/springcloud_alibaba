package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.common.access.entity.TDeviceCameras;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.message.data.mapper.TCamerasMapper;
import com.smart.device.message.feign.DeviceBaseFeignClient;
import com.smart.device.message.data.service.TCamerasService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author
 */
@Service
public class TCamerasServiceImpl extends ServiceImpl<TCamerasMapper, TDeviceCameras> implements TCamerasService {

    @Resource
    private DeviceBaseFeignClient deviceBaseFeignClient;

    @Override
    public DeviceBaseVo selectCamerasLimit(TDeviceCameras vo){
        DeviceBaseVo baseVo = deviceBaseFeignClient.selectDeviceCamerasBySn(vo.getSn());
        return baseVo;
    }

}
