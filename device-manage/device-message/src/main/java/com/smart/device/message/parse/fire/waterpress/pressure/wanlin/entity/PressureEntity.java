package com.smart.device.message.parse.fire.waterpress.pressure.wanlin.entity;

import com.smart.device.message.parse.fire.smoke.entity.DeviceBaseInfo;
import lombok.Data;

import java.util.Date;

/**
 * 华强通信设备
 *
 * @author 三多
 * @Time 2020/4/8
 */
@Data
public class PressureEntity extends DeviceBaseInfo {


    /**
     * {"notifyType":"deviceDataChanged","deviceId":"e9d7261b-e1df-4adf-9ad7-0b7c59bc66b2",
     * "gatewayId":"e9d7261b-e1df-4adf-9ad7-0b7c59bc66b2",
     * "requestId":null,"service":{"serviceId":"ReceivePiezoMeter","serviceType":"ReceivePiezoMeter",
     * "data":{"ReceivePiezoMeter":"NB900190186772603140297903000100400000NC"},"eventTime":"20200515T023353Z"}}
     */

    /**
     * 设备事件类型
     * 事件推送信息
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
     * report 类型
     */
    private String serviceType;
    /**
     * report 类型
     */
    private String serviceId;
    /**
     * 报警时间
     */
    private Date eventTime;

    /**
     * 设备串号
     */
    private String imei;
    /**
     * 设备型号
     */
    private String deviceModel;
    /**
     * 报警类型
     */
    private String type;
    /**
     * 报警内容
     */
    private String content;
    /**
     * 设定最低值
     */
    private String lowValue;
    /**
     * 设定最高值
     */
    private String hightValue;
    /**
     * 当前值
     */
    private String presentValue;


}
