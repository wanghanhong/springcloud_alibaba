package com.smart.device.message.data.controller;

import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.common.access.entity.TDeviceCameras;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.message.data.service.TCamerasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author
 */
@Api(tags = "")
@RestController
@RequestMapping("/api/v2/heart/")
public class TCamerasController extends BaseController {

    @Resource
    private TCamerasService tCamerasService;

    /**
     *   根据IMEI 查询最近的一次心跳信息。和一部分设置的报警条件信息
     * @param
     * @return
     */

    @ApiOperation(value = "根据IMEI查询设备数据")
    @ApiImplicitParam
    @GetMapping("/selectCamerasLimit")
    public Result selectCamerasLimit(TDeviceCameras vo) {
        try {
            DeviceBaseVo Cameras = tCamerasService.selectCamerasLimit(vo);
            return Result.SUCCESS(Cameras);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_CAMERAS);
        }
    }

}
