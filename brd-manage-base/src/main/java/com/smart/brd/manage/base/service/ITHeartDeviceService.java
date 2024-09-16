package com.smart.brd.manage.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.brd.manage.base.entity.THeartDevice;
import com.smart.brd.manage.base.screen.entity.ScreenLineVo;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITHeartDeviceService extends IService<THeartDevice> {

    /**------基本方法开始-----------------------------------------*/
    IPage<THeartDevice> tHeartDeviceList(PageDomain page, THeartDevice entity);

    THeartDevice tHeartDeviceAdd(THeartDevice entity);

    THeartDevice tHeartDeviceUpdate(THeartDevice entity);

    int tHeartDeviceDel(Long id);

    THeartDevice tHeartDeviceDetail(THeartDevice entity);

    ScreenLineVo temperatureCurve(String deviceCode);
    /**------基本方法结束-----------------------------------------*/

}
