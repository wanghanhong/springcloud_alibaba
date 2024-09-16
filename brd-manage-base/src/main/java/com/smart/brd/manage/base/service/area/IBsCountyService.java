package com.smart.brd.manage.base.service.area;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.brd.manage.base.entity.area.BsCounty;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import java.util.List;

/**
 * @author f
 */
public interface IBsCountyService extends IService<BsCounty> {

    List<TBaseRegion> queryCountysByCode(String code);

}
