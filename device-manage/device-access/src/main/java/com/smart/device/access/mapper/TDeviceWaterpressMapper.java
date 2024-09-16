package com.smart.device.access.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.access.entity.TDeviceWaterpress;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 
*/
@Mapper
public interface TDeviceWaterpressMapper extends BaseMapper<TDeviceWaterpress> {

    List<Long> findPressDevice();

    List<Long> findWaterpressIds();
    // 下拉框-查询所有的水压、液位
    List<TDeviceWaterpress> deviceWaterpresssAll(@Param("vo") TDeviceWaterpress vo);

    DeviceBaseVo selectDeviceWaterpress(@Param("vo") DeviceBaseVo vo);

    List<DeviceBaseVo> selectWaterpressAllHigh();
    List<DeviceBaseVo> selectWaterpressAllLow();

}
