package com.smart.device.message.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.install.entity.vo.SdDeviceVo;
import com.smart.device.common.message.entity.THeartElectric;
import com.smart.device.message.data.entity.vo.HeartVo;
import org.apache.ibatis.annotations.Param;

public interface THeartElectricMapper extends BaseMapper<THeartElectric> {

    THeartElectric selectElectricLimit(@Param("vo") HeartVo vo);

    SdDeviceVo selectElectricLast(@Param("deviceId") Long deviceId);

    void insertDeviceElectric(@Param("vo") DeviceBaseVo vo);
}
