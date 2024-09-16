package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.vo.SdDeviceVo;
import com.smart.device.common.message.entity.THeartSmoke;
import com.smart.device.message.data.entity.vo.HeartVo;
import com.smart.device.message.data.entity.vo.THeartSmokeVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author
 */
public interface THeartSmokeService extends IService<THeartSmoke> {

    IPage<THeartSmoke> smokeList(Page page, HeartVo vo);

    int smokeAdd(THeartSmoke tHeartSmoke);

    THeartSmokeVo selectSmokeLimit(HeartVo vo);

    SdDeviceVo selectSmokeLast(Long deviceId);

    // 心跳消息来时，更改设备状态-2021-1-15
    void updateSmokeStatusAll(Long imei,Long deviceId);


}
