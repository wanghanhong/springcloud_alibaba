package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TFeed;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITFeedService extends IService<TFeed> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TFeed> tFeedList(PageDomain page,TFeed entity);

    TFeed tFeedAdd(TFeed entity);

    TFeed tFeedUpdate(TFeed entity);

    int tFeedDel(Long id);

    TFeed tFeedDetail(TFeed entity);
    /**------基本方法结束-----------------------------------------*/

}
