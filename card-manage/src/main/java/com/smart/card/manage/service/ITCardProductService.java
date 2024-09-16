package com.smart.card.manage.service;

import com.smart.card.manage.entity.TCardProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITCardProductService extends IService<TCardProduct> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TCardProduct> tCardProductList(PageDomain page,TCardProduct entity);

    TCardProduct tCardProductAdd(TCardProduct entity);

    TCardProduct tCardProductUpdate(TCardProduct entity);

    int tCardProductDel(Long id);

    TCardProduct tCardProductDetail(TCardProduct entity);
    /**------基本方法结束-----------------------------------------*/

}
