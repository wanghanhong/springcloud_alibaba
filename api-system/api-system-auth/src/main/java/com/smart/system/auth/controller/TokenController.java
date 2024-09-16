package com.smart.system.auth.controller;

import com.smart.common.core.domain.R;
import com.smart.system.auth.form.LoginForm;
import com.smart.system.auth.service.AccessTokenService;
import com.smart.system.auth.service.SysLoginService;
import com.smart.system.common.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: Token处理类
 * @author: SanDuo
 * @date: 2020/5/27 10:33
 * @version: 1.0
 */
@RestController
public class TokenController {
    @Autowired
    private AccessTokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    /**
     * 登录
     * @param form
     * @return
     */
    @PostMapping("login")
    public R login(@RequestBody LoginForm form) {
        // 用户登录
        SysUser user = sysLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return R.ok(tokenService.createToken(user));
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @PostMapping("logout")
    public R logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser user = tokenService.queryByToken(token);
        if (null != user) {
            sysLoginService.logout(user.getLoginName());
            tokenService.expireToken(user.getUserId());
        }
        return R.ok();
    }
}
