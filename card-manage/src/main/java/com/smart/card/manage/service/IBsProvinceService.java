package com.smart.card.manage.service;

import com.smart.card.manage.entity.BsProvince;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface IBsProvinceService extends IService<BsProvince> {

    /**------基本方法开始-----------------------------------------*/
    IPage<BsProvince> bsProvinceList(PageDomain page,BsProvince entity);

    BsProvince bsProvinceAdd(BsProvince entity);

    BsProvince bsProvinceUpdate(BsProvince entity);

    int bsProvinceDel(Long id);

    BsProvince bsProvinceDetail(BsProvince entity);
    /**------基本方法结束-----------------------------------------*/

}
