package com.smart.brd.manage.base.service.area;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.brd.manage.base.entity.area.BsProvince;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import java.util.List;

/**
 * @author f
 */
public interface IBsProvinceService extends IService<BsProvince> {

    List<TBaseRegion> queryProvincesByCode(String code);

}
