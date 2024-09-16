package com.smart.device.access.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.access.entity.TDeviceDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
* @author 
*/
public interface ITDeviceDictService extends IService<TDeviceDict> {

    List<TDeviceDict> queryDeviceParentTypeList();

    List<TDeviceDict> queryDeviceTypeList();

    TDeviceDict getDictBydeviceType(Integer deviceType);



}
