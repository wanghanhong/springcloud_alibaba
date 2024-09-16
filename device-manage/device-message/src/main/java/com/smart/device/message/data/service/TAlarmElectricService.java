package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TAlarmElectric;
import com.smart.device.common.message.vo.AlarmVo;

/**
 * @author
 */
public interface TAlarmElectricService extends IService<TAlarmElectric> {

    IPage<AlarmVo> alarmElectricList(PageDomain page, DeviceCompanyVo vo);
    IPage<DeviceCompanyVo> alarmElectricListUser(PageDomain page, DeviceCompanyVo vo);

    int electricAdd(TAlarmElectric tAlarmElectric);
    AlarmVo  getLastElectricAlarm(DeviceCompanyVo vo);
}
