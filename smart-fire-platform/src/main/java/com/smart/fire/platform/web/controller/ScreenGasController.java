package com.smart.fire.platform.web.controller;

import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.common.install.entity.vo.ScreenVo;
import com.smart.fire.platform.web.feign.DeviceInstallFeign;
import com.smart.fire.platform.web.feign.DeviceMessageFeign;
import com.smart.fire.platform.web.service.ScreenGasService;
import com.smart.fire.platform.web.service.ScreenGasService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/screen/gas")
public class ScreenGasController {

    @Resource
    private ScreenGasService screenGasService;
    @Resource
    private DeviceInstallFeign deviceInstallFeign;
    @Resource
    private DeviceMessageFeign deviceMessageFeign;

    @ApiOperation("设备状态")
    @GetMapping("/deviceStateGas")
    public Result deviceStateGas() {
        try {
            List<ScreenVo> list = deviceInstallFeign.deviceStateGas();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_GAS);
        }
    }
    @ApiOperation("设备类型")
    @GetMapping("/deviceTypeGas")
    public Result deviceTypeGas() {
        try {
            List<ScreenVo> list = deviceInstallFeign.deviceTypeGas();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_GAS);
        }
    }
    @ApiOperation("设备分类统计")
    @GetMapping("/deviceGasEventNum")
    public Result deviceGasEventNum() {
        try {
            List<ScreenVo> list = deviceMessageFeign.deviceGasEventNum();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_GAS);
        }
    }

    @ApiOperation("报警单位排行")
    @GetMapping("/eventNumByCompanyGas")
    public Result eventNumByCompanyGas() {
        try {
            List<ScreenVo> list = screenGasService.eventNumByCompanyGas();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_GAS);
        }
    }
    @ApiOperation("报警设备排行")
    @GetMapping("/eventNumByDeviceGas")
    public Result eventNumByDeviceGas() {
        try {
            List<ScreenVo> list = deviceMessageFeign.eventNumByDeviceGas();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_GAS);
        }
    }

    @ApiOperation("报警设备按月统计")
    @GetMapping("/eventNumByMonthGas")
    public Result eventNumByMonthGas() {
        try {
            Map<String,Object> map = deviceMessageFeign.eventNumByMonthGas();
            return Result.SUCCESS(map);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_GAS);
        }
    }


}
