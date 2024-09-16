package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TVaccinePrevention;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITVaccinePreventionService extends IService<TVaccinePrevention> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TVaccinePrevention> tVaccinePreventionList(PageDomain page,TVaccinePrevention entity);

    TVaccinePrevention tVaccinePreventionAdd(TVaccinePrevention entity);

    TVaccinePrevention tVaccinePreventionUpdate(TVaccinePrevention entity);

    int tVaccinePreventionDel(Long id);

    TVaccinePrevention tVaccinePreventionDetail(TVaccinePrevention entity);
    /**------基本方法结束-----------------------------------------*/

}
