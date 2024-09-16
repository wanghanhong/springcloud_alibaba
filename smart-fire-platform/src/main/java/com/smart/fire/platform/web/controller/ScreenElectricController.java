package com.smart.fire.platform.web.controller;

import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.common.install.entity.vo.ScreenListVo;
import com.smart.device.common.install.entity.vo.ScreenVo;
import com.smart.fire.platform.web.feign.DeviceInstallFeign;
import com.smart.fire.platform.web.feign.DeviceMessageFeign;
import com.smart.fire.platform.web.service.ScreenElectricService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/screen/electric")
public class ScreenElectricController {

    @Resource
    private ScreenElectricService screenElectricService;
    @Resource
    private DeviceInstallFeign deviceInstallFeign;
    @Resource
    private DeviceMessageFeign deviceMessageFeign;

    @ApiOperation("设备状态")
    @GetMapping("/deviceStateElectric")
    public Result deviceStateElectric() {
        try {
            List<ScreenVo> list = deviceInstallFeign.deviceStateElectric();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }
    @ApiOperation("设备类型")
    @GetMapping("/deviceTypeElectric")
    public Result deviceTypeElectric() {
        try {
            List<ScreenVo> list = deviceInstallFeign.deviceTypeElectric();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }
    @ApiOperation("设备分类统计")
    @GetMapping("/deviceElectricEventNum")
    public Result deviceElectricEventNum() {
        try {
            List<ScreenVo> list = deviceMessageFeign.deviceElectricEventNum();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }

    @ApiOperation("报警单位排行")
    @GetMapping("/eventNumByCompanyElectric")
    public Result eventNumByCompanyElectric() {
        try {
            List<ScreenVo> list = screenElectricService.eventNumByCompanyElectric();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }
    @ApiOperation("报警设备排行")
    @GetMapping("/eventNumByDeviceElectric")
    public Result eventNumByDeviceElectric() {
        try {
            ScreenListVo vo = deviceMessageFeign.eventNumByDeviceElectric();
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }

    @ApiOperation("报警设备按月统计")
    @GetMapping("/eventNumByMonthElectric")
    public Result eventNumByMonthElectric() {
        try {
            Map<String,Object> map = deviceMessageFeign.eventNumByMonthElectric();
            return Result.SUCCESS(map);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }

}
