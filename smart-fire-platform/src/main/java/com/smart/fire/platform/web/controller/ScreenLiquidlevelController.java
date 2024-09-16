package com.smart.fire.platform.web.controller;

import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.common.install.entity.vo.ScreenVo;
import com.smart.fire.platform.web.feign.DeviceInstallFeign;
import com.smart.fire.platform.web.feign.DeviceMessageFeign;
import com.smart.fire.platform.web.service.ScreenLiquidlevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/screen/liquidlevel")
public class ScreenLiquidlevelController {

    @Resource
    private ScreenLiquidlevelService screenLiquidlevelService;
    @Resource
    private DeviceInstallFeign deviceInstallFeign;
    @Resource
    private DeviceMessageFeign deviceMessageFeign;

    @ApiOperation("设备状态")
    @GetMapping("/deviceStateLiquidlevel")
    public Result deviceStateLiquidlevel() {
        try {
            List<ScreenVo> list = deviceInstallFeign.deviceStateLiquidlevel();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }
    @ApiOperation("设备类型")
    @GetMapping("/deviceTypeLiquidlevel")
    public Result deviceTypeLiquidlevel() {
        try {
            List<ScreenVo> list = deviceInstallFeign.deviceTypeLiquidlevel();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }
    @ApiOperation("设备分类统计")
    @GetMapping("/deviceLiquidlevelEventNum")
    public Result deviceLiquidlevelEventNum() {
        try {
            List<ScreenVo> list = deviceMessageFeign.deviceLiquidlevelEventNum();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }

    @ApiOperation("报警单位排行")
    @GetMapping("/eventNumByCompanyLiquidlevel")
    public Result eventNumByCompanyLiquidlevel() {
        try {
            List<ScreenVo> list = screenLiquidlevelService.eventNumByCompanyLiquidlevel();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_LIQUIDLEVEL);
        }
    }
    @ApiOperation("报警设备排行")
    @GetMapping("/eventNumByDeviceLiquidlevel")
    public Result eventNumByDeviceLiquidlevel() {
        try {
            List<ScreenVo> list = deviceMessageFeign.eventNumByDeviceLiquidlevel();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_LIQUIDLEVEL);
        }
    }

    @ApiOperation("报警设备按月统计")
    @GetMapping("/eventNumByMonthLiquidlevel")
    public Result eventNumByMonthLiquidlevel() {
        try {
            Map<String,Object> map = deviceMessageFeign.eventNumByMonthLiquidlevel();
            return Result.SUCCESS(map);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_LIQUIDLEVEL);
        }
    }
}
