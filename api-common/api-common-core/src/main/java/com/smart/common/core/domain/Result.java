package com.smart.common.core.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回响应对象
 *
 * @author 三多
 * @Time 2020/3/10
 */
@Data
@NoArgsConstructor
public class Result {
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 操作码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;

    /**
     * 构造
     *
     * @param resultCode ResultCode对象
     */
    public Result(ResultCode resultCode) {
        this.success = resultCode.success;
        this.code = resultCode.code;
        this.message = resultCode.message;
    }

    /**
     * 构造
     *
     * @param resultCode ResultCode对象
     * @param data       数据
     */
    public Result(ResultCode resultCode, Object data) {
        this.success = resultCode.success;
        this.code = resultCode.code;
        this.message = resultCode.message;
        this.data = data;
    }

    /**
     * 构造
     *
     * @param data data
     * @param data 数据
     */
    public Result(Object data) {
        this.success = ResultCode.SUCCESS.success;
        this.code = ResultCode.SUCCESS.code;
        this.message = ResultCode.SUCCESS.message;
        this.data = data;
    }

    /**
     * @param code    操作码
     * @param message 消息
     * @param success 成功标识
     */
    public Result(int code, String message, boolean success) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }
    /**
     * @param code    操作码
     * @param message 消息
     * @param success 成功标识
     */
    public Result(int code, String message, boolean success, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }
    /**
     * 成功
     *
     * @return
     */
    public static Result SUCCESS() {
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 成功
     *
     * @param data 返回数据
     * @return
     */
    public static Result SUCCESS(Object data) {
        return new Result(data);
    }

    public static Result SUCCESS(String message,Object data) {
        return new Result(data);
    }
    /**
     * 服务器错误
     *
     * @return Result
     */
    public static Result ERROR() {
        return ERROR(ResultCode.SERVER_ERROR);
    }

    /**
     * 自定义错误消息
     *
     * @return Result
     */
    public static Result ERROR(ResultCode resultCode) {
        return ERROR(resultCode,null);
    }

    /**
     * 自定义错误消息
     * @param resultCode
     * @param message
     * @return
     */
    public static Result ERROR(ResultCode resultCode,Object message) {
        return new Result(resultCode,message);
    }

    /**
     * 失败
     *
     * @return Result
     */
    public static Result FAIL() {
        return new Result(ResultCode.FAIL);
    }

    public static Result FAIL(String message) {
        return FAIL(ResultCode.FAIL.code, message);
    }

    public static Result FAIL(int code, String message) {
        return new Result(code, message);
    }

}
