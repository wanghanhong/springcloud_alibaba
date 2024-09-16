package com.smart.device.message.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.message.entity.THeartTemp;
import com.smart.device.message.data.entity.vo.HeartVo;
import org.apache.ibatis.annotations.Param;

public interface THeartTempMapper extends BaseMapper<THeartTemp> {

    THeartTemp selectTempLimit(@Param("vo") HeartVo vo);
}
