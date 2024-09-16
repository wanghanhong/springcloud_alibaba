package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.message.entity.TMsgDict;

/**
 * @author
 */
public interface TMsgDictService extends IService<TMsgDict> {

    IPage<TMsgDict> tMsgDictList(Page page, TMsgDict TMsgDict);

    int tMsgDictAdd(TMsgDict tMsgDict);


}
