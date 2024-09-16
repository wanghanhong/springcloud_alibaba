package com.smart.card.manage.service;

import com.smart.card.manage.entity.TBaseDict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITBaseDictService extends IService<TBaseDict> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TBaseDict> tBaseDictList(PageDomain page,TBaseDict entity);

    TBaseDict tBaseDictAdd(TBaseDict entity);

    TBaseDict tBaseDictUpdate(TBaseDict entity);

    int tBaseDictDel(Long id);

    TBaseDict tBaseDictDetail(TBaseDict entity);
    /**------基本方法结束-----------------------------------------*/

}
