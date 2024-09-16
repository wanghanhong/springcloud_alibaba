package com.smart.card.manage.service;

import com.smart.card.manage.entity.TCardProductData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.entity.vo.TCardProductDataDto;
import com.smart.card.manage.entity.vo.TCardVo;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITCardProductDataService extends IService<TCardProductData> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TCardProductDataDto> tCardProductDataList(PageDomain page, TCardVo entity);

    TCardProductData tCardProductDataAdd(TCardProductData entity);

    TCardProductData tCardProductDataUpdate(TCardProductData entity);

    int tCardProductDataDel(Long id);

    TCardProductData tCardProductDataDetail(TCardProductData entity);
    /**------基本方法结束-----------------------------------------*/

}
