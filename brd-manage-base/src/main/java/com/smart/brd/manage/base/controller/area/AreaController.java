package com.smart.brd.manage.base.controller.area;

import com.smart.brd.manage.base.entity.area.TBaseRegion;
import com.smart.brd.manage.base.service.area.AreaService;
import com.smart.brd.manage.base.service.area.IBsCityService;
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
@RequestMapping
public class  AreaController extends BaseController {

    @Resource
    private AreaService areaService;
    @Resource
    private IBsCityService iBsCityService;

    @ApiOperation(value = "根据 code 获取省市县乡")
    @GetMapping("/api/v1/brd/region/selectRegions")
    public Result selectRegions(String regionCode,String level) {
        try {
            List<TBaseRegion> list = areaService.selectRegions(regionCode,level);
            return Result.SUCCESS(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.FAIL();
        }
    }


    @ApiOperation(value = "根据省编码查询地级市")
    @ApiImplicitParam
    @GetMapping("/api/v1/brd/region/queryCitysByCode")
    public List<TBaseRegion> queryCitysByCode(String code){
        try {
            return iBsCityService.queryCitysByCode(code);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}

