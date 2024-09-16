package com.smart.device.access.entity.vo;

import lombok.Data;

/**
 * ctwing烟感设备实体
 *
 * @author SanDuo
 * @since 2020-04-02 16:15:58
 */
@Data
public class SdDeviceCtWingVO {
    private static final long serialVersionUID = -97522868494947073L;

    /**
     * 设备名称--必填
     */
    private String deviceName;
    // 设备编号，MQTT,T_Link,TCP,HTTP,JT/T808，南向云协议必填
    private String deviceSn;

    //imei号，LWM2M,NB网关协议必填
    private String imei;

    //操作者--必填
    private String operator;


    private String deviceId;

    /**
     * 设备类型11 烟感
     */
    private Integer deviceType;

    //总长度不超过15位，使用0~9的数字，String类型,选填
    private String imsi;


    //产品ID
    private String productId;

    //0.自动订阅 1.取消自动订阅，必填
    private Integer autoObserver;

    //由大小写字母加0-9数字组成的16位字符串,选填
    private String pskValue;

    //设备类型  1  烟感   2 气感
    private Integer type;

    //批量查询设备查询条件  选填
    private String searchValue;

    //当前页   选填
    private Integer pageNow;
    //每页记录数  最大40条  选填
    private Integer pageSize;

}