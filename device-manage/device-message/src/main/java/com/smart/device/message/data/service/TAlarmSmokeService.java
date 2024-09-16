package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TAlarmGas;
import com.smart.device.common.message.entity.TAlarmSmoke;
import com.smart.device.common.message.vo.AlarmVo;

/**
 * @author
 */
public interface TAlarmSmokeService extends IService<TAlarmSmoke> {

    IPage<AlarmVo>alarmSmokeList(PageDomain page, DeviceCompanyVo vo);

    int smokeAdd(TAlarmSmoke tAlarmSmoke);

    IPage<DeviceCompanyVo>alarmSmokeListUser(PageDomain page, DeviceCompanyVo vo);

    AlarmVo  getLastSmokeAlarm(DeviceCompanyVo vo);
}
