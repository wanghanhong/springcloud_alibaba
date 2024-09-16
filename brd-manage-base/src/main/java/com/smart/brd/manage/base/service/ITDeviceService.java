package com.smart.brd.manage.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.entity.TDevice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;

import java.util.List;

/**
 * @author 
 */
public interface ITDeviceService extends IService<TDevice> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TDevice> tDeviceList(PageDomain page, TDevice entity);

    TDevice tDeviceAdd(TDevice entity);

    TDevice tDeviceUpdate(TDevice entity);

    int tDeviceDel(Long id);

    TDevice tDeviceDetail(TDevice entity);
    TDevice tdeviceDetailUrl(TDevice entity);

    /**------基本方法结束-----------------------------------------*/

    List<TDevice> queryDeviceList(TDevice entity);

    List<TDevice> findList(Long[] ids);
}
