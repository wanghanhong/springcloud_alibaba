package com.smart.common.exception.user;

/**
 * @description: 用户不存在异常类
 * @author: SanDuo
 * @date: 2020/5/27 10:56
 * @version: 1.0
 */
public class UserNotExistsException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserNotExistsException() {
        super("user.not.exists", null);
    }
}
