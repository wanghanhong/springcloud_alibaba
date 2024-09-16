package com.smart.card.manage.service;

import com.smart.card.common.dict.entity.DictDto;
import com.smart.card.manage.entity.TCardHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

import java.util.List;

/**
 * @author 
 */
public interface ITCardHistoryService extends IService<TCardHistory> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TCardHistory> tCardHistoryList(PageDomain page,TCardHistory entity);

    TCardHistory tCardHistoryAdd(TCardHistory entity);

    TCardHistory tCardHistoryUpdate(TCardHistory entity);

    int tCardHistoryDel(Long id);

    TCardHistory tCardHistoryDetail(TCardHistory entity);

    List<DictDto> tHisType();

    List<DictDto> tHisStatus();

    List<DictDto> tHisSource();
    /**------基本方法结束-----------------------------------------*/

}
