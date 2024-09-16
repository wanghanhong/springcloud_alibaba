package com.smart.brd.manage.base.service.area;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.brd.manage.base.entity.area.BsStreet;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import java.util.List;

/**
 * @author f
 */
public interface IBsStreetService extends IService<BsStreet> {

    List<TBaseRegion> queryStreetsByCode(String code);

}
