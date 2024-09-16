package com.smart.device.access.controller;


import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.access.service.ITDeviceWaterpressService;
import com.smart.device.common.access.entity.TDeviceWaterpress;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author f
 */
@Api(tags = "")
@Slf4j
@RestController
@RequestMapping("/api/v2/device/waterpress")
public class TDeviceWaterpressController {

    /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITDeviceWaterpressService itDeviceWaterpressService;

    @ApiOperation(value = "修改水压接口")
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

    @ApiOperation(value = "所属液位-下拉选择")
    @ApiImplicitParam
    @GetMapping("/deviceLiquidlevelAll")
    public Result deviceLiquidlevelAll(TDeviceWaterpress entity) {
        try {
            List<TDeviceWaterpress> list =itDeviceWaterpressService.deviceLiquidlevelAll(entity);
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_ACCESS_WATERPRESS);
        }
    }

    @ApiOperation(value = "所属水压-下拉选择")
    @ApiImplicitParam
    @GetMapping("/deviceWaterpresssAll")
    public Result deviceWaterpresssAll(TDeviceWaterpress entity) {
        try {
            List<TDeviceWaterpress> list =itDeviceWaterpressService.deviceWaterpresssAll(entity);
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_ACCESS_WATERPRESS);
        }
    }

    @ApiOperation(value = "根据IMEI查询水压数据接口")
    @ApiImplicitParam
    @GetMapping("/selectDeviceWaterpress")
    public DeviceBaseVo selectDeviceWaterpress(Long id,Long imei) {
        try {
            DeviceBaseVo deviceBaseVo = itDeviceWaterpressService.selectDeviceWaterpress(id,imei);
            return deviceBaseVo;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    
}
