package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TDiseaseTreat;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.entity.TLivestockAnalysis;
import com.smart.brd.manage.base.entity.vo.LivestockInfo;
import com.smart.common.core.domain.Result;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITDiseaseTreatService extends IService<TDiseaseTreat> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TDiseaseTreat> tDiseaseTreatList(PageDomain page,TDiseaseTreat entity);

    TDiseaseTreat tDiseaseTreatAdd(TDiseaseTreat entity);

    TDiseaseTreat tDiseaseTreatUpdate(TDiseaseTreat entity);

    int tDiseaseTreatDel(Long id);

    TDiseaseTreat tDiseaseTreatDetail(TDiseaseTreat entity);

    LivestockInfo liveStockInfo(String deviceCode);

    TDiseaseTreat diseaseTreatInfo(Long id);

    void updateDeath(TDiseaseTreat entity);

    void addTreatRecord(TDiseaseTreat entity);

    int getTreatNum(TLivestockAnalysis tLivestockAnalysis);
    /**------基本方法结束-----------------------------------------*/

}
