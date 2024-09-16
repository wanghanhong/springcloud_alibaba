package com.smart.device.install.controller;

import com.smart.common.core.controller.BaseController;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.install.service.ITBaseRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(tags = "")
@Slf4j
@RestController
@RequestMapping
public class TBaseRegionController extends BaseController {

    @Resource
    private ITBaseRegionService itBaseRegionService;

    @ApiOperation(value = "根据编码查询区域名称列表")
    @GetMapping("/api/v2/region/selectRegionsByCode")
    public List<TBaseRegion> selectRegionsByCode(String province,String city,String county,String town) {
        List<TBaseRegion> list = itBaseRegionService.selectRegionsByCode(province,city,county,town);
        return list;
    }
    @ApiOperation(value = "根据编码查询区域")
    @GetMapping("/api/v2/region/mapRegions")
    public Map<String,String> mapRegions(String regionCodes) {
        Map<String,String> map = itBaseRegionService.mapRegions(regionCodes);
        return map;
    }


}
