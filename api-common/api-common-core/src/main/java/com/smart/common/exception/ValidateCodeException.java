package com.smart.common.exception;

/**
 * @author 三多
 * @Time 2020/5/22
 */
public class ValidateCodeException extends Exception {
    /**
     * 无参构造
     */
    public ValidateCodeException() {
    }

    /**
     * 有参
     *
     * @param message
     */
    public ValidateCodeException(String message) {
        super(message);
    }
}
