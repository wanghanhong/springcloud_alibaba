package com.smart.card.manage.service;

import com.smart.card.manage.entity.TCardIdentity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITCardIdentityService extends IService<TCardIdentity> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TCardIdentity> tCardIdentityList(PageDomain page,TCardIdentity entity);

    TCardIdentity tCardIdentityAdd(TCardIdentity entity);

    TCardIdentity tCardIdentityUpdate(TCardIdentity entity);

    int tCardIdentityDel(Long id);

    TCardIdentity tCardIdentityDetail(TCardIdentity entity);
    /**------基本方法结束-----------------------------------------*/

}
