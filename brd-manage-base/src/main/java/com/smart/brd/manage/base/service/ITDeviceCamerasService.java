package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TDeviceCameras;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITDeviceCamerasService extends IService<TDeviceCameras> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TDeviceCameras> tDeviceCamerasList(PageDomain page,TDeviceCameras entity);

    TDeviceCameras tDeviceCamerasAdd(TDeviceCameras entity);

    TDeviceCameras tDeviceCamerasUpdate(TDeviceCameras entity);

    int tDeviceCamerasDel(Long id);

    TDeviceCameras tDeviceCamerasDetail(TDeviceCameras entity);
    /**------基本方法结束-----------------------------------------*/

}
