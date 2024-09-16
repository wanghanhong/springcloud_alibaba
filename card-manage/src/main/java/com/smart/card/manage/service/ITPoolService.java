package com.smart.card.manage.service;

import com.smart.card.manage.entity.TPool;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.entity.TPoolHistory;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITPoolService extends IService<TPool> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TPool> tPoolList(PageDomain page,TPool entity);

    IPage<TPoolHistory> tPoolHisList(PageDomain page, TPoolHistory vo);

    TPool tPoolAdd(TPool entity);

    TPool tPoolUpdate(TPool entity);

    int tPoolDel(Long id);

    TPool tPoolDetail(TPool entity);
    /**------基本方法结束-----------------------------------------*/

}
