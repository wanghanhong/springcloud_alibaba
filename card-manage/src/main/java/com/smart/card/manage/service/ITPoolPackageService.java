package com.smart.card.manage.service;

import com.smart.card.manage.entity.TPoolPackage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITPoolPackageService extends IService<TPoolPackage> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TPoolPackage> tPoolPackageList(PageDomain page,TPoolPackage entity);

    TPoolPackage tPoolPackageAdd(TPoolPackage entity);

    TPoolPackage tPoolPackageUpdate(TPoolPackage entity);

    int tPoolPackageDel(Long id);

    TPoolPackage tPoolPackageDetail(TPoolPackage entity);
    /**------基本方法结束-----------------------------------------*/

}
