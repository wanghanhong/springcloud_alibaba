package com.smart.card.manage.service;

import com.smart.card.manage.entity.TPoolPackageMemberUse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITPoolPackageMemberUseService extends IService<TPoolPackageMemberUse> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TPoolPackageMemberUse> tPoolPackageMemberUseList(PageDomain page,TPoolPackageMemberUse entity);

    TPoolPackageMemberUse tPoolPackageMemberUseAdd(TPoolPackageMemberUse entity);

    TPoolPackageMemberUse tPoolPackageMemberUseUpdate(TPoolPackageMemberUse entity);

    int tPoolPackageMemberUseDel(Long id);

    TPoolPackageMemberUse tPoolPackageMemberUseDetail(TPoolPackageMemberUse entity);
    /**------基本方法结束-----------------------------------------*/

}
