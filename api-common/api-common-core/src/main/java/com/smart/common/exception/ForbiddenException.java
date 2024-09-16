package com.smart.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @description: 权限不足
 * @author: SanDuo
 * @date: 2020/5/23 18:09
 * @version: 1.0
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "forbidden")
public class ForbiddenException extends RuntimeException {
    /***/
    private static final long serialVersionUID = -4552488542483342775L;

    private static final String DEFAULT_MSG = "forbidden";

    public ForbiddenException(String msg) {
        super(msg);
    }

    public ForbiddenException() {
        super(DEFAULT_MSG);
    }
}
