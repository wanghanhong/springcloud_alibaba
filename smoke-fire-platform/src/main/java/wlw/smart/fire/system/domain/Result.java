package wlw.smart.fire.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("Success")
    private Boolean Success;
    /**
     * 操作码
     */
    @JsonProperty("Code")
    private Integer Code;
    /**
     * 消息
     */
    @JsonProperty("Message")
    private String Message;
    /**
     * 数据
     */
    @JsonProperty("Data")
    private Object Data;

    /**
     * 构造
     *
     * @param resultCode ResultCode对象
     */
    public Result(ResultCode resultCode) {
        this.Success = resultCode.Success;
        this.Code = resultCode.Code;
        this.Message = resultCode.Message;
    }

    /**
     * 构造
     *
     * @param resultCode ResultCode对象
     * @param Data       数据
     */
    public Result(ResultCode resultCode, Object Data) {
        this.Success = resultCode.Success;
        this.Code = resultCode.Code;
        this.Message = resultCode.Message;
        this.Data = Data;
    }

    /**
     * 构造
     *
     * @param Data Data
     * @param Data 数据
     */
    public Result(Object Data) {
        this.Success = ResultCode.SUCCESS.Success;
        this.Code = ResultCode.SUCCESS.Code;
        this.Message = ResultCode.SUCCESS.Message;
        this.Data = Data;
    }

    /**
     * @param Code    操作码
     * @param Message 消息
     * @param Success 成功标识
     */
    public Result(int Code, String Message, boolean Success) {
        this.Success = Success;
        this.Code = Code;
        this.Message = Message;
    }
    /**
     * @param Code    操作码
     * @param Message 消息
     * @param Success 成功标识
     */
    public Result(int Code, String Message, boolean Success, Object Data) {
        this.Success = Success;
        this.Code = Code;
        this.Message = Message;
        this.Data = Data;
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
     * @param Data 返回数据
     * @return
     */
    public static Result SUCCESS(Object Data) {
        return new Result(Data);
    }

    public static Result SUCCESS(String Message, Object Data) {
        return new Result(Data);
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
     * @param Message
     * @return
     */
    public static Result ERROR(ResultCode resultCode, Object Message) {
        return new Result(resultCode,Message);
    }

    /**
     * 失败
     *
     * @return Result
     */
    public static Result FAIL() {
        return new Result(ResultCode.FAIL);
    }

    public static Result FAIL(String Message) {
        return FAIL(ResultCode.FAIL.Code, Message);
    }

    public static Result FAIL(int Code, String Message) {
        return new Result(Code, Message, false);
    }

}
