package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TDiseaseRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITDiseaseRecordService extends IService<TDiseaseRecord> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TDiseaseRecord> tDiseaseRecordList(PageDomain page,TDiseaseRecord entity);

    TDiseaseRecord tDiseaseRecordAdd(TDiseaseRecord entity);

    TDiseaseRecord tDiseaseRecordUpdate(TDiseaseRecord entity);

    int tDiseaseRecordDel(Long id);

    TDiseaseRecord tDiseaseRecordDetail(TDiseaseRecord entity);
    /**------基本方法结束-----------------------------------------*/

}
