package com.smart.device.access.controller;

import com.smart.device.access.service.TDeviceUserinfoTypeService;
import com.smart.device.common.access.entity.TDeviceUserinfoType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
@Api(tags = "")
@RestController
@RequestMapping("/api/v2/device/userinfotype")
public class TDeviceUserinfoTypeController {

    @Resource
    private TDeviceUserinfoTypeService userinfoTypeService;

    @ApiOperation(value = "根据类型查询字典")
    @ApiImplicitParam
    @GetMapping("/getDictBydeviceType")
    public TDeviceUserinfoType getDictBydeviceType(String partId) {
        try {
            TDeviceUserinfoType dict = userinfoTypeService.getUserinfoType(partId);
            return dict;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
