package com.smart.device.message.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TAlarmWaterpress;
import com.smart.device.common.message.vo.AlarmVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author
 */

public interface TAlarmWaterpressMapper extends BaseMapper<TAlarmWaterpress> {

    IPage<AlarmVo> alarmWaterpressList(Page<AlarmVo>page, @Param("vo") DeviceCompanyVo vo);
    IPage<DeviceCompanyVo> alarmWaterpressListUser(Page<DeviceCompanyVo>page, @Param("vo") DeviceCompanyVo vo);

    Integer updateAlarmWaterpress(@Param("vo") AlarmVo vo);
    AlarmVo getLastWaterpressAlarm(@Param("vo")DeviceCompanyVo vo);
}
