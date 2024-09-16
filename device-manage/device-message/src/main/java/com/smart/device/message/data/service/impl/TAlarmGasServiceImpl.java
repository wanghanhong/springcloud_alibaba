package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.message.entity.TAlarmGas;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.message.data.mapper.TAlarmGasMapper;
import com.smart.device.message.data.service.TAlarmGasService;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
public class TAlarmGasServiceImpl extends ServiceImpl<TAlarmGasMapper, TAlarmGas> implements TAlarmGasService {

    @Override
    public IPage<TAlarmGas> gasList(Page page, AlarmVo vo) {
        return null;
    }

    @Override
    public int gasAdd(TAlarmGas tAlarmGas) {
        IdWorker idWorker = new IdWorker(0,0);
        tAlarmGas.setId(idWorker.nextId());
        this.save(tAlarmGas);
        return 0;
    }
}
