package com.smart.device.access.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.access.entity.TDeviceSmoke;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.access.entity.vo.DeviceRedisVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
* @author 
*/
@Mapper
public interface TDeviceSmokeMapper extends BaseMapper<TDeviceSmoke> {

    List<Long> findSmokeDevice();

    List<Long> findSmokeIds();

    DeviceBaseVo selectDeviceSmoke(@Param("vo") DeviceBaseVo vo);

    List<DeviceBaseVo> selectSmokeAllHigh();
    List<DeviceBaseVo> selectSmokeAllLow();

    // 查询所有的离线的设备
    List<DeviceRedisVo> selectOffLineList();
}
