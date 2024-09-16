package com.smart.card.manage.service;

import com.smart.card.manage.entity.TPoolProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITPoolProductService extends IService<TPoolProduct> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TPoolProduct> tPoolProductList(PageDomain page,TPoolProduct entity);

    TPoolProduct tPoolProductAdd(TPoolProduct entity);

    TPoolProduct tPoolProductUpdate(TPoolProduct entity);

    int tPoolProductDel(Long id);

    TPoolProduct tPoolProductDetail(TPoolProduct entity);
    /**------基本方法结束-----------------------------------------*/

}
