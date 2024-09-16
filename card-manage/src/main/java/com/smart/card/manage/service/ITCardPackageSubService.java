package com.smart.card.manage.service;

import com.smart.card.manage.entity.TCardPackageSub;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITCardPackageSubService extends IService<TCardPackageSub> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TCardPackageSub> tCardPackageSubList(PageDomain page,TCardPackageSub entity);

    TCardPackageSub tCardPackageSubAdd(TCardPackageSub entity);

    TCardPackageSub tCardPackageSubUpdate(TCardPackageSub entity);

    int tCardPackageSubDel(Long id);

    TCardPackageSub tCardPackageSubDetail(TCardPackageSub entity);
    /**------基本方法结束-----------------------------------------*/

}
