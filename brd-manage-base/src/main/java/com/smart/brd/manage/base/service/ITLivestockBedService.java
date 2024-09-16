package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.entity.vo.TLivestockBedVo;
import com.smart.common.core.domain.Result;
import com.smart.common.core.page.PageDomain;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 
 */
public interface ITLivestockBedService extends IService<TLivestockBed> {

    /**------基本方法开始-----------------------------------------*/
    IPage<BarnsVo> tLivestockBedList(PageDomain page,BarnsVo entity);

    TLivestockBed tLivestockBedAdd(TLivestockBed entity);

    TLivestockBed tLivestockBedUpdate(TLivestockBed entity);

    int tLivestockBedDel(Long id);

    TLivestockBed tLivestockBedDetail(TLivestockBed entity);

    /**------基本方法结束-----------------------------------------*/

    Result barnsAdd(BarnsVo entity);

    BarnsVo barnsUpdate(BarnsVo entity);

    BarnsVo barnsInfo(Long id);

    List<TLivestockBed> queryBedList(HttpServletRequest request,TLivestockBed entity);

    List<Tree> selectTree(TLivestockAnalysis tla);

    TLivestockBed quetyBed(Long fieldId,String shedName,String bedName);

    List<TLivestockBedVo> queryBreederList(HttpServletRequest request,TLivestockBedVo entity);
}
