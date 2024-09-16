package com.smart.card.manage.service;

import com.smart.card.manage.entity.TCardProductSub;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITCardProductSubService extends IService<TCardProductSub> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TCardProductSub> tCardProductSubList(PageDomain page,TCardProductSub entity);

    TCardProductSub tCardProductSubAdd(TCardProductSub entity);

    TCardProductSub tCardProductSubUpdate(TCardProductSub entity);

    int tCardProductSubDel(Long id);

    TCardProductSub tCardProductSubDetail(TCardProductSub entity);
    /**------基本方法结束-----------------------------------------*/

}
