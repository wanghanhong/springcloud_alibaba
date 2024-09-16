package com.smart.common.exception;

import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * 全局异常
 *
 * @author 三多
 * @Time 2020/7/10
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    public Result httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return Result.FAIL("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result notFountExceptionHandler(RuntimeException e) {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        log.error("运行时异常:", e);
        return Result.FAIL("运行时异常:" + e.getMessage());
    }

    /**
     * 重复键异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result duplicateKeyExceptionHandler(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return Result.FAIL("数据库中已存在该记录");
    }

    /**
     * 校验绑定异常
     * 处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(BindException.class)
    public Result bindExceptionHandler(BindException e) throws Exception {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
        return Result.ERROR(ResultCode.PARAM_VALIDATE_ERROR, message);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是ConstraintViolationException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result constraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
        return Result.ERROR(ResultCode.PARAM_VALIDATE_ERROR, message);
    }

    /**
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
        //下边ResultCodeEnum.PARAMS_BS_ERROR.getCode()就是你自己自定义的返回code码
        return Result.ERROR(ResultCode.PARAM_VALIDATE_ERROR, message);
    }


    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) throws Exception {
        log.error(e.getMessage(), e);
        return Result.FAIL("服务器错误，请联系管理员");
    }

    /**
     * 捕获并处理未授权异常
     *
     * @param e 授权异常
     * @return 统一封装的结果类, 含有代码code和提示信息msg
     */
    @ExceptionHandler(UnauthorizedException.class)
    public Result unauthorizedExceptionHandler(UnauthorizedException e) {
        return Result.FAIL(401, e.getMessage());
    }

    /**
     * 验证码错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ValidateCodeException.class)
    public Result captchaHandler(ValidateCodeException e) {
        return Result.FAIL(e.getMessage());
    }

    /**
     * SQL 异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(SQLException.class)
    public Result sqlExceptionHandler(SQLException e) {
        return Result.ERROR(ResultCode.SQL_ERROR);
    }
}
