package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TLivestockBed;
import com.smart.brd.manage.base.entity.TLivestockShed;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author 
 */
public interface ITLivestockShedService extends IService<TLivestockShed> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TLivestockShed> tLivestockShedList(PageDomain page,TLivestockShed entity);

    TLivestockShed tLivestockShedAdd(TLivestockShed entity);

    TLivestockShed tLivestockShedUpdate(TLivestockShed entity);

    int tLivestockShedDel(Long id);

    TLivestockShed tLivestockShedDetail(TLivestockShed entity);

    /**------基本方法结束-----------------------------------------*/

    List<TLivestockShed> queryShedList(TLivestockShed entity);

    TLivestockShed quetyShed(Long fieldId, String shedName);
}
