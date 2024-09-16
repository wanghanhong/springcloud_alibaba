package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.message.entity.THeartGas;
import com.smart.device.message.data.entity.vo.HeartVo;
import com.smart.device.message.data.mapper.THeartGasMapper;
import com.smart.device.message.data.service.THeartGasService;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
public class THeartGasServiceImpl extends ServiceImpl<THeartGasMapper, THeartGas> implements THeartGasService {

    @Override
    public IPage<THeartGas> gasList(Page page, HeartVo vo) {
        return null;
    }

    @Override
    public int gasAdd(THeartGas tHeartGas) {
        IdWorker idWorker = new IdWorker(0,0);
        tHeartGas.setId(idWorker.nextId());
        this.save(tHeartGas);
        return 0;
    }
}
