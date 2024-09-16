package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TVaccineRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITVaccineRecordService extends IService<TVaccineRecord> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TVaccineRecord> vaccineRecordList(PageDomain page, TVaccineRecord entity);

    TVaccineRecord vaccineRecordAdd(TVaccineRecord entity);

    TVaccineRecord vaccineRecordUpdate(TVaccineRecord entity);

    int vaccineRecordDel(Long id);

    TVaccineRecord vaccineRecordDetail(TVaccineRecord entity);
    /**------基本方法结束-----------------------------------------*/

}
