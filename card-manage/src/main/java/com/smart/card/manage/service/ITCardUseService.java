package com.smart.card.manage.service;

import com.smart.card.common.dict.entity.DictDto;
import com.smart.card.manage.entity.TCardUse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;
import com.xxl.job.core.biz.model.ReturnT;

import java.util.List;

/**
 * @author 
 */
public interface ITCardUseService extends IService<TCardUse> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TCardUse> tCardUseList(PageDomain page,TCardUse entity);

    TCardUse tCardUseAdd(TCardUse entity);

    TCardUse tCardUseUpdate(TCardUse entity);

    int tCardUseDel(Long id);

    TCardUse tCardUseDetail(TCardUse entity);

    List<DictDto> tCardBills();
    /**------基本方法结束-----------------------------------------*/
    ReturnT<String> refreshCardUse(String acc, String date);
}
