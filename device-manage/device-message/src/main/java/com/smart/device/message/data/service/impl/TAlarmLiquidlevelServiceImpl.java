package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.message.entity.TAlarmLiquidlevel;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.message.data.mapper.TAlarmLiquidlevelMapper;
import com.smart.device.message.data.service.TAlarmLiquidlevelService;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
public class TAlarmLiquidlevelServiceImpl extends ServiceImpl<TAlarmLiquidlevelMapper, TAlarmLiquidlevel> implements TAlarmLiquidlevelService {

    @Override
    public IPage<TAlarmLiquidlevel> liquidlevelList(Page page, AlarmVo vo) {
        return null;
    }

    @Override
    public int liquidlevelAdd(TAlarmLiquidlevel tAlarmLiquidlevel) {
        IdWorker idWorker = new IdWorker(0,0);
        tAlarmLiquidlevel.setId(idWorker.nextId());
        this.save(tAlarmLiquidlevel);
        return 0;
    }
}
