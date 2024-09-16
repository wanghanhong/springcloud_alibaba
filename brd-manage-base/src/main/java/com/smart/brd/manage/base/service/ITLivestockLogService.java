package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TLivestockLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITLivestockLogService extends IService<TLivestockLog> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TLivestockLog> tLivestockLogList(PageDomain page,TLivestockLog entity);

    TLivestockLog tLivestockLogAdd(TLivestockLog entity);

    TLivestockLog tLivestockLogUpdate(TLivestockLog entity);

    int tLivestockLogDel(Long id);

    TLivestockLog tLivestockLogDetail(TLivestockLog entity);
    /**------基本方法结束-----------------------------------------*/

}
