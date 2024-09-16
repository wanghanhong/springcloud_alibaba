package com.smart.device.access.controller;


import com.smart.device.access.service.TUserInfoTypeService;
import com.smart.device.common.access.entity.UserInfoType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "")
@RestController
@RequestMapping("/api/v2/userInfoType")
public class TUserInfoTypeController {

    @Resource
    TUserInfoTypeService tUserInfoTypeService;

    @ApiOperation(value = "根据partId类型查找")
    @ApiImplicitParam
    @GetMapping("/getDictByDeviceType")
    public UserInfoType getDictByDeviceType(String partId){
        try {
            UserInfoType dictByDeviceType = tUserInfoTypeService.getDictByDeviceType(partId);
            return dictByDeviceType;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
