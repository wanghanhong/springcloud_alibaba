package com.smart.card.manage.service;

import com.smart.card.manage.entity.TPackage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITPackageService extends IService<TPackage> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TPackage> tPackageList(PageDomain page,TPackage entity);

    TPackage tPackageAdd(TPackage entity);

    TPackage tPackageUpdate(TPackage entity);

    int tPackageDel(Long id);

    TPackage tPackageDetail(TPackage entity);
    /**------基本方法结束-----------------------------------------*/

}
