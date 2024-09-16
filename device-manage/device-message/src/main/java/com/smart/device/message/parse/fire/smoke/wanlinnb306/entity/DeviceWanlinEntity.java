package com.smart.device.message.parse.fire.smoke.wanlinnb306.entity;


import lombok.Data;

@Data
public class DeviceWanlinEntity {

    /**
     * {"notifyType":"deviceDataChanged","service":{"serviceType":"Report","data":{"devId":"10009305","electricQuantity":100,"devState":0,"temperature":219,"signal":12,"cmdValue":"10","smokescope":0},"eventTime":"20200401T182505Z","serviceId":"Report"},"deviceId":"e917e013-044b-4f32-8b53-588ef68c185a","gatewayId":"e917e013-044b-4f32-8b53-588ef68c185a"}
     */


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
    private String eventTime;


    /**
     * 电信网关设备ID
     */
    private String devId;
    /**
     * 电池电量
     */
    private String electricQuantity;
    /**
     * 1发生火警 2温度过高 3设备被拆 4低电 f报警恢复 5 测试
     */
    private String devState;
    /**
     * 当前温度
     */
    private String temperature;
    /**
     * 信号强度
     */
    private String signal;
    /**
     * 2登录 10事件 f报警恢复 7 复位
     */
    private String cmdValue;
    /**
     * 烟雾浓度
     */
    private String smokeScope;


}
