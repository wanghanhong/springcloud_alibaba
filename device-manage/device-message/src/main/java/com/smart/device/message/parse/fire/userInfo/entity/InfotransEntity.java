package com.smart.device.message.parse.fire.userInfo.entity;

import com.smart.device.message.parse.fire.smoke.entity.DeviceBaseInfo;
import lombok.Data;

import java.util.List;

/**
 * 用户信息传输装置解析
 */
@Data
public class InfotransEntity extends DeviceBaseInfo {

    private Integer upPacketSN;

    private Integer upDataSN;

    private String topic;

    private Long timestamp;
    private Long imei;

    private String serviceId;

    private String deviceId;

    private String assocAssetId;

    private Integer deviceType;

    private String messageType;

    private String APPdata;
    /*
    * 命名名称
    * */
    private Integer c;

    /*
    * 命令ID
    * */
    private Long id;

    /*
    * 时间戳，毫秒数
    * */
    private Long tm;

    /*
    * 消防设施部件
    * */
    private String partId;

    /*
    * 状态值
    * */
    private String v;

    /*
    * 集合
    * */
    private List b;

    private String IMSI;

    private String tenantId;

    private String productId;

    private String protocol;

    private String payload;


}
