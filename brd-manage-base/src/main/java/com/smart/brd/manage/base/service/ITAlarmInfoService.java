package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TAlarmInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.entity.TLivestockAnalysis;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITAlarmInfoService extends IService<TAlarmInfo> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TAlarmInfo> tAlarmInfoList(PageDomain page,TAlarmInfo entity);

    TAlarmInfo tAlarmInfoAdd(TAlarmInfo entity);

    TAlarmInfo tAlarmInfoUpdate(TAlarmInfo entity);

    int tAlarmInfoDel(Long id);

    TAlarmInfo tAlarmInfoDetail(TAlarmInfo entity);


    /**------基本方法结束-----------------------------------------*/

    int getUnprocessedNum(TLivestockAnalysis tLivestockAnalysis);

}
