package com.smart.common.exception.user;

/**
 * @description: 用户密码不正确或不符合规范异常类
 * @author: SanDuo
 * @date: 2020/5/27 10:56
 * @version: 1.0
 */
public class UserPasswordNotMatchException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
