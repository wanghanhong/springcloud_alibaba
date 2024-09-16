package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.vo.SdDeviceVo;
import com.smart.device.common.message.entity.THeartWaterpress;
import com.smart.device.message.data.entity.vo.HeartVo;
import com.smart.device.message.data.entity.vo.THeartWaterpressVo;

/**
 * @author
 */
public interface THeartWaterpressService extends IService<THeartWaterpress> {

    IPage<THeartWaterpress> waterpressList(Page page, HeartVo vo);

    Long waterpressAdd(THeartWaterpress tHeartWaterpress);

    THeartWaterpressVo selectWaterpressLimit(HeartVo vo);

    SdDeviceVo selectWaterpressLast(Long deviceId);

    // 心跳消息来时，更改设备状态-2021-1-15
    void updateWaterpressStatusAll(Long imei,Long deviceId);

}
