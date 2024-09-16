package com.smart.device.access.controller;

import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.access.service.ITDeviceCamerasService;
import com.smart.device.access.service.ITDeviceDictService;
import com.smart.device.common.access.entity.TDeviceDict;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author f
 */
@Api(tags = "")
@RestController
@RequestMapping("/api/v2/device/dict")
public class TDeviceDictController {

    @Resource
    private ITDeviceDictService deviceDictService;

    @ApiOperation(value = "查询设备大类-类型")
    @ApiImplicitParam
    @GetMapping("/queryDeviceParentTypeList")
    public List<TDeviceDict> queryDeviceParentTypeList() {
        try {
            List<TDeviceDict> list = deviceDictService.queryDeviceParentTypeList();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "根据类型查询字典")
    @ApiImplicitParam
    @GetMapping("/getDictBydeviceType")
    public TDeviceDict getDictBydeviceType(Integer deviceType) {
        try {
            TDeviceDict dict = deviceDictService.getDictBydeviceType(deviceType);
            return dict;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ApiOperation(value = "查询设备类型-详细类型")
    @ApiImplicitParam
    @GetMapping("/queryDeviceTypeList")
    public Result queryDeviceTypeList() {
        try {
            List<TDeviceDict> list = deviceDictService.queryDeviceTypeList();
            return Result.SUCCESS(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_ACCESS_ERROR);
        }
    }


}
