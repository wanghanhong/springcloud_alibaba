package com.smart.brd.sys.common;

import com.smart.brd.sys.common.exception.CustomException;
import com.smart.common.core.domain.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author dukzzz
 * @date 2021/5/21 17:16:下午
 * @desc
 */
@ControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result processExceptionError(Exception ex) {
        if (ex instanceof CustomException) {
            CustomException ex1 = (CustomException) ex;
            return new Result(ex1.getCode(), ex1.getMsg(),true);
        }
        return new Result(500,"服务器内部错误!");
    }
}
