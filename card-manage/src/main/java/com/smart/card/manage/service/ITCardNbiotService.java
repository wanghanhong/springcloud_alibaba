package com.smart.card.manage.service;

import com.smart.card.manage.entity.TCardNbiot;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITCardNbiotService extends IService<TCardNbiot> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TCardNbiot> tCardNbiotList(PageDomain page,TCardNbiot entity);

    TCardNbiot tCardNbiotAdd(TCardNbiot entity);

    TCardNbiot tCardNbiotUpdate(TCardNbiot entity);

    int tCardNbiotDel(Long id);

    TCardNbiot tCardNbiotDetail(TCardNbiot entity);
    /**------基本方法结束-----------------------------------------*/

}
