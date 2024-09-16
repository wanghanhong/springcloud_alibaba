package com.smart.device.message.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.vo.SdDeviceVo;
import com.smart.device.common.message.entity.THeartSmoke;
import com.smart.device.message.data.entity.vo.HeartVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author
 */

public interface THeartSmokeMapper extends BaseMapper<THeartSmoke> {

    THeartSmoke selectSmokeLimit(@Param("vo") HeartVo vo);

    SdDeviceVo selectSmokeLast(@Param("deviceId") Long deviceId);

}
