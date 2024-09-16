package com.smart.device.install.service.area.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.area.BsProvince;
import com.smart.device.install.mapper.area.BsProvinceMapper;
import com.smart.device.install.service.area.AreaService;
import com.smart.device.install.service.area.IBsCityService;
import com.smart.device.install.service.area.IBsCountyService;
import com.smart.device.install.service.area.IBsProvinceService;
import com.smart.device.install.service.area.IBsStreetService;
import com.smart.device.install.service.area.IBsVillageService;
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
