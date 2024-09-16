package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.message.entity.THeartLiquidlevel;
import com.smart.device.message.data.entity.vo.HeartVo;
import com.smart.device.message.data.mapper.THeartLiquidlevelMapper;
import com.smart.device.message.data.service.THeartLiquidlevelService;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
public class THeartLiquidlevelServiceImpl extends ServiceImpl<THeartLiquidlevelMapper, THeartLiquidlevel> implements THeartLiquidlevelService {

    @Override
    public IPage<THeartLiquidlevel> liquidlevelList(Page page, HeartVo vo) {
        return null;
    }

    @Override
    public int liquidlevelAdd(THeartLiquidlevel tHeartLiquidlevel) {
        IdWorker idWorker = new IdWorker(0,0);
        tHeartLiquidlevel.setId(idWorker.nextId());
        this.save(tHeartLiquidlevel);
        return 0;
    }
}
