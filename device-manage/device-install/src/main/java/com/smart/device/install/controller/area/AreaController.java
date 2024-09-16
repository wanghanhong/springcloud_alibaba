package com.smart.device.install.controller.area;

import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.install.service.area.AreaService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
@RequestMapping
public class  AreaController extends BaseController {

    @Resource
    private AreaService areaService;

    @ApiOperation(value = "根据 code 获取省市县乡")
    @GetMapping("/api/v2/region/selectRegions")
    public Result selectRegions(String regionCode,String level) {
        try {
            List<TBaseRegion> list = areaService.selectRegions(regionCode,level);
            return Result.SUCCESS(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.FAIL();
        }
    }

}

