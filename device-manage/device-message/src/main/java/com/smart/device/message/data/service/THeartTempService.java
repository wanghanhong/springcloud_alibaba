package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.message.entity.THeartTemp;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.message.data.entity.vo.HeartVo;

/**
 * @author jungle
 */
public interface THeartTempService extends IService<THeartTemp> {

    IPage<THeartTemp> tempList(Page page, AlarmVo vo);

    int tempAdd(THeartTemp tHeartTemp);

    THeartTemp selectTempLimit(HeartVo vo);
}
