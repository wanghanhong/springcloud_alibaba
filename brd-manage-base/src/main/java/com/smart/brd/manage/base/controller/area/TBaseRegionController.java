package com.smart.brd.manage.base.controller.area;

import com.smart.brd.manage.base.entity.area.TBaseRegion;
import com.smart.brd.manage.base.service.area.ITBaseRegionService;
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
@RequestMapping("/api/v1/brd/region/")
public class TBaseRegionController extends BaseController {

    @Resource
    private ITBaseRegionService itBaseRegionService;

    @GetMapping("/selectRegionsByCode")
    public List<TBaseRegion> selectRegionsByCode(String province, String city, String county, String town) {
        return itBaseRegionService.selectRegionsByCode(province,city,county,town);
    }

    @GetMapping("/mapRegions")
    public Map<String,String> mapRegions(String regionCodes) {
        return itBaseRegionService.mapRegions(regionCodes);
    }


}
