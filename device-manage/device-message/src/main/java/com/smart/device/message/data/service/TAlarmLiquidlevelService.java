package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.message.entity.TAlarmLiquidlevel;
import com.smart.device.common.message.vo.AlarmVo;

/**
 * @author
 */
public interface TAlarmLiquidlevelService extends IService<TAlarmLiquidlevel> {

    IPage<TAlarmLiquidlevel> liquidlevelList(Page page, AlarmVo vo);

    int liquidlevelAdd(TAlarmLiquidlevel tAlarmLiquidlevel);

}
