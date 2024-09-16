package com.smart.common.exception.user;

import com.smart.common.exception.base.BaseException;

/**
 * @description: 用户信息异常类
 * @author: SanDuo
 * @date: 2020/5/27 10:52
 * @version: 1.0
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
