package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.message.entity.TAlarmGas;
import com.smart.device.common.message.vo.AlarmVo;

/**
 * @author
 */
public interface TAlarmGasService extends IService<TAlarmGas> {

    IPage<TAlarmGas> gasList(Page page, AlarmVo vo);

    int gasAdd(TAlarmGas tAlarmGas);
    
}
