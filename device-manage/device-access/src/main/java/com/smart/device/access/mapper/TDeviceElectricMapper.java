package com.smart.device.access.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.access.entity.TDeviceElectric;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 
*/
@Mapper
public interface TDeviceElectricMapper extends BaseMapper<TDeviceElectric> {
    List<Long> findElectricDevice();

    List<Long> findElectricIds();
    // 更改设定报警值
    int updateDBElectric(@Param("vo") TDeviceElectric vo);

    DeviceBaseVo selectDeviceElectric(@Param("vo") DeviceBaseVo vo);

    List<DeviceBaseVo> selectElectricAllHigh();
    List<DeviceBaseVo> selectElectricAllLow();

}
