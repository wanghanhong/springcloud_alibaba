package com.smart.device.access.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.access.entity.TDeviceCameras;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
@Mapper
public interface TDeviceCamerasMapper extends BaseMapper<TDeviceCameras> {

    List<Long> findCamerasDevice();

    List<Long> findCamerasIds();
    
    DeviceBaseVo selectDeviceCameras(@Param("vo") DeviceBaseVo vo);

}
