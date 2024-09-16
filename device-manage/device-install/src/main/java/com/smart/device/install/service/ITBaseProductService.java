package com.smart.device.install.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.TBaseProduct;

/**
 * @author f
 */
public interface ITBaseProductService extends IService<TBaseProduct> {

    /**
     * 烟感列表
     *
     * @param entity
     * @return
     */
    IPage<TBaseProduct> baseProductList(PageDomain page, TBaseProduct entity);

    /**
     * 烟感添加
     *
     * @param entity
     * @return
     */
    TBaseProduct baseProductAdd(TBaseProduct entity);

    /**
     * 烟感删除
     *
     * @param
     * @return
     */
    int baseProductDel(Long id);

    /**
     * 烟感修改
     *
     * @param entity
     * @return
     */
    TBaseProduct baseProductUpdate(TBaseProduct entity);
    /**
     *  根据ID查询详情
     *
     * @param
     * @return
     */
    TBaseProduct selectBaseProductByID(Long id);

}
