package com.smart.common.exception.user;

/**
 * @description: 用户锁定异常类
 * @author: SanDuo
 * @date: 2020/5/27 10:54
 * @version: 1.0
 */
public class UserBlockedException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserBlockedException() {
        super("user.blocked", null);
    }
}
