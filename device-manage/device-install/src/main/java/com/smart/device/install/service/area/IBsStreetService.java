package com.smart.device.install.service.area;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.area.BsStreet;

import java.util.List;

/**
 * @author f
 */
public interface IBsStreetService extends IService<BsStreet> {

    List<TBaseRegion> queryStreetsByCode(String code);

}
