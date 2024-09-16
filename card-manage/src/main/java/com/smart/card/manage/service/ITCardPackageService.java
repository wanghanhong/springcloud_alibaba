package com.smart.card.manage.service;

import com.smart.card.manage.entity.TCardPackage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITCardPackageService extends IService<TCardPackage> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TCardPackage> tCardPackageList(PageDomain page,TCardPackage entity);

    TCardPackage tCardPackageAdd(TCardPackage entity);

    TCardPackage tCardPackageUpdate(TCardPackage entity);

    int tCardPackageDel(Long id);

    TCardPackage tCardPackageDetail(TCardPackage entity);
    /**------基本方法结束-----------------------------------------*/

}
