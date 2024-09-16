package com.smart.device.message.data.controller;


import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.common.install.entity.vo.SdDeviceVo;
import com.smart.device.message.data.entity.vo.HeartVo;
import com.smart.device.message.data.entity.vo.THeartSmokeVo;
import com.smart.device.message.data.service.THeartSmokeService;
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
public class THeartSmokeController extends BaseController {

    @Resource
    private THeartSmokeService tHeartSmokeService;

    /**
     *   根据IMEI 查询最近的一次心跳信息。和一部分设置的报警条件信息
     * @param
     * @return
     */

    @ApiOperation(value = "根据IMEI查询设备数据")
    @ApiImplicitParam
    @GetMapping("/selectSmokeLimit")
    public Result selectSmokeLimit(HeartVo vo) {
        try {
            THeartSmokeVo Smoke = tHeartSmokeService.selectSmokeLimit(vo);
            return Result.SUCCESS(Smoke);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_SMOKE);
        }
    }

    @ApiOperation(value = "根据ID查询设备数据")
    @ApiImplicitParam
    @GetMapping("/selectSmokeLast")
    public SdDeviceVo selectSmokeLast(Long deviceId) {
        try {
            SdDeviceVo result = tHeartSmokeService.selectSmokeLast(deviceId);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return new SdDeviceVo();
        }
    }

}
