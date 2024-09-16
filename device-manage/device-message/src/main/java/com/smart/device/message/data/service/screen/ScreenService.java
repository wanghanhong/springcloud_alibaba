package com.smart.device.message.data.service.screen;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.install.entity.vo.ScreenMainVo;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.common.install.entity.vo.SdDeviceVo;
import java.util.List;
import java.util.Map;

public interface ScreenService {
    
    ScreenMainVo internetDept(DeviceCompanyVo vo);

    List<Map<String,Object>> deviceNewAlarmDay(DeviceCompanyVo vo);

    List<Map<String,Object>> deviceNewDay(DeviceCompanyVo vo);

    List<Map<String,Object>> alarmState(DeviceCompanyVo vo);

    List<Map<String,Object>>  deviceFaultDay(DeviceCompanyVo vo);

    List<Map<String,Object>> alarmDeptDay(DeviceCompanyVo vo);

    List<Map<String,Object>>  voiceCall(DeviceCompanyVo vo);

    List<DeviceCompanyVo> deptStateList(DeviceCompanyVo vo);

    List<DeviceCompanyVo> deptInfoDay(DeviceCompanyVo vo);

    List<DeviceCompanyVo> deviceInfoDay(DeviceCompanyVo vo);

    List<DeviceCompanyVo> alarmInfoDay(DeviceCompanyVo vo);

    // 所有报警设备按照经纬度 分组
    IPage<AlarmVo> MapDevicesGroupByLL(DeviceCompanyVo vo);
    // 地图--设备列表
    IPage<AlarmVo> MapDevicesDetailByID(PageDomain page, AlarmVo alarmVO);

    void screenSendPhone(DeviceCompanyVo vo);

}
