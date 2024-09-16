package com.smart.device.access.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.access.entity.TDeviceLiquidlevel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 
*/
@Mapper
public interface TDeviceLiquidlevelMapper extends BaseMapper<TDeviceLiquidlevel> {

    List<Long> findLiquidlevelDevice();

    List<Long> findLiquidlevelIds();
}
