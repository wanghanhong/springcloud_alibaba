package com.smart.card.manage.service;

import com.smart.card.manage.entity.TCardSms;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITCardSmsService extends IService<TCardSms> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TCardSms> tCardSmsList(PageDomain page,TCardSms entity);

    TCardSms tCardSmsAdd(TCardSms entity);

    TCardSms tCardSmsUpdate(TCardSms entity);

    int tCardSmsDel(Long id);

    TCardSms tCardSmsDetail(TCardSms entity);
    /**------基本方法结束-----------------------------------------*/

}
