package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TBrdBreeder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITBrdBreederService extends IService<TBrdBreeder> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TBrdBreeder> tBrdBreederList(PageDomain page,TBrdBreeder entity);

    TBrdBreeder tBrdBreederAdd(TBrdBreeder entity);

    TBrdBreeder tBrdBreederUpdate(TBrdBreeder entity);

    int tBrdBreederDel(Long id);

    TBrdBreeder tBrdBreederDetail(TBrdBreeder entity);
    /**------基本方法结束-----------------------------------------*/

}
