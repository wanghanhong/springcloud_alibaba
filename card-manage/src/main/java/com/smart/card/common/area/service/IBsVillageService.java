package com.smart.card.common.area.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.card.common.area.entity.BsRegion;
import com.smart.card.common.area.entity.BsVillage;
import java.util.List;

/**
 * @author f
 */
public interface IBsVillageService extends IService<BsVillage> {

    List<BsRegion> queryVillagesByCode(String code);

}
