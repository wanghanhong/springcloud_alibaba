package com.smart.card.common.area.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.card.common.area.entity.BsProvince;
import com.smart.card.common.area.entity.BsRegion;
import java.util.List;

/**
 * @author f
 */
public interface AreaService extends IService<BsProvince> {

    List<BsRegion> selectRegions(String code, String level);

}
