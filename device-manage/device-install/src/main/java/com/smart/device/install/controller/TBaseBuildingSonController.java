package com.smart.device.install.controller;

import com.smart.device.install.service.ITBaseBuildingSonService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author f
 */
@Api(tags = "")
@Slf4j
@RestController
@RequestMapping("/api/v2/install/base/buildingson")
public class TBaseBuildingSonController {

    /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBaseBuildingSonService itBaseBuildingSonService;

//    @ApiOperation(value = "建筑物设备查询接口")
//    @ApiImplicitParam
//    @GetMapping("/baseBuildingInitialize")
//    public Result baseBuildingList() {
//        try {
//            List<TBaseBuildingSon > list = itBaseBuildingSonService.selecByBuildingId(null);
//            return Result.SUCCESS(list);
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.ERROR(ResultCode.ERROR_MANAGER_BUILDING);
//        }
//    }


}
