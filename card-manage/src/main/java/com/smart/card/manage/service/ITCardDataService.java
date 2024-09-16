package com.smart.card.manage.service;

import com.smart.card.manage.entity.TCardData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITCardDataService extends IService<TCardData> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TCardData> tCardDataList(PageDomain page,TCardData entity);

    TCardData tCardDataAdd(TCardData entity);

    TCardData tCardDataUpdate(TCardData entity);

    int tCardDataDel(Long id);

    TCardData tCardDataDetail(TCardData entity);
    /**------基本方法结束-----------------------------------------*/

}
