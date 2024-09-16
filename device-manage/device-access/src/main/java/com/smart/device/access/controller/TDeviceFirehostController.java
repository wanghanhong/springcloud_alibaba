package com.smart.device.access.controller;


import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.access.service.ITDeviceWaterpressService;
import com.smart.device.common.access.entity.TDeviceWaterpress;
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
@RequestMapping("/api/v2/device/firehost")
public class TDeviceFirehostController {

    @Resource
    private ITDeviceWaterpressService itDeviceWaterpressService;
    
    @ApiOperation(value = "修改消防主机接口")
    @ApiImplicitParam
    @PostMapping("/deviceWaterpressUpdate")
    public Result deviceWaterpressUpdate(@RequestBody TDeviceWaterpress entity) {
        try {
            itDeviceWaterpressService.deviceWaterpressUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_ACCESS_WATERPRESS);
        }
    }

}
