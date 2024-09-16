package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TFeedAdditive;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITFeedAdditiveService extends IService<TFeedAdditive> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TFeedAdditive> tFeedAdditiveList(PageDomain page,TFeedAdditive entity);

    TFeedAdditive tFeedAdditiveAdd(TFeedAdditive entity);

    TFeedAdditive tFeedAdditiveUpdate(TFeedAdditive entity);

    int tFeedAdditiveDel(Long id);

    TFeedAdditive tFeedAdditiveDetail(TFeedAdditive entity);
    /**------基本方法结束-----------------------------------------*/

}
