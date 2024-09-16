package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.vo.SdDeviceVo;
import com.smart.device.common.message.entity.THeartElectric;
import com.smart.device.message.data.entity.vo.HeartVo;
import com.smart.device.message.data.entity.vo.THeartElectricVo;

/**
 * @author
 */
public interface THeartElectricService extends IService<THeartElectric> {

    IPage<THeartElectric> electricList(Page page, HeartVo vo);

    int electricAdd(THeartElectric tHeartElectric);

    THeartElectricVo selectElectricLimit(HeartVo vo);

    SdDeviceVo selectElectricLast(Long deviceId);

    // 心跳消息来时，更改设备状态-2021-1-15
    void updateElectricStatusAll(Long imei,Long deviceId);

}
