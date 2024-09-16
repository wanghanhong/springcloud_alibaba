package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TEdition;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITEditionService extends IService<TEdition> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TEdition> tEditionList(PageDomain page, TEdition entity);

    TEdition tEditionAdd(TEdition entity);

    TEdition tEditionUpdate(TEdition entity);

    int tEditionDel(Long id);

    TEdition tEditionDetail(TEdition entity);
    /**------基本方法结束-----------------------------------------*/

}
