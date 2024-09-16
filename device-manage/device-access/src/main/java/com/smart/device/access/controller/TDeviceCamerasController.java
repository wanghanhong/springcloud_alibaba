package com.smart.device.access.controller;

import com.smart.device.access.service.ITDeviceCamerasService;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author f
 */
@Api(tags = "")
@RestController
@RequestMapping("/api/v2/device/cameras")
public class TDeviceCamerasController {

    @Resource
    private ITDeviceCamerasService itDeviceCamerasService;

    @ApiOperation(value = "根据IMEI查询电力数据接口")
    @ApiImplicitParam
    @GetMapping("/selectDeviceCameras")
    public DeviceBaseVo selectDeviceCameras(String sn) {
        try {
            DeviceBaseVo deviceBaseVo = itDeviceCamerasService.selectDeviceCameras(sn);
            return deviceBaseVo;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
