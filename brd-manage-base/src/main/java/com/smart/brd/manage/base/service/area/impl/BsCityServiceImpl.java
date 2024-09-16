package com.smart.brd.manage.base.service.area.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.entity.area.BsCity;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import com.smart.brd.manage.base.mapper.area.BsCityMapper;
import com.smart.brd.manage.base.service.area.IBsCityService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author f
 */
@Service
public class BsCityServiceImpl extends ServiceImpl<BsCityMapper, BsCity> implements IBsCityService {

    @Resource
    private BsCityMapper bsCityMapper;

    @Override
    public List<TBaseRegion> queryCitysByCode(String code) {
        return bsCityMapper.queryCitysByCode(code);
    }

    @Override
    public List<TBaseRegion> Citys() {
        List<TBaseRegion> areas = queryCitysByCode("610000");
        return areas;
    }

    @Override
    public Map<String, String> mapCitys() {
        List<TBaseRegion> areas = queryCitysByCode("610000");
        Map<String,String> cityMap = areas.stream().collect(Collectors.toMap(TBaseRegion::getRegionCode,TBaseRegion::getRegionName));
        return cityMap;
    }
}
