package com.smart.card.common.area.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.card.common.area.entity.BsCounty;
import com.smart.card.common.area.entity.BsRegion;
import java.util.List;

/**
 * @author f
 */
public interface IBsCountyService extends IService<BsCounty> {

    List<BsRegion> queryCountysByCode(String code);

}
