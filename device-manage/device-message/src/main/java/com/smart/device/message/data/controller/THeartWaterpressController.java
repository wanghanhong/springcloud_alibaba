package com.smart.device.message.data.controller;

import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.common.install.entity.vo.SdDeviceVo;
import com.smart.device.message.data.entity.vo.HeartVo;
import com.smart.device.message.data.entity.vo.THeartWaterpressVo;
import com.smart.device.message.data.service.THeartWaterpressService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author
 */
@RestController
@RequestMapping("/api/v2/heart/")
public class THeartWaterpressController extends BaseController {

    @Resource
    private THeartWaterpressService tHeartWaterpressService;

    /**
     *   根据IMEI 查询最近的一次心跳信息。和一部分设置的报警条件信息
     * @param
     * @return
     */

    @ApiOperation(value = "根据IMEI查询设备数据")
    @ApiImplicitParam
    @GetMapping("/selectWaterpressLimit")
    public Result selectWaterpressLimit(HeartVo vo) {
        try {
            THeartWaterpressVo Waterpress = tHeartWaterpressService.selectWaterpressLimit(vo);
            return Result.SUCCESS(Waterpress);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }

    @ApiOperation(value = "根据ID查询设备数据")
    @ApiImplicitParam
    @GetMapping("/selectWaterpressLast")
    public SdDeviceVo selectWaterpressLast(Long deviceId) {
        try {
            SdDeviceVo result = tHeartWaterpressService.selectWaterpressLast(deviceId);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return new SdDeviceVo();
        }
    }


}
