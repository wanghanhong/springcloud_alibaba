package com.smart.card.manage.service;

import com.smart.card.manage.entity.BsStreet;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface IBsStreetService extends IService<BsStreet> {

    /**------基本方法开始-----------------------------------------*/
    IPage<BsStreet> bsStreetList(PageDomain page,BsStreet entity);

    BsStreet bsStreetAdd(BsStreet entity);

    BsStreet bsStreetUpdate(BsStreet entity);

    int bsStreetDel(Long id);

    BsStreet bsStreetDetail(BsStreet entity);
    /**------基本方法结束-----------------------------------------*/

}
