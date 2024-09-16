package com.smart.device.install.service.area;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.area.BsCounty;

import java.util.List;

/**
 * @author f
 */
public interface IBsCountyService extends IService<BsCounty> {

    List<TBaseRegion> queryCountysByCode(String code);

}
