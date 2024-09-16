package com.smart.device.message.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TAlarmElectric;
import com.smart.device.common.message.vo.AlarmVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author
 */

public interface TAlarmElectricMapper extends BaseMapper<TAlarmElectric> {

    IPage<AlarmVo> alarmElectricList(Page<AlarmVo>page, @Param("vo") DeviceCompanyVo vo);
    IPage<DeviceCompanyVo> alarmElectricListUser(Page<DeviceCompanyVo>page, @Param("vo") DeviceCompanyVo vo);
    Integer updateAlarmEelectric(@Param("vo") AlarmVo vo);
    AlarmVo getLastEelectricAlarm(@Param("vo")DeviceCompanyVo vo);

}
