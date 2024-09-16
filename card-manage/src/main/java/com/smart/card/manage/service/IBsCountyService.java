package com.smart.card.manage.service;

import com.smart.card.manage.entity.BsCounty;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface IBsCountyService extends IService<BsCounty> {

    /**------基本方法开始-----------------------------------------*/
    IPage<BsCounty> bsCountyList(PageDomain page,BsCounty entity);

    BsCounty bsCountyAdd(BsCounty entity);

    BsCounty bsCountyUpdate(BsCounty entity);

    int bsCountyDel(Long id);

    BsCounty bsCountyDetail(BsCounty entity);
    /**------基本方法结束-----------------------------------------*/

}
