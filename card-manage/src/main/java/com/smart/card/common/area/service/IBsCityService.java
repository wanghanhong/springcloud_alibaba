package com.smart.card.common.area.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.card.common.area.entity.BsCity;
import com.smart.card.common.area.entity.BsRegion;
import java.util.List;
import java.util.Map;

public interface IBsCityService extends IService<BsCity> {

    List<BsRegion> queryCitysByCode(String code);

    List<BsRegion> Citys();

    Map<String,String> mapCitys();

}
