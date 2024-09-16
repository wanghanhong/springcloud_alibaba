package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TLivestockPurchase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITLivestockPurchaseService extends IService<TLivestockPurchase> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TLivestockPurchase> tLivestockPurchaseList(PageDomain page,TLivestockPurchase entity);

    TLivestockPurchase tLivestockPurchaseAdd(TLivestockPurchase entity);

    TLivestockPurchase tLivestockPurchaseUpdate(TLivestockPurchase entity);

    int tLivestockPurchaseDel(Long id);

    TLivestockPurchase tLivestockPurchaseDetail(TLivestockPurchase entity);
    /**------基本方法结束-----------------------------------------*/

}
