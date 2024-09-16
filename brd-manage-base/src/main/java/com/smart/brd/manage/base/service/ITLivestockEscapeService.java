package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TLivestockEscape;
import com.smart.brd.manage.base.entity.TLivestockEscape;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.entity.dto.EscapeDto;
import com.smart.brd.manage.base.entity.vo.EscapeVo;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITLivestockEscapeService extends IService<TLivestockEscape> {

    /**------基本方法开始-----------------------------------------*/
    IPage<EscapeDto> tLivestockEscapeList(PageDomain page, EscapeVo entity);

    TLivestockEscape tLivestockEscapeAdd(EscapeVo entity);

    TLivestockEscape tLivestockEscapeUpdate(EscapeVo entity);

    int tLivestockEscapeDel(Long id);

    EscapeDto tLivestockEscapeDetail(EscapeVo entity);
    /**------基本方法结束-----------------------------------------*/

    EscapeVo queryEscapeAge(EscapeVo entity);


}
