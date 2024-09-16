package com.smart.brd.sys.system.controller;

import com.smart.brd.sys.common.utils.MD5Util;
import com.smart.brd.sys.system.manager.UserManager;
import com.smart.brd.sys.system.service.UserService;
import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.smart.brd.sys.system.service.UserConfigService;
import javax.annotation.Resource;


@Slf4j
@Validated
@RestController
public class WeixinLoginAuthController extends BaseController {
    @Resource
    private UserManager userManager;
    @Resource
    private UserService userService;
    @Resource
    private UserConfigService userConfigService;

    @Value("${xcxappKey}")
    public String xcxappKey;
    @Value("${xcxappSecret}")
    public String xcxappSecret;
    private String message;

    @GetMapping("/api/v2/resetUserPassWord")
    @ResponseBody
    public Result resetUserPassWord(String username){
        try {
            userService.resetUserPassWord(username);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "重置密码失败.";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    public static void main(String[] args){
        String password3 = "admin@2020wlw";
        String password4 = MD5Util.encrypt2(password3);
        System.out.println(password4);
    }


}
