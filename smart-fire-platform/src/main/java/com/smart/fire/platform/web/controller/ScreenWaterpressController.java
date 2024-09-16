package com.smart.fire.platform.web.controller;

import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.common.install.entity.vo.ScreenVo;
import com.smart.fire.platform.web.feign.DeviceInstallFeign;
import com.smart.fire.platform.web.feign.DeviceMessageFeign;
import com.smart.fire.platform.web.service.ScreenElectricService;
import com.smart.fire.platform.web.service.ScreenWaterpressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/screen/waterpress")
public class ScreenWaterpressController {

    @Resource
    private ScreenWaterpressService screenWaterpressService;
    @Resource
    private DeviceInstallFeign deviceInstallFeign;
    @Resource
    private DeviceMessageFeign deviceMessageFeign;

    @ApiOperation("设备状态")
    @GetMapping("/deviceStateWaterpress")
    public Result deviceStateWaterpress() {
        try {
            List<ScreenVo> list = deviceInstallFeign.deviceStateWaterpress();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }
    @ApiOperation("设备类型")
    @GetMapping("/deviceTypeWaterpress")
    public Result deviceTypeWaterpress() {
        try {
            List<ScreenVo> list = deviceInstallFeign.deviceTypeWaterpress();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }
    @ApiOperation("设备事件分类统计")
    @GetMapping("/deviceWaterpressEventNum")
    public Result deviceWaterpressEventNum() {
        try {
            List<ScreenVo> list = deviceMessageFeign.deviceWaterpressEventNum();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }
    
    @ApiOperation("报警单位排行")
    @GetMapping("/eventNumByCompanyWaterpress")
    public Result eventNumByCompanyWaterpress() {
        try {
            List<ScreenVo> list = screenWaterpressService.eventNumByCompanyWaterpress();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }
    @ApiOperation("报警设备排行")
    @GetMapping("/eventNumByDeviceWaterpress")
    public Result eventNumByDeviceWaterpress() {
        try {
            List<ScreenVo> list = deviceMessageFeign.eventNumByDeviceWaterpress();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }

    @ApiOperation("报警设备按月统计")
    @GetMapping("/eventNumByMonthWaterpress")
    public Result eventNumByMonthWaterpress() {
        try {
            Map<String,Object> map = deviceMessageFeign.eventNumByMonthWaterpress();
            return Result.SUCCESS(map);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }

}
