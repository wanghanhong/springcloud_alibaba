package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.message.entity.TAlarmTemp;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.message.data.mapper.TAlarmTempMapper;
import com.smart.device.message.data.service.TAlarmTempService;

public class TAlarmTempServiceImpl extends ServiceImpl<TAlarmTempMapper, TAlarmTemp> implements TAlarmTempService {

    @Override
    public IPage<TAlarmTemp> tempList(Page page, AlarmVo vo) {
        return null;
    }

    @Override
    public int tempAdd(TAlarmTemp tAlarmTemp) {
        IdWorker idWorker = new IdWorker(0,0);
        tAlarmTemp.setId(idWorker.nextId());
        this.save(tAlarmTemp);
        return 0;
    }

}
