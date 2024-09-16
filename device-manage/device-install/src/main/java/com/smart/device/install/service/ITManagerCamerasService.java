package com.smart.device.install.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.TManagerCameras;

import java.util.List;

/**
 * @author f
 */
public interface ITManagerCamerasService extends IService<TManagerCameras> {

    IPage<TManagerCameras> managerCamerasList(PageDomain page, TManagerCameras entity);
    /**
     * 摄像头添加
     *
     * @param entity
     * @return
     */
    TManagerCameras managerCamerasAdd(TManagerCameras entity);
    /**
     * 摄像头删除
     *
     * @param
     * @return
     */
    int managerCamerasDel(Long id);
    /**
     * 摄像头修改
     *
     * @param entity
     * @return
     */
    TManagerCameras managerCamerasUpdate(TManagerCameras entity);

    TManagerCameras selectCamerasByID(Long id);
    
    List<TManagerCameras> camerasListNoPage(TManagerCameras entity);
    //  根据IMEI SN号查询是否存在
    TManagerCameras getManagerCameras(TManagerCameras entity);

}
