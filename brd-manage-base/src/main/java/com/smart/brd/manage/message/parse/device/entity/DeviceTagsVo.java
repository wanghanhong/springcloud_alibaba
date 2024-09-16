package com.smart.brd.manage.message.parse.device.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author junglelocal
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DeviceTagsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 固定GPS位置信息
     */
    private String gps;

    /**
     * 服务器key码
     */
    private String key;

    /**
     * 标签号码
     */
    private String tagsId;

    /**
     * 发送时间的时间戳
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date eventTime;

    /**
     * 标签的电池电压
     */
    private String batteryPower;

    /**
     * 体温温度范围30~42℃
     */
    private String temperature;

    /**
     * 环境温度
     */
    private String envTemperature;

    /**
     * 接收标签的信息pcb序号
     */
    private String stationId;

    /**
     * 合盖状态
     */
    private String closingFlag;

    /**
     * 拆盒盖次数0~99
     */
    private Integer damage;

    /**
     * 电池电压1.9V~3.3V
     */
    private Double bat;

    /**
     * 标签信号：0代表信号正常；1代表信号弱或者异常
     */
    private String rssi;

    /**
     * 发送时间间隔30分钟
     */
    private Integer intervals;

    /**
     * 本网关MAC地址
     */
    private String mac;

    /**
     * 使用上网方式：WiFi、4G、有线三种
     */
    private String netmode;

    /**
     *  通讯公网通讯的信号值
     */
    private Integer lterssi;

    /**
     * 距离（取值范围 : 0-100）
     */
    private Integer distance;

    /**
     * 步数（取值范围 : 0-9999999）
     */
    private Long walk;
}
