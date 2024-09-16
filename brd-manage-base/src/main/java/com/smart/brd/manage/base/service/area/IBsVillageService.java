package com.smart.brd.manage.base.service.area;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.brd.manage.base.entity.area.BsVillage;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import java.util.List;

/**
 * @author f
 */
public interface IBsVillageService extends IService<BsVillage> {

    List<TBaseRegion> queryVillagesByCode(String code);

}
