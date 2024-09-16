package com.smart.device.message.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TAlarmSmoke;
import com.smart.device.common.message.vo.AlarmVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author
 */

public interface TAlarmSmokeMapper extends BaseMapper<TAlarmSmoke> {

    IPage<AlarmVo> alarmSmokeList(Page<AlarmVo> page, @Param("vo") DeviceCompanyVo vo);
    IPage<DeviceCompanyVo> alarmSmokeListUser(Page<DeviceCompanyVo> page, @Param("vo") DeviceCompanyVo vo);
    Integer updateAlarmSmoke(@Param("vo") AlarmVo vo);
    AlarmVo getLastSmokeAlarm(@Param("vo")DeviceCompanyVo vo);
}
