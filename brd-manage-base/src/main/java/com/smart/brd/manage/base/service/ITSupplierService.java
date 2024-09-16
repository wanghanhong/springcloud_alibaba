package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TSupplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITSupplierService extends IService<TSupplier> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TSupplier> tSupplierList(PageDomain page,TSupplier entity);

    TSupplier tSupplierAdd(TSupplier entity);

    TSupplier tSupplierUpdate(TSupplier entity);

    int tSupplierDel(Long id);

    TSupplier tSupplierDetail(TSupplier entity);
    /**------基本方法结束-----------------------------------------*/

}
