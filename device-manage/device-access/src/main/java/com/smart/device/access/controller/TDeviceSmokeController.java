package com.smart.device.access.controller;

import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.access.service.ITDeviceSmokeService;
import com.smart.device.common.access.entity.TDeviceSmoke;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author f
 */
@Api(tags = "")
@RestController
@RequestMapping("/api/v2/device/smoke")
public class TDeviceSmokeController extends BaseController {

    @Resource
    private ITDeviceSmokeService itDeviceSmokeService;

    @ApiOperation(value = "修改烟感接口")
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

    @ApiOperation(value = "根据IMEI查询烟感数据接口")
    @ApiImplicitParam
    @GetMapping("/selectDeviceSmoke")
    public DeviceBaseVo selectDeviceSmoke(Long id,Long imei) {
        try {
            DeviceBaseVo deviceBaseVo = itDeviceSmokeService.selectDeviceSmoke(id,imei);
            return deviceBaseVo;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
