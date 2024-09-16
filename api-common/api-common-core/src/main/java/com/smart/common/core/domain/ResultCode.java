package com.smart.common.core.domain;

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
    /************************系统错误返回码:1XXX***************************/
    FAIL(false, 10001, "操作失败!"),
    UN_AUTHENTICATED(false, 10002, "您还未登录!"),
    UN_AUTHORISE(false, 10003, "没有访问权限!"),
    SERVER_ERROR(false, 10004, "抱歉系统繁忙，请稍后重试!"),
    SERVER_ERROR_HYSTRIX(false, 10005, "服务已被降级熔断"),
    SQL_ERROR(false, 10006 ,"SQL异常!"),
    PARAM_VALIDATE_ERROR(false, 10007 ,"参数校验失败!"),
    /************************用户操作返回码:2XXX***************************/
    MOBILE_ERROR_OR_PASSWORD_ERROR(false, 20001, "用户名或者密码错误！"),
    /************************企业操作返回码   ：3XXXX***************************/
    /************************权限操作返回码   : 4XXXX***************************/
    /************************设备基础操作返回码: 51XXX***************************/
    /************************设备消息操作返回码: 52XXX***************************/
    /************************宣传模块操作返回码: 53XXX***************************/
    /************************其他操作返回码   : XXXXX***************************/

    ERROR_DEVICE_ACCESS_ERROR(false, 51000, "操作失败!"),
    ERROR_DEVICE_ACCESS_SMOKE(false, 51001, "烟感设备操作失败!"),
    ERROR_DEVICE_ACCESS_GAS(false, 51002, "气感设备操作失败!"),
    ERROR_DEVICE_ACCESS_WATERPRESS(false, 51003, "水利设备操作失败!"),
    ERROR_DEVICE_ACCESS_ELECTRIC(false, 51004, "电力设备操作失败!"),
    ERROR_DEVICE_ACCESS_CAMERAS(false, 51005, "摄像头设备操作失败!"),

    PUBLICIZE_UPDATE_FAIL(false, 53001, "更新失败!"),
    PUBLICIZE_DELETE_FAIL(false, 53002, "删除失败!"),
    PUBLICIZE_EXAMINE_FAIL(false, 53003, "审核失败!"),
    PUBLICIZE_ADD_FAIL(false, 53004, "添加失败!"),
    PUBLICIZE_NOUS_NAME(false, 53005, "消防常识名称不能为空!"),
    PUBLICIZE_NOUS_CONTENT(false, 53006, "消防常识内容不能为空!"),
    PUBLICIZE_NOUS_FILE_FAIL(false, 53007, "文件上传出错!"),
    PUBLICIZE_PARAM_FAIL(false, 53008, "参数校验失败!"),
    PUBLICIZE_NOT_EXITS(false, 53009, "信息不存在!"),

    ERROR_DEVICE_INSTALL_SMOKE(false, 54001, "烟感设备操作失败!"),
    ERROR_DEVICE_INSTALL_GAS(false, 54002, "气感设备操作失败!"),
    ERROR_DEVICE_INSTALL_WATERPRESS(false, 54003, "水利设备操作失败!"),
    ERROR_DEVICE_INSTALL_LIQUIDLEVEL(false, 54003, "液位设备操作失败!"),
    ERROR_DEVICE_INSTALL_ELECTRIC(false, 54004, "电力设备操作失败!"),
    ERROR_DEVICE_INSTALL_CAMERAS(false, 54005, "摄像头设备操作失败!"),

    ERROR_MANAGER_BUILDING(false, 54101, "建筑物操作失败!"),
    ERROR_MANAGER_COMPANY(false, 54102, "单位操作失败!"),
    ERROR_MANAGER_PRODUCT(false, 54103, "厂商操作失败!"),
    ERROR_MANAGER_PIC(false, 54104, "图片操作失败!"),
    ERROR_MANAGER_FIREHYDRANT(false, 54105, "消防栓操作失败!"),

    ERROR_MANAGER_FIREFIGHTER(false, 54111, "消防维保人员操作失败!"),
    ERROR_MANAGER_INSPECTION(false, 54112, "巡检计划操作失败!"),
    ERROR_MANAGER_HIS_INSPECTION(false, 54113, "巡检记录操作失败!"),


    ERROR_MESSAGE_MANAGER_PRODUCT(false, 56001, "消息生产者操作失败!"),
    ERROR_MESSAGE_MANAGER_CONSUMER(false, 56001, "消息消费者操作失败!"),

    ERROR_DEVICE_MONITOR(false, 57001, "联网监控操作失败!"),
    ;
    /**
     * 操作是否成功
     */
    Boolean success;
    /**
     * 操作代码
     */
    int code;
    /**
     * 提示信息
     */
    String message;


}
