package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.message.entity.THeartFirehost;
import com.smart.device.message.data.entity.vo.HeartVo;
import com.smart.device.message.data.mapper.THeartFirehostMapper;
import com.smart.device.message.data.service.THeartFirehostService;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
public class THeartFirehostServiceImpl extends ServiceImpl<THeartFirehostMapper, THeartFirehost> implements THeartFirehostService {

    @Override
    public IPage<THeartFirehost> firehostList(Page page, HeartVo vo) {
        return null;
    }

    @Override
    public int firehostAdd(THeartFirehost tHeartFirehost) {
        IdWorker idWorker = new IdWorker(0,0);
        tHeartFirehost.setId(idWorker.nextId());
        this.save(tHeartFirehost);
        return 0;
    }
}
