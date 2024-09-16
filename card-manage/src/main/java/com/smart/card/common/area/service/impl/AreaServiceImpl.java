package com.smart.card.common.area.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.card.common.area.entity.BsProvince;
import com.smart.card.common.area.entity.BsRegion;
import com.smart.card.common.area.mapper.BsProvinceMapper;
import com.smart.card.common.area.service.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author f
 */
@Service
public class AreaServiceImpl extends ServiceImpl<BsProvinceMapper, BsProvince> implements AreaService {

    @Resource
    private IBsProvinceService bsProvinceService;
    @Resource
    private IBsCityService bsCityService;
    @Resource
    private IBsCountyService bsCountyService;
    @Resource
    private IBsStreetService bsStreetService;
    @Resource
    private IBsVillageService bsVillageService;

    @Override
    public List<BsRegion> selectRegions(String code, String level){
        List<BsRegion> list = new ArrayList<>();
        if(level != null){
            if("1".equals(level)){
                list = bsProvinceService.queryProvincesByCode(code);
            }else if("2".equals(level)){
                list = bsCityService.queryCitysByCode(code);
            }else if("3".equals(level)){
                list = bsCountyService.queryCountysByCode(code);
            }else if("4".equals(level)){
                list = bsStreetService.queryStreetsByCode(code);
            }else if("5".equals(level)){
                list = bsVillageService.queryVillagesByCode(code);
            }
        }else{
            list = bsProvinceService.queryProvincesByCode(code);
        }
        return list;
    }
}
