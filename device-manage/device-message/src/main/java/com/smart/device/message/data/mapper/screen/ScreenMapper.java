package com.smart.device.message.data.mapper.screen;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.common.install.entity.vo.SdDeviceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface ScreenMapper {

    List<Map<Integer,Object>> internetDept(@Param("vo") DeviceCompanyVo vo);

    List<Map<String,Object>> deviceNewAlarmDay(@Param("vo") DeviceCompanyVo vo);

    List<Map<String,Object>> deviceAlarmTotal(@Param("vo") DeviceCompanyVo vo);

    List<Map<String,Object>> deviceNewDay(@Param("vo") DeviceCompanyVo vo);

    List<Map<String,Object>> alarmState(@Param("vo") DeviceCompanyVo vo);

    Map<String,Object>  deviceAlarmDay(@Param("vo") DeviceCompanyVo vo);

    List<Map<String,Object>> alarmDeptDay(@Param("vo") DeviceCompanyVo vo);

    List<Map<String,Object>>  voiceCall(@Param("vo") DeviceCompanyVo vo);

    List<DeviceCompanyVo> deptStateList(@Param("vo") DeviceCompanyVo vo);

    Map<String,Object>  deviceDeptDay(@Param("vo") DeviceCompanyVo vo);

    Map<String,Object>  deviceNumDay(@Param("vo") DeviceCompanyVo vo);

    List<DeviceCompanyVo> deptInfoDay(@Param("vo") DeviceCompanyVo vo);

    List<DeviceCompanyVo> deviceInfoDay(@Param("vo") DeviceCompanyVo vo);

    List<DeviceCompanyVo> alarmInfoDay(@Param("vo") DeviceCompanyVo vo);

    List<AlarmVo> MapdevicesGroupByLL(@Param("vo") DeviceCompanyVo vo);
    Long MapdevicesGroupByLLCount(@Param("vo") DeviceCompanyVo vo);

    IPage<AlarmVo> alarmDevicesList(Page<AlarmVo> page, @Param("vo") AlarmVo alarmVO);




}
