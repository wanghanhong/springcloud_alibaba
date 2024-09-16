package com.smart.card.manage.service;

import com.smart.card.manage.entity.BsVillage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author 
 */
public interface IBsVillageService extends IService<BsVillage> {

    /**------基本方法开始-----------------------------------------*/
    IPage<BsVillage> bsVillageList(PageDomain page,BsVillage entity);

    BsVillage bsVillageAdd(BsVillage entity);

    BsVillage bsVillageUpdate(BsVillage entity);

    int bsVillageDel(Long id);

    BsVillage bsVillageDetail(BsVillage entity);
    /**------基本方法结束-----------------------------------------*/

}
