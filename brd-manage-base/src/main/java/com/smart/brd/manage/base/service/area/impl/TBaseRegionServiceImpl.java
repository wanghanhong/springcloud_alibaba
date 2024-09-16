package com.smart.brd.manage.base.service.area.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.common.constant.BrdConstant;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import com.smart.brd.manage.base.entity.vo.BrdFieldVo;
import com.smart.brd.manage.base.mapper.area.TBaseRegionMapper;
import com.smart.brd.manage.base.service.area.ITBaseRegionService;
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
 * <p>
 * 建筑物 服务实现类
 * </p>
 *
 * @author cf
 * @since 2019-09-17
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TBaseRegionServiceImpl extends ServiceImpl<TBaseRegionMapper, TBaseRegion> implements ITBaseRegionService {

    @Resource
    private TBaseRegionMapper tBaseRegionMapper;

    @Override
    public List<TBaseRegion> selectRegions(String regionCode) {
        List<TBaseRegion> regions = new ArrayList<>();
        try {
            if (StringUtils.isNotBlank(regionCode)) {
                regions = tBaseRegionMapper.selectRegions(regionCode);
            } else {
                regions = tBaseRegionMapper.selectProvinces();
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
                List<TBaseRegion> regions = tBaseRegionMapper.mapRegions(regionCodes);
                map = regions.stream().collect(Collectors.toMap(TBaseRegion::getRegionCode,TBaseRegion::getRegionName));
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
        if (codes.endsWith(BrdConstant.COMMA)) {
            codes = codes.substring(0, codes.length() - 1);
        }
        return codes;
    }

    @Override
    public List<TBaseRegion> selectRegionsByCode(String province, String city, String county, String town) {
        List<TBaseRegion> regions = new ArrayList<>();
        try {
            regions = tBaseRegionMapper.selectRegionsByCode(province,city,county,town);
            return regions;
        } catch (Exception e) {
            log.error("查询区域失败！", e);
            return new ArrayList<>();
        }
    }

    @Override
    public String getRegionsByTDeptInfoVo(List<BrdFieldVo> list) {
        Map<String,String> map = new HashMap<>();
        for (int i=0;i<list.size();i++){
            BrdFieldVo vo = list.get(i);
            if(StringUtils.isNotBlank(vo.getProvince())){
                map.put(vo.getProvince(),vo.getProvince());
            }
            if(StringUtils.isNotBlank(vo.getCity())){
                map.put(vo.getCity(),vo.getCity());
            }
            if(StringUtils.isNotBlank(vo.getCounty())){
                map.put(vo.getCounty(),vo.getCounty());
            }
            if(StringUtils.isNotBlank(vo.getTown())){
                map.put(vo.getTown(),vo.getTown());
            }
        }
        String codes = "";
        codes = getCodesString(map, codes);
        return codes;
    }


}

