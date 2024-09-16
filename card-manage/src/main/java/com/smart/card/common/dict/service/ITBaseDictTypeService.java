package com.smart.card.common.dict.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.card.common.dict.entity.TBaseDictType;
import com.smart.common.core.domain.Result;
import com.smart.common.core.page.PageDomain;
import java.util.List;

/**
 * @author 
 */
public interface ITBaseDictTypeService extends IService<TBaseDictType> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TBaseDictType> tBaseDictTypeList(PageDomain page, TBaseDictType entity);

    Result tBaseDictTypeAdd(TBaseDictType entity);

    TBaseDictType tBaseDictTypeUpdate(TBaseDictType entity);

    int tBaseDictTypeDel(Long id);

    TBaseDictType tBaseDictTypeDetail(TBaseDictType entity);

    /**------基本方法结束-----------------------------------------*/

    List<TBaseDictType> findAll();
}
