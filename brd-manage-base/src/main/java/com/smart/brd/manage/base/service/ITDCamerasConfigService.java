package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TDCamerasConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.entity.vo.DeviceVo;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITDCamerasConfigService extends IService<TDCamerasConfig> {

    /**------基本方法开始-----------------------------------------*/
    IPage<DeviceVo> tDCamerasConfigList(PageDomain page, TDCamerasConfig entity);

    TDCamerasConfig tDCamerasConfigAdd(TDCamerasConfig entity);

    TDCamerasConfig tDCamerasConfigUpdate(TDCamerasConfig entity);

    int tDCamerasConfigDel(Long id);

    TDCamerasConfig tDCamerasConfigDetail(TDCamerasConfig entity);
    /**------基本方法结束-----------------------------------------*/

}
