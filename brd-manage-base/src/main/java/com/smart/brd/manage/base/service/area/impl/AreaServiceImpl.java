package com.smart.brd.manage.base.service.area.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.common.constant.BrdConstant;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import com.smart.brd.manage.base.entity.area.BsProvince;
import com.smart.brd.manage.base.mapper.area.BsProvinceMapper;
import com.smart.brd.manage.base.service.area.*;
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
    public List<TBaseRegion> selectRegions(String code,String level){
        List<TBaseRegion> list = new ArrayList<>();
        if(level != null){
            if(BrdConstant.LEVEL_1.equals(level)){
                list = bsProvinceService.queryProvincesByCode(code);
            }else if(BrdConstant.LEVEL_2.equals(level)){
                list = bsCityService.queryCitysByCode(code);
            }else if(BrdConstant.LEVEL_3.equals(level)){
                list = bsCountyService.queryCountysByCode(code);
            }else if(BrdConstant.LEVEL_4.equals(level)){
                list = bsStreetService.queryStreetsByCode(code);
            }else if(BrdConstant.LEVEL_5.equals(level)){
                list = bsVillageService.queryVillagesByCode(code);
            }
        }else{
            list = bsProvinceService.queryProvincesByCode(code);
        }
        return list;
    }
}
