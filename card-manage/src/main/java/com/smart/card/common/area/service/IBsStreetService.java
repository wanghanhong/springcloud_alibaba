package com.smart.card.common.area.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.card.common.area.entity.BsRegion;
import com.smart.card.common.area.entity.BsStreet;
import java.util.List;

/**
 * @author f
 */
public interface IBsStreetService extends IService<BsStreet> {

    List<BsRegion> queryStreetsByCode(String code);

}
