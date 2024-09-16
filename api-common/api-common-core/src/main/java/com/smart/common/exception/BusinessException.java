package com.smart.common.exception;

import com.smart.common.exception.base.BaseException;

/**
 * 业务异常类
 * @author 三多
 * @Time 2020/7/13
 */
public class BusinessException extends BaseException {
    public BusinessException(String defaultMessage) {
        super(defaultMessage);
    }
}
