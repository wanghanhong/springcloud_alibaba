package com.smart.brd.manage.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.brd.manage.base.entity.TBaseDict;
import com.smart.brd.manage.base.entity.TLivestockShed;
import com.smart.brd.manage.base.entity.TShedTransfer;
import com.smart.brd.manage.base.entity.vo.ShedTransVo;
import com.smart.brd.manage.base.entity.vo.StockBedVo;
import com.smart.common.core.page.PageDomain;

import java.util.List;

/**
 * @author 
 */
public interface ITShedTransferService extends IService<TShedTransfer> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TShedTransfer> tShedTransferList(PageDomain page,TShedTransfer entity);

    Boolean tShedTransferAdd(ShedTransVo entity);

    TShedTransfer tShedTransferUpdate(ShedTransVo entity);

    int tShedTransferDel(Long id);

    TShedTransfer tShedTransferDetail(TShedTransfer entity);
    /**------基本方法结束-----------------------------------------*/

    /**
     * 获取转栏原因
     * @return list
     */
    List<TBaseDict> getTrans();

    /**
     * 获取转栏原因
     * @return list
     */
    List<TLivestockShed> getSheds();

    /**
     * 获取转栏原因
     * @return list
     */
    List<StockBedVo> getBeds(Long shedId);
}
