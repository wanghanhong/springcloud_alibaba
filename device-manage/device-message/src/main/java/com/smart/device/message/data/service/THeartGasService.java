package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.message.entity.THeartGas;
import com.smart.device.message.data.entity.vo.HeartVo;

/**
 * @author
 */
public interface THeartGasService extends IService<THeartGas> {

    IPage<THeartGas> gasList(Page page, HeartVo vo);

    int gasAdd(THeartGas tHeartGas);

}
