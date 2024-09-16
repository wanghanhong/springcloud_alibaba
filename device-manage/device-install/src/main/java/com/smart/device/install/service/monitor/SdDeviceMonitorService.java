package com.smart.device.install.service.monitor;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.vo.DeviceMonitorVo;

public interface SdDeviceMonitorService extends IService<DeviceMonitorVo> {

    // 根据部门查询 报警信息合计
    IPage<DeviceMonitorVo> deptControl(PageDomain page,DeviceMonitorVo alarmVO);
    // 根据部门查询 报警信息合计（单个部门）
    IPage<DeviceMonitorVo> deviceAlarmsList(PageDomain page, DeviceMonitorVo alarmVO);

}
