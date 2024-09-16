package com.smart.device.message.data.mapper.screen;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.vo.AlarmVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface XcxMapper {

    IPage<AlarmVo> deviceAlarmsList(Page<DeviceCompanyVo> page, @Param("vo") DeviceCompanyVo alarmVO);





}
