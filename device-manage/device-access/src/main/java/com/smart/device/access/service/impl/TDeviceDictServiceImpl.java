package com.smart.device.access.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.access.mapper.TDeviceCamerasMapper;
import com.smart.device.access.mapper.TDeviceDictMapper;
import com.smart.device.access.service.ITDeviceDictService;
import com.smart.device.common.access.entity.TDeviceDict;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
* @author
*/
@Service
public class TDeviceDictServiceImpl extends ServiceImpl<TDeviceDictMapper, TDeviceDict> implements ITDeviceDictService {

    @Resource
    private TDeviceDictMapper tDeviceDictMapper;

    @Override
    public List<TDeviceDict> queryDeviceParentTypeList() {
        List<TDeviceDict> list = tDeviceDictMapper.queryDeviceParentTypeList();
        return list;
    }
    @Override
    public List<TDeviceDict> queryDeviceTypeList() {
        List<TDeviceDict> list = tDeviceDictMapper.queryDeviceTypeList();
        return list;
    }
    @Override
    public TDeviceDict getDictBydeviceType(Integer deviceType) {
        TDeviceDict dict = tDeviceDictMapper.getDictBydeviceType(deviceType);
        return dict;
    }
}
