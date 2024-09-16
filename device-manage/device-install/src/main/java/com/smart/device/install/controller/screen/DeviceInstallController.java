package com.smart.device.install.controller.screen;

import com.smart.device.common.install.entity.vo.ScreenVo;
import com.smart.device.install.service.screen.DeviceInstallService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v2/install/base/screen/install")
public class DeviceInstallController {
    @Resource
    private DeviceInstallService deviceInstallService;

    @ApiOperation(value = "获取烟感状态列表")
    @GetMapping("/deviceStateSmoke")
    public List<ScreenVo> deviceStateSmoke() {
        List<ScreenVo> list = deviceInstallService.deviceStateSmoke();
        return list;
    }
    @ApiOperation(value = "获取气感状态列表")
    @GetMapping("/deviceStateGas")
    public List<ScreenVo> deviceStateGas() {
        List<ScreenVo> list = deviceInstallService.deviceStateGas();
        return list;
    }
    @ApiOperation(value = "获取水压状态列表")
    @GetMapping("/deviceStateWaterpress")
    public List<ScreenVo> deviceStateWaterpress() {
        List<ScreenVo> list = deviceInstallService.deviceStateWaterpress();
        return list;
    }
    @ApiOperation(value = "获取液位状态列表")
    @GetMapping("/deviceStateLiquidlevel")
    public List<ScreenVo> deviceStateLiquidlevel() {
        List<ScreenVo> list = deviceInstallService.deviceStateLiquidlevel();
        return list;
    }
    @ApiOperation(value = "获取电气状态列表")
    @GetMapping("/deviceStateElectric")
    public List<ScreenVo> deviceStateElectric() {
        List<ScreenVo> list = deviceInstallService.deviceStateElectric();
        return list;
    }

    @ApiOperation(value = "获取烟感型号列表")
    @GetMapping("/deviceTypeSmoke")
    public List<ScreenVo> deviceTypeSmoke() {
        List<ScreenVo> list = deviceInstallService.deviceTypeSmoke();
        return list;
    }
    @ApiOperation(value = "获取气感型号列表")
    @GetMapping("/deviceTypeGas")
    public List<ScreenVo> deviceTypeGas() {
        List<ScreenVo> list = deviceInstallService.deviceTypeGas();
        return list;
    }
    @ApiOperation(value = "获取水压型号列表")
    @GetMapping("/deviceTypeWaterpress")
    public List<ScreenVo> deviceTypeWaterpress() {
        List<ScreenVo> list = deviceInstallService.deviceTypeWaterpress();
        return list;
    }
    @ApiOperation(value = "获取液位型号列表")
    @GetMapping("/deviceTypeLiquidleve")
    public List<ScreenVo> deviceTypeLiquidlevel() {
        List<ScreenVo> list = deviceInstallService.deviceTypeLiquidlevel();
        return list;
    }
    @ApiOperation(value = "获取电气型号列表")
    @GetMapping("/deviceTypeElectric")
    public List<ScreenVo> deviceTypeElectric() {
        List<ScreenVo> list = deviceInstallService.deviceTypeElectric();
        return list;
    }

    @ApiOperation(value = "获取烟感数量-按单位统计")
    @GetMapping("/deviceSmokeNumByCompany")
    public List<ScreenVo> deviceSmokeNumByCompany() {
        List<ScreenVo> list = deviceInstallService.deviceSmokeNumByCompany();
        return list;
    }
    @ApiOperation(value = "获取气感数量-按单位统计")
    @GetMapping("/deviceGasNumByCompany")
    public List<ScreenVo> deviceGasNumByCompany() {
        List<ScreenVo> list = deviceInstallService.deviceGasNumByCompany();
        return list;
    }
    @ApiOperation(value = "获取水压数量-按单位统计")
    @GetMapping("/deviceWaterpressNumByCompany")
    public List<ScreenVo> deviceWaterpressNumByCompany() {
        List<ScreenVo> list = deviceInstallService.deviceWaterpressNumByCompany();
        return list;
    }
    @ApiOperation(value = "获取液位数量-按单位统计")
    @GetMapping("/deviceLiquidlevelNumByCompany")
    public List<ScreenVo> deviceLiquidlevelNumByCompany() {
        List<ScreenVo> list = deviceInstallService.deviceLiquidlevelNumByCompany();
        return list;
    }
    @ApiOperation(value = "获取电气数量-按单位统计")
    @GetMapping("/deviceElectricNumByCompany")
    public List<ScreenVo> deviceElectricNumByCompany() {
        List<ScreenVo> list = deviceInstallService.deviceElectricNumByCompany();
        return list;
    }

    @ApiOperation(value = "获取电气设备关联统计")
    @GetMapping("/deviceBuildCompanyElectric")
    public List<ScreenVo> deviceBuildCompanyElectric() {
        List<ScreenVo> list = deviceInstallService.deviceBuildCompanyElectric();
        return list;
    }
}
