package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.message.entity.TAlarmFirehost;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.message.data.mapper.TAlarmFirehostMapper;
import com.smart.device.message.data.service.TAlarmFirehostService;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
public class TAlarmFirehostServiceImpl extends ServiceImpl<TAlarmFirehostMapper, TAlarmFirehost> implements TAlarmFirehostService {

    @Override
    public IPage<TAlarmFirehost> firehostList(Page page, AlarmVo vo) {
        return null;
    }

    @Override
    public int firehostAdd(TAlarmFirehost tAlarmFirehost) {
        IdWorker idWorker = new IdWorker(0,0);
        tAlarmFirehost.setId(idWorker.nextId());
        this.save(tAlarmFirehost);
        return 0;
    }
}
