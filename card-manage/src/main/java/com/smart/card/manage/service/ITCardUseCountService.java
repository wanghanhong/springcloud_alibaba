package com.smart.card.manage.service;

import com.smart.card.manage.entity.TCardUseCount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITCardUseCountService extends IService<TCardUseCount> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TCardUseCount> tCardUseCountList(PageDomain page,TCardUseCount entity);

    TCardUseCount tCardUseCountAdd(TCardUseCount entity);

    TCardUseCount tCardUseCountUpdate(TCardUseCount entity);

    int tCardUseCountDel(Long id);

    TCardUseCount tCardUseCountDetail(TCardUseCount entity);
    /**------基本方法结束-----------------------------------------*/

}
