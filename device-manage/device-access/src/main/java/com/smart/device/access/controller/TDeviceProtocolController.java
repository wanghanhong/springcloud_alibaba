package com.smart.device.access.controller;

import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.access.service.ITDeviceProtocolService;
import com.smart.device.common.access.entity.TDeviceProtocol;
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
@RequestMapping("/api/v2/device/protocol")
public class TDeviceProtocolController {

    @Resource
    private ITDeviceProtocolService deviceProtocolService;

    @ApiOperation(value = "查询协议")
    @ApiImplicitParam
    @GetMapping("/queryProtocolList")
    public Result queryProtocolList() {
        try {
            List<TDeviceProtocol> list = deviceProtocolService.queryProtocolList();
            return Result.SUCCESS(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_ACCESS_ERROR);
        }
    }


}
