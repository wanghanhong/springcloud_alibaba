package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TLivestockDead;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.entity.vo.DeadVo;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface ITLivestockDeadService extends IService<TLivestockDead> {

    /**------基本方法开始-----------------------------------------*/
    IPage<DeadVo> tLivestockDeadList(PageDomain page,DeadVo entity);

    TLivestockDead tLivestockDeadAdd(DeadVo entity);

    TLivestockDead tLivestockDeadUpdate(DeadVo entity);

    int tLivestockDeadDel(Long id);

    DeadVo tLivestockDeadDetail(DeadVo entity);
    /**------基本方法结束-----------------------------------------*/

}
