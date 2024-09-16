package com.smart.card.common.area.controller;

import com.smart.card.common.area.entity.BsRegion;
import com.smart.card.common.area.service.IBsRegionService;
import com.smart.common.core.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/region/")
public class BsRegionController extends BaseController {

    @Resource
    private IBsRegionService iBsRegionService;

    @GetMapping("/selectRegionsByCode")
    public List<BsRegion> selectRegionsByCode(String province, String city, String county, String town) {
        List<BsRegion> list = iBsRegionService.selectRegionsByCode(province,city,county,town);
        return list;
    }

    @GetMapping("/mapRegions")
    public Map<String,String> mapRegions(String regionCodes) {
        Map<String,String> map = iBsRegionService.mapRegions(regionCodes);
        return map;
    }


}
