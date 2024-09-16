package com.smart.card.manage.service;

import com.smart.card.manage.entity.TCardStatus;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITCardStatusService extends IService<TCardStatus> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TCardStatus> tCardStatusList(PageDomain page,TCardStatus entity);

    TCardStatus tCardStatusAdd(TCardStatus entity);

    TCardStatus tCardStatusUpdate(TCardStatus entity);

    int tCardStatusDel(Long id);

    TCardStatus tCardStatusDetail(TCardStatus entity);
    /**------基本方法结束-----------------------------------------*/

}
