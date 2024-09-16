package com.smart.card.manage.service;

import com.smart.card.manage.entity.TCardVoice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITCardVoiceService extends IService<TCardVoice> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TCardVoice> tCardVoiceList(PageDomain page,TCardVoice entity);

    TCardVoice tCardVoiceAdd(TCardVoice entity);

    TCardVoice tCardVoiceUpdate(TCardVoice entity);

    int tCardVoiceDel(Long id);

    TCardVoice tCardVoiceDetail(TCardVoice entity);
    /**------基本方法结束-----------------------------------------*/

}
