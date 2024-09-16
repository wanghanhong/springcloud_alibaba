package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.message.entity.TAlarmTemp;
import com.smart.device.common.message.vo.AlarmVo;

public interface TAlarmTempService extends IService<TAlarmTemp> {

    IPage<TAlarmTemp> tempList(Page page, AlarmVo vo);

    int tempAdd(TAlarmTemp tAlarmTemp);
}
