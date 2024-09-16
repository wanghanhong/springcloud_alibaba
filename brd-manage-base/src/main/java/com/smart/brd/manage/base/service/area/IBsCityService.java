package com.smart.brd.manage.base.service.area;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.brd.manage.base.entity.area.BsCity;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import java.util.List;
import java.util.Map;

public interface IBsCityService extends IService<BsCity> {

    List<TBaseRegion> queryCitysByCode(String code);

    List<TBaseRegion> Citys();

    Map<String,String> mapCitys();

}
