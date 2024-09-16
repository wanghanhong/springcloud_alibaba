package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.message.entity.THeartLiquidlevel;
import com.smart.device.message.data.entity.vo.HeartVo;

/**
 * @author
 */
public interface THeartLiquidlevelService extends IService<THeartLiquidlevel> {

    IPage<THeartLiquidlevel> liquidlevelList(Page page, HeartVo vo);

    int liquidlevelAdd(THeartLiquidlevel tHeartLiquidlevel);

}
