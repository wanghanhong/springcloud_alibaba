package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TRegion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITRegionService extends IService<TRegion> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TRegion> tRegionList(PageDomain page,TRegion entity);

    TRegion tRegionAdd(TRegion entity);

    TRegion tRegionUpdate(TRegion entity);

    int tRegionDel(Long id);

    TRegion tRegionDetail(TRegion entity);
    /**------基本方法结束-----------------------------------------*/

}
