package com.smart.brd.manage.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.brd.manage.base.entity.TLivestockOut;
import com.smart.brd.manage.base.entity.TLivestockShedout;
import com.smart.brd.manage.base.entity.vo.ShedOutAllVo;
import com.smart.brd.manage.base.entity.vo.ShedOutVo;
import com.smart.common.core.page.PageDomain;

/**
 * @author junglelocal
 */
public interface ITLivestockShedoutService extends IService<TLivestockShedout> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TLivestockOut> tLivestockOutList(PageDomain page, ShedOutAllVo entity);

    IPage<ShedOutVo> tLivestockShedoutList(PageDomain page, ShedOutVo entity);

    TLivestockShedout tLivestockOutAdd(ShedOutAllVo entity);

    int tLivestockOutDel(Long id);

    ShedOutVo tLivestockOutDetail(Long shedoutId);
    /**------基本方法结束-----------------------------------------*/
}
