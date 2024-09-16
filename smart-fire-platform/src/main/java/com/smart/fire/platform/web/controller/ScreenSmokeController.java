package com.smart.fire.platform.web.controller;

import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.common.install.entity.vo.ScreenVo;
import com.smart.fire.platform.web.feign.DeviceInstallFeign;
import com.smart.fire.platform.web.feign.DeviceMessageFeign;
import com.smart.fire.platform.web.service.ScreenSmokeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/screen/smoke")
public class ScreenSmokeController {

    @Resource
    private ScreenSmokeService screenSmokeService;
    @Resource
    private DeviceInstallFeign deviceInstallFeign;
    @Resource
    private DeviceMessageFeign deviceMessageFeign;

    @ApiOperation("设备状态")
    @GetMapping("/deviceStateSmoke")
    public Result deviceStateSmoke() {
        try {
            List<ScreenVo> list = deviceInstallFeign.deviceStateSmoke();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_SMOKE);
        }
    }
    @ApiOperation("设备类型")
    @GetMapping("/deviceTypeSmoke")
    public Result deviceTypeSmoke() {
        try {
            List<ScreenVo> list = deviceInstallFeign.deviceTypeSmoke();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_SMOKE);
        }
    }
    @ApiOperation("设备分类统计")
    @GetMapping("/deviceSmokeEventNum")
    public Result deviceSmokeEventNum() {
        try {
            List<ScreenVo> list = deviceMessageFeign.deviceSmokeEventNum();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_SMOKE);
        }
    }

    @ApiOperation("报警单位排行")
    @GetMapping("/eventNumByCompanySmoke")
    public Result eventNumByCompanySmoke() {
        try {
            List<ScreenVo> list = screenSmokeService.eventNumByCompanySmoke();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_SMOKE);
        }
    }
    @ApiOperation("报警设备排行")
    @GetMapping("/eventNumByDeviceSmoke")
    public Result eventNumByDeviceSmoke() {
        try {
            List<ScreenVo> list = deviceMessageFeign.eventNumByDeviceSmoke();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_SMOKE);
        }
    }

    @ApiOperation("报警设备按月统计")
    @GetMapping("/eventNumByMonthSmoke")
    public Result eventNumByMonthSmoke() {
        try {
            Map<String,Object> map = deviceMessageFeign.eventNumByMonthSmoke();
            return Result.SUCCESS(map);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_SMOKE);
        }
    }

}
