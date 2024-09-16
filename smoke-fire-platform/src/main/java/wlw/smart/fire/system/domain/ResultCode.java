package wlw.smart.fire.system.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 公共返回码
 *
 * @author 三多
 * @Time 2020/3/10
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    //SUCCESS(true, 10000, "操作成功!"),
    SUCCESS(true, HttpStatus.OK.value(), "操作成功!"),
    SERVER_ERROR(false, 10004, "抱歉系统繁忙，请稍后重试!"),
    /************************系统错误返回码:1XXX***************************/
    FAIL(false, 10001, "操作失败!");

    /**
     * 操作是否成功
     */
    boolean Success;
    /**
     * 操作代码
     */
    int Code;
    /**
     * 提示信息
     */
    String Message;


}
