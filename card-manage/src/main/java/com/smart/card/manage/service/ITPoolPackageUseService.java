package com.smart.card.manage.service;

import com.smart.card.manage.entity.TPoolPackageUse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITPoolPackageUseService extends IService<TPoolPackageUse> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TPoolPackageUse> tPoolPackageUseList(PageDomain page,TPoolPackageUse entity);

    TPoolPackageUse tPoolPackageUseAdd(TPoolPackageUse entity);

    TPoolPackageUse tPoolPackageUseUpdate(TPoolPackageUse entity);

    int tPoolPackageUseDel(Long id);

    TPoolPackageUse tPoolPackageUseDetail(TPoolPackageUse entity);
    /**------基本方法结束-----------------------------------------*/

}
