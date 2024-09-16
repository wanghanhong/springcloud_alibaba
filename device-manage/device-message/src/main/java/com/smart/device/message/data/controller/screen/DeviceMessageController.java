package com.smart.device.message.data.controller.screen;

import com.smart.device.common.install.entity.vo.ScreenListVo;
import com.smart.device.common.install.entity.vo.ScreenVo;
import com.smart.device.message.data.service.screen.DeviceMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(tags = "")
@Slf4j
@RestController
@RequestMapping("/api/v2/message/base/screen/event")
public class DeviceMessageController {
    @Resource
    private DeviceMessageService deviceMessageService;

    @ApiOperation(value = "获取烟感事件列表")
    @GetMapping("/deviceSmokeEventNum")
    public List<ScreenVo> deviceSmokeEventNum() {
        List<ScreenVo> list = deviceMessageService.deviceSmokeEventNum();
        return list;
    }
    @ApiOperation(value = "获取气感事件列表")
    @GetMapping("/deviceGasEventNum")
    public List<ScreenVo> deviceGasEventNum() {
        List<ScreenVo> list = deviceMessageService.deviceGasEventNum();
        return list;
    }
    @ApiOperation(value = "获取水压事件列表")
    @GetMapping("/deviceWaterpressEventNum")
    public List<ScreenVo> deviceWaterpressEventNum() {
        List<ScreenVo> list = deviceMessageService.deviceWaterpressEventNum();
        return list;
    }
    @ApiOperation(value = "获取液位事件列表")
    @GetMapping("/deviceLiquidleveEventNum")
    public List<ScreenVo> deviceLiquidleveEventNum() {
        List<ScreenVo> list = deviceMessageService.deviceLiquidleveEventNum();
        return list;
    }
    @ApiOperation(value = "获取电气事件列表")
    @GetMapping("/deviceElectricEventNum")
    public List<ScreenVo> deviceElectricEventNum() {
        List<ScreenVo> list = deviceMessageService.deviceElectricEventNum();
        return list;
    }

    @ApiOperation(value = "获取烟感-事件按照单位分类")
    @GetMapping("/eventNumByCompanySmoke")
    public List<ScreenVo> eventNumByCompanySmoke() {
        List<ScreenVo> list = deviceMessageService.eventNumByCompanySmoke();
        return list;
    }
    @ApiOperation(value = "获取气感-事件按照单位分类")
    @GetMapping("/eventNumByCompanyGas")
    public List<ScreenVo> eventNumByCompanyGas() {
        List<ScreenVo> list = deviceMessageService.eventNumByCompanyGas();
        return list;
    }
    @ApiOperation(value = "获取水压-事件按照单位分类")
    @GetMapping("/eventNumByCompanyWaterpress")
    public List<ScreenVo> eventNumByCompanyWaterpress() {
        List<ScreenVo> list = deviceMessageService.eventNumByCompanyWaterpress();
        return list;
    }
    @ApiOperation(value = "获取液位-事件按照单位分类")
    @GetMapping("/eventNumByCompanyLiquidleve")
    public List<ScreenVo> eventNumByCompanyLiquidleve() {
        List<ScreenVo> list = deviceMessageService.eventNumByCompanyLiquidleve();
        return list;
    }
    @ApiOperation(value = "获取电气-事件按照单位分类")
    @GetMapping("/eventNumByCompanyElectric")
    public List<ScreenVo> eventNumByCompanyElectric() {
        List<ScreenVo> list = deviceMessageService.eventNumByCompanyElectric();
        return list;
    }

    @ApiOperation(value = "获取烟感-事件按照设备分类")
    @GetMapping("/eventNumByDeviceSmoke")
    public List<ScreenVo> eventNumByDeviceSmoke() {
        List<ScreenVo> list = deviceMessageService.eventNumByDeviceSmoke();
        return list;
    }
    @ApiOperation(value = "获取气感-事件按照设备分类")
    @GetMapping("/eventNumByDeviceGas")
    public List<ScreenVo> eventNumByDeviceGas() {
        List<ScreenVo> list = deviceMessageService.eventNumByDeviceGas();
        return list;
    }
    @ApiOperation(value = "获取水压-事件按照设备分类")
    @GetMapping("/eventNumByDeviceWaterpress")
    public List<ScreenVo> eventNumByDeviceWaterpress() {
        List<ScreenVo> list = deviceMessageService.eventNumByDeviceWaterpress();
        return list;
    }
    @ApiOperation(value = "获取液位-事件按照设备分类")
    @GetMapping("/eventNumByDeviceLiquidleve")
    public List<ScreenVo> eventNumByDeviceLiquidleve() {
        List<ScreenVo> list = deviceMessageService.eventNumByDeviceLiquidleve();
        return list;
    }
    @ApiOperation(value = "获取电气-事件按照设备分类")
    @GetMapping("/eventNumByDeviceElectric")
    public ScreenListVo eventNumByDeviceElectric() {
        ScreenListVo list = deviceMessageService.eventNumByDeviceElectric();
        return list;
    }


    @ApiOperation(value = "获取烟感-事件按照月份分类")
    @GetMapping("/eventNumByMonthSmoke")
    public Map<String,Object> eventNumByMonthSmoke() {
        Map<String,Object> map = deviceMessageService.eventNumByMonthSmoke();
        return map;
    }
    @ApiOperation(value = "获取气感-事件按照月份分类")
    @GetMapping("/eventNumByMonthGas")
    public Map<String,Object> eventNumByMonthGas() {
        Map<String,Object> map = deviceMessageService.eventNumByMonthGas();
        return map;
    }
    @ApiOperation(value = "获取水压-事件按照月份分类")
    @GetMapping("/eventNumByMonthWaterpress")
    public Map<String,Object> eventNumByMonthWaterpress() {
        Map<String,Object> map = deviceMessageService.eventNumByMonthWaterpress();
        return map;
    }
    @ApiOperation(value = "获取液位-事件按照月份分类")
    @GetMapping("/eventNumByMonthLiquidleve")
    public Map<String,Object> eventNumByMonthLiquidleve() {
        Map<String,Object> map = deviceMessageService.eventNumByMonthLiquidleve();
        return map;
    }
    @ApiOperation(value = "获取电气-事件按照月份分类")
    @GetMapping("/eventNumByMonthElectric")
    public Map<String,Object> eventNumByMonthElectric() {
        Map<String,Object> list = deviceMessageService.eventNumByMonthElectric();
        return list;
    }


}
