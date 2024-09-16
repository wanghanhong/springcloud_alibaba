package com.smart.card.manage.service;

import com.smart.card.manage.entity.TProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITProductService extends IService<TProduct> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TProduct> tProductList(PageDomain page,TProduct entity);

    TProduct tProductAdd(TProduct entity);

    TProduct tProductUpdate(TProduct entity);

    int tProductDel(Long id);

    TProduct tProductDetail(TProduct entity);
    /**------基本方法结束-----------------------------------------*/

}
