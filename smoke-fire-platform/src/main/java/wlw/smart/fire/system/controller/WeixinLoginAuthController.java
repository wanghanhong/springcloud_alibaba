package wlw.smart.fire.system.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.smart.common.core.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wlw.smart.fire.common.utils.MD5Util;
import wlw.smart.fire.system.domain.Result;
import wlw.smart.fire.system.domain.po.User;
import wlw.smart.fire.system.manager.UserManager;
import wlw.smart.fire.system.service.UserConfigService;
import wlw.smart.fire.system.service.UserService;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


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


}
