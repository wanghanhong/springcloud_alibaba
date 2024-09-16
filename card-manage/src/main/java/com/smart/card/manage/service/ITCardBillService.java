package com.smart.card.manage.service;

import com.smart.card.manage.entity.TCardBill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITCardBillService extends IService<TCardBill> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TCardBill> tCardBillList(PageDomain page,TCardBill entity);

    TCardBill tCardBillAdd(TCardBill entity);

    TCardBill tCardBillUpdate(TCardBill entity);

    int tCardBillDel(Long id);

    TCardBill tCardBillDetail(TCardBill entity);
    /**------基本方法结束-----------------------------------------*/

}
