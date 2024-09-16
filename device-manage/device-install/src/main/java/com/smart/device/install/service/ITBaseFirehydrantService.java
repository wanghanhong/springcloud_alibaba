package com.smart.device.install.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.TBaseFirehydrant;

/**
 * @author f
 */
public interface ITBaseFirehydrantService extends IService<TBaseFirehydrant> {

    /**
     * 烟感列表
     *
     * @param entity
     * @return
     */
    IPage<TBaseFirehydrant> baseFirehydrantList(PageDomain page, TBaseFirehydrant entity);

    /**
     * 烟感添加
     *
     * @param entity
     * @return
     */
    TBaseFirehydrant baseFirehydrantAdd(TBaseFirehydrant entity);

    /**
     * 烟感删除
     *
     * @param
     * @return
     */
    int baseFirehydrantDel(TBaseFirehydrant entity);

    /**
     * 烟感修改
     *
     * @param entity
     * @return
     */
    TBaseFirehydrant baseFirehydrantUpdate(TBaseFirehydrant entity);
    /**
     *  根据ID查询详情
     *
     * @param
     * @return
     */
    TBaseFirehydrant selectBaseFirehydrantByID(Long id);

}
