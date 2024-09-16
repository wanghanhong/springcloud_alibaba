package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.message.entity.THeartFirehost;
import com.smart.device.message.data.entity.vo.HeartVo;

/**
 * @author
 */
public interface THeartFirehostService extends IService<THeartFirehost> {

    IPage<THeartFirehost> firehostList(Page page, HeartVo vo);

    int firehostAdd(THeartFirehost tHeartFirehost);

}
