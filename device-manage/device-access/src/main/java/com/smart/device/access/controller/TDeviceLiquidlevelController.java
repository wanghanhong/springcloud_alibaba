package com.smart.device.access.controller;


import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.access.service.ITDeviceLiquidlevelService;
import com.smart.device.common.access.entity.TDeviceLiquidlevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author f
 */
@RestController
@Api(tags = "")
@RequestMapping("/api/v2/device/liquidlevel")
public class TDeviceLiquidlevelController {

    @Resource
    private ITDeviceLiquidlevelService itDeviceLiquidlevelService;

    @ApiOperation(value = "修改液位接口")
    @ApiImplicitParam
    @PostMapping("/deviceLiquidlevelUpdate")
    public Result deviceLiquidlevelUpdate(@RequestBody TDeviceLiquidlevel entity) {
        try {
            itDeviceLiquidlevelService.deviceLiquidlevelUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_ACCESS_WATERPRESS);
        }
    }

}
