package com.smart.card.manage.service;

import com.smart.card.manage.entity.TProductSub;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITProductSubService extends IService<TProductSub> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TProductSub> tProductSubList(PageDomain page,TProductSub entity);

    TProductSub tProductSubAdd(TProductSub entity);

    TProductSub tProductSubUpdate(TProductSub entity);

    int tProductSubDel(Long id);

    TProductSub tProductSubDetail(TProductSub entity);
    /**------基本方法结束-----------------------------------------*/

}
