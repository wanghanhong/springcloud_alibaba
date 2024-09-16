package com.smart.system.auth.service;

import com.smart.common.constant.Constants;
import com.smart.common.constant.UserConstants;
import com.smart.common.enums.UserStatus;
import com.smart.common.exception.user.UserBlockedException;
import com.smart.common.exception.user.UserDeleteException;
import com.smart.common.exception.user.UserNotExistsException;
import com.smart.common.exception.user.UserPasswordNotMatchException;
import com.smart.common.log.publish.PublishFactory;
import com.smart.common.utils.DateUtils;
import com.smart.common.utils.IpUtils;
import com.smart.common.utils.MessageUtils;
import com.smart.common.utils.ServletUtils;
import com.smart.system.common.entity.SysUser;
import com.smart.system.common.feign.RemoteUserService;
import com.smart.system.common.util.PasswordUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 系统登录Service
 * @author: SanDuo
 * @date: 2020/5/27 10:44
 * @version: 1.0
 */
@Component
public class SysLoginService {

    @Autowired(required = false)
    private RemoteUserService userService;

    /**
     * 登录
     */
    public SysUser login(String username, String password) {
        // 验证码校验
        /*if
        (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,
                    Constants.LOGIN_FAIL,
                    MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }*/
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password)) {
            PublishFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null"));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            PublishFactory.recordLoginInfo(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            PublishFactory.recordLoginInfo(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }
        // 查询用户信息
        SysUser user = userService.selectSysUserByUsername(username);
        // 邮箱登录和手机号登录校验
        /*if (user == null && maybeMobilePhoneNumber(username)) {
            user = userService.selectUserByPhoneNumber(username);
        }
        if (user == null && maybeEmail(username)) {
            user = userService.selectUserByEmail(username);
        }*/
        if (user == null) {
            PublishFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists"));
            throw new UserNotExistsException();
        }
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            PublishFactory.recordLoginInfo(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.delete"));
            throw new UserDeleteException();
        }
        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            PublishFactory.recordLoginInfo(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.blocked", user.getRemark()));
            throw new UserBlockedException();
        }
        if (!PasswordUtil.matches(user, password)) {
            throw new UserPasswordNotMatchException();
        }
        PublishFactory.recordLoginInfo(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"));
        recordLoginInfo(user);
        return user;
    }

    /**
     * 校验邮箱
     *
     * @param emailName
     * @return
     */
    private boolean maybeEmail(String emailName) {
        if (!emailName.matches(UserConstants.EMAIL_PATTERN)) {
            return false;
        }
        return true;
    }

    /**
     * 校验电话号码
     *
     * @param phoneNumber
     * @return
     */
    private boolean maybeMobilePhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN)) {
            return false;
        }
        return true;
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUser user) {
        user.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserLoginRecord(user);
    }

    /**
     * 退出登录-日志记录
     * @param loginName
     */
    public void logout(String loginName) {
        PublishFactory.recordLoginInfo(loginName, Constants.LOGOUT, MessageUtils.message("user.logout.success"));
    }
}