package com.smart.device.install.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.TBaseCompany;

import java.util.List;

/**
 * @author f
 */
public interface ITBaseCompanyService extends IService<TBaseCompany> {

    List<TBaseCompany> selectCompanys(TBaseCompany vo);

    /**
     * 烟感列表
     *
     * @param entity
     * @return
     */
    IPage<TBaseCompany> baseCompanyList(PageDomain page, TBaseCompany entity);

    /**
     * 烟感添加
     *
     * @param entity
     * @return
     */
    TBaseCompany baseCompanyAdd(TBaseCompany entity);

    /**
     * 烟感删除
     *
     * @param
     * @return
     */
    int baseCompanyDel(Long id);

    /**
     * 烟感修改
     *
     * @param entity
     * @return
     */
    TBaseCompany baseCompanyUpdate(TBaseCompany entity);
    /**
     *  根据ID查询详情
     *
     * @param
     * @return
     */
    TBaseCompany selecBaseCompanyByWhree(TBaseCompany entity);

}
