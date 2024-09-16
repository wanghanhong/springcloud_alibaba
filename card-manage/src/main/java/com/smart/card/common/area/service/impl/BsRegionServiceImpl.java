package com.smart.card.common.area.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.card.common.area.entity.BsRegion;
import com.smart.card.common.area.mapper.BsRegionMapper;
import com.smart.card.common.area.service.IBsRegionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cf
 * @since 2019-09-17
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BsRegionServiceImpl extends ServiceImpl<BsRegionMapper, BsRegion> implements IBsRegionService{

    @Resource
    private BsRegionMapper bsRegionMapper;

    @Override
    public List<BsRegion> selectRegions(String regionCode) {
        List<BsRegion> regions = new ArrayList<>();
        try {
            if (StringUtils.isNotBlank(regionCode)) {
                regions = bsRegionMapper.selectRegions(regionCode);
            } else {
                regions = bsRegionMapper.selectProvinces();
            }
            return regions;
        } catch (Exception e) {
            log.error("查询区域失败！", e);
            return new ArrayList<>();
        }
    }


    @Override
    public Map<String, String> mapRegions(String regionCodes) {
        Map<String, String> map = new HashMap<>();
        try {
            if(StringUtils.isNotBlank(regionCodes)){
                List<BsRegion> regions = bsRegionMapper.mapRegions(regionCodes);
                map = regions.stream().collect(Collectors.toMap(BsRegion::getRegionCode,BsRegion::getRegionName));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    private String getCodesString(Map<String, String> map, String codes) {
        for (String key : map.keySet()) {
            codes += ("'"+key + "',");
        }
        if (codes.endsWith(",")) {
            codes = codes.substring(0, codes.length() - 1);
        }
        return codes;
    }

    @Override
    public List<BsRegion> selectRegionsByCode(String province, String city, String county, String town) {
        List<BsRegion> regions = new ArrayList<>();
        try {
            regions = bsRegionMapper.selectRegionsByCode(province,city,county,town);
            return regions;
        } catch (Exception e) {
            log.error("查询区域失败！", e);
            return new ArrayList<>();
        }
    }


}

