package com.smart.card.manage.service;

import com.smart.card.manage.entity.TPackageSub;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITPackageSubService extends IService<TPackageSub> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TPackageSub> tPackageSubList(PageDomain page,TPackageSub entity);

    TPackageSub tPackageSubAdd(TPackageSub entity);

    TPackageSub tPackageSubUpdate(TPackageSub entity);

    int tPackageSubDel(Long id);

    TPackageSub tPackageSubDetail(TPackageSub entity);
    /**------基本方法结束-----------------------------------------*/

}
