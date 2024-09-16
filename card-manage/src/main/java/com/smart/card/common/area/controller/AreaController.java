package com.smart.card.common.area.controller;

import com.smart.card.common.area.entity.BsRegion;
import com.smart.card.common.area.service.AreaService;
import com.smart.card.common.area.service.IBsCityService;
import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/region/")
public class  AreaController extends BaseController {

    @Resource
    private AreaService areaService;
    @Resource
    private IBsCityService iBsCityService;

    @ApiOperation(value = "根据 code 获取省市县乡")
    @GetMapping("/selectRegions")
    public Result selectRegions(String regionCode,String level) {
        try {
            List<BsRegion> list = areaService.selectRegions(regionCode,level);
            return Result.SUCCESS(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.FAIL();
        }
    }


    @ApiOperation(value = "根据省编码查询地级市")
    @ApiImplicitParam
    @GetMapping("/queryCitysByCode")
    public List<BsRegion> queryCitysByCode(String code){
        try {
            List<BsRegion> list = iBsCityService.queryCitysByCode(code);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<BsRegion>();
        }
    }

}

