package com.smart.device.message.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.vo.SdDeviceVo;
import com.smart.device.common.message.entity.THeartWaterpress;
import com.smart.device.message.data.entity.vo.HeartVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author
 */

public interface THeartWaterpressMapper extends BaseMapper<THeartWaterpress> {

    THeartWaterpress selectWaterpressLimit(@Param("vo") HeartVo vo);

    SdDeviceVo selectWaterpressLast(@Param("deviceId") Long deviceId);

}
