package com.smart.device.access.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.access.entity.TDeviceGas;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 
*/
@Mapper
public interface TDeviceGasMapper extends BaseMapper<TDeviceGas> {
    List<Long> findGasDevice();

    List<Long> findGasIds();
}
