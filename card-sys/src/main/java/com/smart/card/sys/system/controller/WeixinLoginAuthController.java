package com.smart.card.sys.system.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.smart.card.sys.common.utils.MD5Util;
import com.smart.card.sys.system.domain.po.User;
import com.smart.card.sys.system.manager.UserManager;
import com.smart.card.sys.system.service.UserConfigService;
import com.smart.card.sys.system.service.UserService;
import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    // 小程序端-保存用户
    @PostMapping("/api/v1/xcx/user/add")
    @ResponseBody
    public Result saveXCXUser(@RequestBody User user){
        if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotEmpty(user.getUsername())) {
            List<User> users = userService.getBaseMapper().selectList(
                    new LambdaQueryWrapper<User>().eq(User::getUsername,user.getUsername())
            );
            if (!users.isEmpty()){
                message = "用户名重复，请重试";
                return Result.FAIL(message);
            }
        }
        try {
            user.setUsername(user.getUsername());
            user.setPassword(user.getPassword());
            user.setDeptId(null);
            user.setRoleId("1");
            user.setTenantId(1L);
            user.setStatus("1");
            user.setCreateTime(new Date());
            user.setPassTime(new Date());
            user.setUserType(0);
            user.setIsXcx(1);
            user.setIsShow(0);
            createUser(user);
            return Result.SUCCESS();
        } catch (Exception e) {
            log.error(message, e);
            message = "新增用户失败";
            return Result.FAIL(message);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void createUser(User user) throws Exception {
        // 创建用户
        user.setCreateTime(new Date());
        user.setPassTime(new Date());
        user.setAvatar(User.DEFAULT_AVATAR);
        user.setPassword(MD5Util.encrypt(user.getUsername(), user.getPassword()) );
        //性别默认未知
        if (ObjectUtil.isNull(user.getGender())) {
            user.setGender(User.SEX_UNKNOW);
        }
        userService.save(user);

        // 保存用户角色
        if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotEmpty(user.getRoleId())) {
            String[] roles = user.getRoleId().split(StringPool.COMMA);
            userService.setUserRoles(user, roles);
        }
        // 创建用户默认的个性化配置
        userConfigService.initDefaultUserConfig(String.valueOf(user.getUserId()));
        // 将用户相关信息保存到 Redis中
        userManager.loadUserRedisCache(user);
    }

    @GetMapping("/api/v1/xcx/user/resetPassWord")
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

    // 特定接口，自定义账户，密码，和部门
    @PostMapping("/api/v1/xcx/user/createUserPass")
    @ResponseBody
    public Result createUserPass(@RequestBody User user){
        //  保持前端一直，前端用来一次MD5加密
        String password = MD5Util.encrypt2(user.getPassword());
        if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotEmpty(user.getUsername())) {
            List<User> users = userService.getBaseMapper().selectList(
                    new LambdaQueryWrapper<User>().eq(User::getUsername,user.getUsername())
            );
            if (!users.isEmpty()){
                message = "用户名重复，请重试";
                return Result.FAIL(message);
            }
        }
        try {
            user.setUsername(user.getUsername());
            user.setPassword(password);
            user.setDeptId(user.getDeptId());
            user.setRoleId("1");
            user.setTenantId(1L);
            user.setStatus("1");
            user.setCreateTime(new Date());
            user.setPassTime(new Date());
            user.setUserType(0);

            if(user.getIsXcx() != null){
                user.setIsXcx(user.getIsXcx());
            }else{
                user.setIsXcx(0);
            }
            if(user.getIsShow() != null){
                user.setIsShow(user.getIsShow());
            }else{
                user.setIsShow(0);
            }
            createUser(user);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "新增用户失败";
            return Result.FAIL(message);
        }
    }



}
