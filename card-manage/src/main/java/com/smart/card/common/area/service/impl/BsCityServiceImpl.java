package com.smart.card.common.area.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.card.common.area.entity.BsCity;
import com.smart.card.common.area.entity.BsRegion;
import com.smart.card.common.area.mapper.BsCityMapper;
import com.smart.card.common.area.service.IBsCityService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author f
 */
@Service
public class BsCityServiceImpl extends ServiceImpl<BsCityMapper, BsCity> implements IBsCityService {

    @Resource
    private BsCityMapper bsCityMapper;

    @Override
    public List<BsRegion> queryCitysByCode(String code) {
        return bsCityMapper.queryCitysByCode(code);
    }

    @Override
    public List<BsRegion> Citys() {
        List<BsRegion> areas = queryCitysByCode("610000");
        return areas;
    }

    @Override
    public Map<String, String> mapCitys() {
        List<BsRegion> areas = queryCitysByCode("610000");
        Map<String,String> cityMap = areas.stream().collect(Collectors.toMap(BsRegion::getRegionCode,BsRegion::getRegionName));
        return cityMap;
    }
}
