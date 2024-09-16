package com.smart.card.common.dict.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.card.common.dict.entity.DictDto;
import com.smart.card.common.dict.entity.TBaseDict;
import com.smart.common.core.page.PageDomain;
import java.util.List;

/**
 * @author 
 */
public interface ITBaseDictService extends IService<TBaseDict> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TBaseDict> tBaseDictList(PageDomain page, TBaseDict entity);

    TBaseDict tBaseDictAdd(TBaseDict entity);

    TBaseDict tBaseDictUpdate(TBaseDict entity);

    int tBaseDictDel(Long id);

    TBaseDict tBaseDictDetail(TBaseDict entity);


    /**------基本方法结束-----------------------------------------*/

    List<DictDto> tByDict(String dictTypeId);


}
