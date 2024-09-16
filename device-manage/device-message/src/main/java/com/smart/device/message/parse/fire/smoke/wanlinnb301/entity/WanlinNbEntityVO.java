package com.smart.device.message.parse.fire.smoke.wanlinnb301.entity;

import lombok.Data;

@Data
public class WanlinNbEntityVO {

    /**
     * 设备事件类型
     */
    private String notifyType;
    /**
     * 设备deviceID
     */
    private String deviceId;
    /**
     * 设备 网关
     */
    private String gatewayId;
    /**
     * 请求 ID
     */
    private String requestId;
    /**
     * service 类型
     */
    private String serviceType;
    /**
     * service ID
     */
    private String serviceId;
    /**
     * 产品类型
     */
    private String Type;
    /**
     * 设备所属
     */
    private String gen;
    /**
     * 软件版本号
     */
    private String version;
    /**
     * 设备编码
     */
    private String imei;
    /**
     * ICCID
     */
    private String iccid;
    /**
     * 电池电量  0-40 范围 十进制
     */
    private String electricQuantity;
    /**
     * X=状态 10 自检； 01 报警； 02 低电； 11 恢复
     */
    private String status;
    /**
     * 当前温度  0-50 范围 十进制
     */
    private String temperature;
    /**
     * 信号强度  0-40 范围 十进制
     */
    private String signal;
    /**
     * 报警时间
     */
    private String eventTime;

}
