package com.smart.card.manage.service;

import com.smart.card.common.dict.entity.DictDto;
import com.smart.card.common.dict.entity.TBaseDict;
import com.smart.card.manage.entity.TCard;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.entity.vo.TCardVo;
import com.smart.common.core.page.PageDomain;
import com.xxl.job.core.biz.model.ReturnT;

import java.util.List;

/**
 * @author 
 */
public interface ITCardService extends IService<TCard> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TCardVo> tCardList(PageDomain page, TCard entity);

    TCard tCardAdd(TCard entity);

    TCard tCardUpdate(TCard entity);

    int tCardDel(Long id);

    TCard tCardDetail(TCard entity);

    TCard tCardRemain(TCard entity);

    Boolean updatePoolStatus(String acc, String pool);

    List<DictDto> tCardStatus();

    List<DictDto> tTags();

    List<DictDto> tPoolMember();
    /**------基本方法结束-----------------------------------------*/
    void refreshProduct(TCard card);
}
