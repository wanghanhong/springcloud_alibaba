package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.message.entity.THeartWaterpressSon;
import com.smart.device.message.data.entity.vo.HeartVo;
import com.smart.device.message.data.mapper.THeartWaterpressSonMapper;
import com.smart.device.message.data.service.THeartWaterpressSonService;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
public class THeartWaterpressSonServiceImpl extends ServiceImpl<THeartWaterpressSonMapper, THeartWaterpressSon> implements THeartWaterpressSonService {

    @Override
    public IPage<THeartWaterpressSon> waterpressSonList(Page page, HeartVo vo) {
        return null;
    }

    @Override
    public int waterpressSonAdd(THeartWaterpressSon tHeartWaterpressSon) {
        IdWorker idWorker = new IdWorker(0,0);
        tHeartWaterpressSon.setId(idWorker.nextId());
        this.save(tHeartWaterpressSon);
        return 0;
    }
}
