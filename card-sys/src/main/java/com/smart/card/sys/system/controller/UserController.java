package com.smart.card.sys.system.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.smart.card.sys.common.annotation.Log;
import com.smart.card.sys.common.controller.BaseController;
import com.smart.card.sys.common.domain.QueryRequest;
import com.smart.card.sys.common.utils.MD5Util;
import com.smart.card.sys.system.domain.po.User;
import com.smart.card.sys.system.domain.po.UserConfig;
import com.smart.card.sys.system.domain.vo.UserVo;
import com.smart.card.sys.system.manager.UserManager;
import com.smart.card.sys.system.service.UserConfigService;
import com.smart.card.sys.system.service.UserService;
import com.smart.card.sys.system.usertoken.constant.RedisConst;
import com.smart.common.core.domain.Result;
import com.smart.common.utils.EncryptUtil;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {
    private Integer error_code = 10001;
    private String message;

    @Resource
    private UserService userService;
    @Resource
    private UserConfigService userConfigService;
    @Resource
    private UserManager userManager;


    @GetMapping("/check/{username}")
    public Result checkUserName(@NotBlank(message = "{required}") @PathVariable String username) {
        boolean status = this.userService.findByName(username) == null;
        return Result.SUCCESS(status);
    }

    @GetMapping("/{username}")
    public Result detail(@NotBlank(message = "{required}") @PathVariable String username) {
        User user = this.userService.findByName(username);
        return Result.SUCCESS(user);
    }

    @GetMapping("/list")
//    @RequiresPermissions("user:view")
    public Result userList(HttpServletRequest request,QueryRequest queryRequest, User user) {
        Map<String, Object> dataTable = getDataTable(userService.findUserDetail(request,user, queryRequest));
        return Result.SUCCESS(dataTable);
    }

    /**
     *  添加用户
     *      1、获取当前用户的租户ID
     *          a 超级管理员，生成
     *          b 租户：获取当前租户的id
     *      2、设置租户ID到User用户
     *      3、保存
     * @param user
     * @return
     * @throws
     */
    @Log("新增用户")
    @PostMapping("/add")
    //   @RequiresPermissions("user:add")
    public Result addUser(HttpServletRequest request,@RequestBody User user){
            try {
                user.setStatus("1");
                user.setUsername(user.getUsername());
                user.setTenantId(tenantId);
                Result res = userService.createUser(request,user);
                return res;
            } catch (Exception e) {
                message = "新增用户失败";
                log.error(message, e);
                return Result.FAIL(message);
            }
    }

    @Log("修改用户")
    @PostMapping("/update")
//    @RequiresPermissions("user:update")
    public Result updateUser(HttpServletRequest request,@RequestBody User user){
        try {
            this.userService.updateUser(request,user);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "用户状态修改失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @Log("用户状态修改")
    @PostMapping("/status")
//    @RequiresPermissions("user:update")
    public Result changeStatus(@RequestBody UserVo vo){
        try {
            this.userService.changeStatus(vo);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "用户状态修改失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @Log("删除用户")
    @GetMapping("/del/{userIds}")
//    @RequiresPermissions("user:delete")
    public Result deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds){
        try {
            String[] ids = userIds.split(StringPool.COMMA);
            for(int i=0;i<ids.length;i++){
                Long id = Long.parseLong(ids[i]);
                if(id < 0){
                    message = "包含系统用户，请联系管理员删除";
                    return Result.FAIL(message);
                }
            }
            this.userService.deleteUsers(ids);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "删除用户失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @PostMapping("/profile")
    public Result updateProfile(@RequestBody User user){
        try {
            this.userService.updateProfile(user);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "修改个人信息失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @PostMapping("/avatar")
    public Result updateAvatar(@RequestBody UserVo vo){
        try {
            this.userService.updateAvatar(vo.getUsername(), vo.getAvatar());
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "修改头像失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @PostMapping("/userConfig")
    public Result updateUserConfig(@RequestBody UserConfig userConfig){
        try {
            this.userConfigService.update(userConfig);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "修改个性化配置失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @GetMapping("/password/check")
    public Result checkPassword(@RequestBody UserVo vo) {
        String encryptPassword = MD5Util.encrypt(vo.getUsername(), vo.getPassword());
        User user = userService.findByName(vo.getUsername());
        boolean status;
        if (user != null) {
            status = StringUtils.equals(user.getPassword(), encryptPassword);
        } else {
            status = false;
        }
        return Result.SUCCESS();
    }

    //异步校验用户密码
    @GetMapping("/password/updateCheck")
    public Result updateCheck(@RequestBody UserVo vo) {
        //获取上下文request，获取token并解析
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader("Authorization");
        String tokenStr = "";
        try {
            // 把加过密的token 解密
            EncryptUtil encryptUtil = new EncryptUtil(RedisConst.TOKEN_CACHE_PREFIX);
            tokenStr = encryptUtil.decrypt(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        String username = "";
        if(com.smart.common.utils.StringUtils.isNotBlank(tokenStr)){
            // 从token 中获取附加的各种参数
            DecodedJWT jwt = JWT.decode(tokenStr);
            username = jwt.getClaim("username").asString();

        }
        String encryptPassword = MD5Util.encrypt(username, vo.getPassword());
        User user = userService.findByName(username);
        boolean status;
        if (user != null) {
            status = StringUtils.equals(user.getPassword(), encryptPassword);
        } else {
            status = false;
        }
        return Result.SUCCESS();
    }

    @PostMapping("/password")
    public Result updatePassword(@RequestBody UserVo vo){
        try {
            String username = StringUtils.lowerCase(vo.getUsername());
            String password = MD5Util.encrypt(username, vo.getOldPassword());
            //final String errorMessage = "用户名或密码错误";
            User user = this.userManager.getUser(username);
            if (!user.getPassword().equals(password)) {
                message = "原密码错误";
                return Result.FAIL(message);
            }
            if (!StringUtils.equals(user.getPassword(), password)) {
                message = "密码与原密码相同";
                return Result.FAIL(message);
            }
            userService.updatePassword(username, vo.getPassword(),vo.getOldPassword());
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "修改密码失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @PostMapping("/password/reset")
//    @RequiresPermissions("user:reset")
    public Result resetPassword(@RequestBody UserVo vo){
        try {
            Assert.notNull(vo.getUserNames(), "userNames不能为空");
            String[] usernameArr = vo.getUserNames().split(StringPool.COMMA);
            this.userService.resetPassword(usernameArr);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "重置用户密码失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @PostMapping("/excel")
//    @RequiresPermissions("user:export")
    public Result export(HttpServletRequest request, QueryRequest queryRequest, User user, HttpServletResponse response){
        try {
            List<User> users = this.userService.findUserDetail(request,user, queryRequest).getRecords();
            ExcelKit.$Export(User.class, response).downXlsx(users, false);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }
}
