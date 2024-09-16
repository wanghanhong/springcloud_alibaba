package com.smart.device.access.controller;


import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.access.service.ITDeviceSmokeService;
import com.smart.device.common.access.entity.TDeviceSmoke;
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
@Api(tags = "")
@RestController
@RequestMapping("/api/v2/device/gas")
public class TDeviceGasController {

    @Resource
    private ITDeviceSmokeService itDeviceSmokeService;

    @ApiOperation(value = "修改气感接口")
    @ApiImplicitParam
    @PostMapping("/deviceSmokeUpdate")
    public Result deviceSmokeUpdate(@RequestBody TDeviceSmoke entity) {
        try {
            itDeviceSmokeService.deviceSmokeUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_ACCESS_SMOKE);
        }
    }

}
