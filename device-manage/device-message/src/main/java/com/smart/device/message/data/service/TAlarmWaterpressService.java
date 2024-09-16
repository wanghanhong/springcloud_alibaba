package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TAlarmWaterpress;
import com.smart.device.common.message.vo.AlarmVo;

/**
 * @author
 */
public interface TAlarmWaterpressService extends IService<TAlarmWaterpress> {

    IPage<AlarmVo> alarmWaterpressList(PageDomain page, DeviceCompanyVo vo);
    IPage<DeviceCompanyVo> alarmWaterpressListUser(PageDomain page, DeviceCompanyVo vo);
    int waterpressAdd(TAlarmWaterpress tAlarmWaterpress);
    AlarmVo  getLastWaterpressAlarm(DeviceCompanyVo vo);
}
