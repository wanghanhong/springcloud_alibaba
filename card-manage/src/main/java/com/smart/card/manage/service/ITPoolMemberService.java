package com.smart.card.manage.service;

import com.smart.card.manage.entity.TPoolMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITPoolMemberService extends IService<TPoolMember> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TPoolMember> tPoolMemberList(PageDomain page,TPoolMember entity);

    TPoolMember tPoolMemberAdd(TPoolMember entity);

    TPoolMember tPoolMemberUpdate(TPoolMember entity);

    int tPoolMemberDel(Long id);

    TPoolMember tPoolMemberDetail(TPoolMember entity);
    /**------基本方法结束-----------------------------------------*/

}
