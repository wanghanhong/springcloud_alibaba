package com.smart.device.message.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.message.entity.TStrategyAlarm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author
 */

public interface TStrategyAlarmMapper extends BaseMapper<TStrategyAlarm> {

    List<TStrategyAlarm> queryStrategys(@Param("vo") TStrategyAlarm vo);

    TStrategyAlarm queryStrategyByTypeAndCode(@Param("vo") TStrategyAlarm vo);

}
