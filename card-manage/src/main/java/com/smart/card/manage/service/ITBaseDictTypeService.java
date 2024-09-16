package com.smart.card.manage.service;

import com.smart.card.manage.entity.TBaseDictType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITBaseDictTypeService extends IService<TBaseDictType> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TBaseDictType> tBaseDictTypeList(PageDomain page,TBaseDictType entity);

    TBaseDictType tBaseDictTypeAdd(TBaseDictType entity);

    TBaseDictType tBaseDictTypeUpdate(TBaseDictType entity);

    int tBaseDictTypeDel(Long id);

    TBaseDictType tBaseDictTypeDetail(TBaseDictType entity);
    /**------基本方法结束-----------------------------------------*/

}
