package com.smart.device.install.controller;

import com.smart.common.core.domain.Result;
import com.smart.device.common.install.entity.TBaseDict;
import com.smart.device.install.service.ITBaseDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author f
 */
@Api(tags = "")
@Slf4j
@RestController
@RequestMapping("/api/v2/device/dict")
public class TBaseDictController {

    @Resource
    private ITBaseDictService itBaseDictService;

    @ApiOperation(value = "消防器材选择")
    @ApiImplicitParam
    @GetMapping("/dictListByType")
    public List<TBaseDict> dictListByType(String type) {
        try {
            List<TBaseDict> list =itBaseDictService.dictListByType(type);
            return list;
        } catch (Exception e){
            e.printStackTrace();;
        }
        return new ArrayList<>();
    }

    @ApiOperation(value = "")
    @ApiImplicitParam
    @GetMapping("/firehydrantDictList")
    public Result firehydrantDictList() {
        try {
            List<TBaseDict> list =itBaseDictService.dictListByType("1");
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.FAIL("查询失败");
        }
    }

    @ApiOperation(value = "公司类型选择")
    @ApiImplicitParam
    @GetMapping("/companyTypeList")
    public Result companyTypeList() {
        try {
            List<TBaseDict> list =itBaseDictService.dictListByType("3");
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.FAIL("查询失败");
        }
    }


}
