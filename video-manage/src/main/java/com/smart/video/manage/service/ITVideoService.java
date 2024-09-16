package com.smart.video.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.video.manage.entity.TVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;

import java.util.List;

/**
 * @author 
 */
public interface ITVideoService extends IService<TVideo> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TVideo> tDeviceList(PageDomain page, TVideo entity);

    TVideo tDeviceAdd(TVideo entity);

    TVideo tDeviceUpdate(TVideo entity);

    int tDeviceDel(Long id);

    TVideo tDeviceDetail(TVideo entity);

    /**------基本方法结束-----------------------------------------*/

    List<TVideo> findList(Long[] ids);
}
