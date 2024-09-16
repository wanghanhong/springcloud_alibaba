package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TDeviceExt;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITDeviceExtService extends IService<TDeviceExt> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TDeviceExt> tDeviceExtList(PageDomain page,TDeviceExt entity);

    TDeviceExt tDeviceExtAdd(TDeviceExt entity);

    TDeviceExt tDeviceExtUpdate(TDeviceExt entity);

    int tDeviceExtDel(Long id);

    TDeviceExt tDeviceExtDetail(TDeviceExt entity);
    /**------基本方法结束-----------------------------------------*/

}
