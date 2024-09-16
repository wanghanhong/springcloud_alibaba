package com.smart.device.install.service.area;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.area.BsVillage;

import java.util.List;

/**
 * @author f
 */
public interface IBsVillageService extends IService<BsVillage> {

    List<TBaseRegion> queryVillagesByCode(String code);

}
