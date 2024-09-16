package com.smart.device.message.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.common.message.entity.TMsgDict;
import com.smart.device.message.data.mapper.TBaseMsgMapper;
import com.smart.device.message.data.service.TMsgDictService;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
public class TMsgDictServiceImpl extends ServiceImpl<TBaseMsgMapper, TMsgDict> implements TMsgDictService {

    @Override
    public IPage<TMsgDict> tMsgDictList(Page page, TMsgDict TMsgDict) {
        return null;
    }

    @Override
    public int tMsgDictAdd(TMsgDict tMsgDict) {
        return 0;
    }
}
