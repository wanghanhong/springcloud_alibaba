package com.smart.card.manage.service;

import com.smart.card.manage.entity.BsCity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface IBsCityService extends IService<BsCity> {

    /**------基本方法开始-----------------------------------------*/
    IPage<BsCity> bsCityList(PageDomain page,BsCity entity);

    BsCity bsCityAdd(BsCity entity);

    BsCity bsCityUpdate(BsCity entity);

    int bsCityDel(Long id);

    BsCity bsCityDetail(BsCity entity);
    /**------基本方法结束-----------------------------------------*/

}
