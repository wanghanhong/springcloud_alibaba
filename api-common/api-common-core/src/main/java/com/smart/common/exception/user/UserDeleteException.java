package com.smart.common.exception.user;

/**
 * @description: 用户账号已被删除
 * @author: SanDuo
 * @date: 2020/5/27 10:55
 * @version: 1.0
 */
public class UserDeleteException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserDeleteException() {
        super("user.password.delete", null);
    }
}
