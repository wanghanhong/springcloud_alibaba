package com.smart.brd.sys.system.controller;

import cn.hutool.http.HttpStatus;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.smart.brd.sys.system.usertoken.constant.RedisConst;
import com.smart.common.utils.EncryptUtil;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smart.brd.sys.common.annotation.Log;
import com.smart.brd.sys.common.controller.BaseController;
import com.smart.brd.sys.common.domain.FebsResponse;
import com.smart.brd.sys.common.domain.QueryRequest;
import com.smart.brd.sys.common.exception.FebsException;
import com.smart.brd.sys.common.utils.MD5Util;
import com.smart.brd.sys.system.domain.po.User;
import com.smart.brd.sys.system.domain.po.UserConfig;
import com.smart.brd.sys.system.domain.vo.UserVo;
import com.smart.brd.sys.system.manager.UserManager;
import com.smart.brd.sys.system.service.UserConfigService;
import com.smart.brd.sys.system.service.UserService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author Pano
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    private Integer error_code = 10001;
    private String message;

    @Resource
    private UserService userService;
    @Resource
    private UserConfigService userConfigService;
    @Resource
    private UserManager userManager;


    @GetMapping("check/{username}")
    public FebsResponse checkUserName(@NotBlank(message = "{required}") @PathVariable String username) {
        boolean status = this.userService.findByName(username) == null;
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(status);
    }

    @GetMapping("/{username}")
    public FebsResponse detail(@NotBlank(message = "{required}") @PathVariable String username) {
        User user = this.userService.findByName(username);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(user);
    }

    @GetMapping
//    @RequiresPermissions("user:view")
    public Map<String, Object> userList(HttpServletRequest request,QueryRequest queryRequest, User user) {
        Map<String, Object> dataTable = getDataTable(userService.findUserDetail(request,user, queryRequest));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
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
     * @throws FebsException
     */
    @Log("新增用户")
    @PostMapping
    //   @RequiresPermissions("user:add")
    public FebsResponse addUser(HttpServletRequest request,@RequestBody User user) throws FebsException {
        User bean = userService.findByName(user.getUsername());
            try {
                user.setStatus("1");
                user.setUsername(user.getUsername());
                user.setTenantId(tenantId);
                this.userService.createUser(request,user);
                return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
            } catch (Exception e) {
                log.error(message, e);
                return new FebsResponse().code(error_code).message(e.getMessage());
            }
    }

    @Log("修改用户")
    @PutMapping
//    @RequiresPermissions("user:update")
    public FebsResponse updateUser(HttpServletRequest request,@RequestBody User user) throws FebsException {
        try {
            this.userService.updateUser(request,user);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            log.error(message, e);
            return new FebsResponse().code(error_code).message(e.getMessage());
        }
    }

    @Log("用户状态修改")
    @PutMapping("/status")
//    @RequiresPermissions("user:update")
    public FebsResponse changeStatus(@RequestBody UserVo vo) throws FebsException {
        try {
            this.userService.changeStatus(vo);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "用户状态修改失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @Log("删除用户")
    @DeleteMapping("/{userIds}")
//    @RequiresPermissions("user:delete")
    public FebsResponse deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) throws FebsException {
        try {
            String[] ids = userIds.split(StringPool.COMMA);
            for(int i=0;i<ids.length;i++){
                Long id = Long.parseLong(ids[i]);
                if(id < 0){
                    return new FebsResponse().code(HttpStatus.HTTP_OK).message("包含系统用户，请联系管理员删除");
                }
            }
            this.userService.deleteUsers(ids);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "删除用户失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @PutMapping("profile")
    public FebsResponse updateProfile(@RequestBody User user) throws FebsException {
        try {
            this.userService.updateProfile(user);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改个人信息失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @PutMapping("avatar")
    public FebsResponse updateAvatar(@RequestBody UserVo vo) throws FebsException {
        try {
            this.userService.updateAvatar(vo.getUsername(), vo.getAvatar());
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改头像失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @PutMapping("userConfig")
    public FebsResponse updateUserConfig(@RequestBody UserConfig userConfig) throws FebsException {
        try {
            this.userConfigService.update(userConfig);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改个性化配置失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @GetMapping("password/check")
    public FebsResponse checkPassword(@RequestBody UserVo vo) {
        String encryptPassword = MD5Util.encrypt(vo.getUsername(), vo.getPassword());
        User user = userService.findByName(vo.getUsername());
        boolean status;
        if (user != null) {
            status = StringUtils.equals(user.getPassword(), encryptPassword);
        } else {
            status = false;
        }
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(status);
    }

    //异步校验用户密码
    @GetMapping("password/updateCheck")
    public FebsResponse updateCheck(@RequestBody UserVo vo) {
        //获取上下文request，获取token并解析
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = "";
        if (requestAttributes!=null) {
            HttpServletRequest request = requestAttributes.getRequest();
             token = request.getHeader("Authorization");
        }
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
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(status);
    }

    @PutMapping("password")
    public FebsResponse updatePassword(@RequestBody UserVo vo) throws FebsException {
        try {
            String username = org.apache.commons.lang3.StringUtils.lowerCase(vo.getUsername());
            String password = MD5Util.encrypt(username, vo.getOldPassword());
            //final String errorMessage = "用户名或密码错误";
            User user = this.userManager.getUser(username);
            if (!user.getPassword().equals(password)) {
                message = "原密码错误";
                return new FebsResponse().code(error_code).message(message);
            }
            if (!StringUtils.equals(user.getPassword(), password)) {
                message = "密码与原密码相同";
                return new FebsResponse().code(error_code).message(message);
            }
            userService.updatePassword(username, vo.getPassword(),vo.getOldPassword());
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改密码失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @PutMapping("password/reset")
//  @RequiresPermissions("user:reset")
    public FebsResponse resetPassword(@RequestBody UserVo vo) throws FebsException {
        try {
            Assert.notNull(vo.getUserNames(), "userNames不能为空");
            String[] usernameArr = vo.getUserNames().split(StringPool.COMMA);
            this.userService.resetPassword(usernameArr);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "重置用户密码失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @PostMapping("excel")
//    @RequiresPermissions("user:export")
    public FebsResponse export(HttpServletRequest request,QueryRequest queryRequest, User user, HttpServletResponse response) throws FebsException {
        try {
            List<User> users = this.userService.findUserDetail(request,user, queryRequest).getRecords();
            ExcelKit.$Export(User.class, response).downXlsx(users, false);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }
}
