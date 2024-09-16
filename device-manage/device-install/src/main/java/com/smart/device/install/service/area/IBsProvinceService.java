package com.smart.device.install.service.area;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.area.BsProvince;

import java.util.List;

/**
 * @author f
 */
public interface IBsProvinceService extends IService<BsProvince> {

    List<TBaseRegion> queryProvincesByCode(String code);

}
