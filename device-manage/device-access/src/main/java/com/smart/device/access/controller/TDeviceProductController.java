package com.smart.device.access.controller;

import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.access.service.ITDeviceDictService;
import com.smart.device.access.service.ITDeviceProductService;
import com.smart.device.common.access.entity.TDeviceDict;
import com.smart.device.common.access.entity.TDeviceProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author f
 */
@Api(tags = "")
@RestController
@RequestMapping("/api/v2/device/product")
public class TDeviceProductController {

    @Resource
    private ITDeviceProductService deviceProductService;

    @ApiOperation(value = "查询厂商")
    @ApiImplicitParam
    @GetMapping("/queryProductList")
    public Result queryProductList() {
        try {
            List<TDeviceProduct> list = deviceProductService.queryProductList();
            return Result.SUCCESS(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_ACCESS_ERROR);
        }
    }

}
