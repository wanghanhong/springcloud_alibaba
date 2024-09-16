package com.smart.device.message.parse.fire.temp.wanlin.entity;

import lombok.Data;

@Data
public class TempEntity {

    /**
     * 当前湿度
     */
    private String humidity;

    /**
     *设备编号
     */
    private String mac;

    /**
     *ICCID
     */
    private String iccid;

    /**
     *客户端ID
     */
    private String clientId;

    /**
     *事件编号
     */
    private String event;

    /**
     * 事件发生时间
     */
    private String eventDate;

    /**
     * 事件内容
     */
    private String content;

    /**
     * 最高湿度
     */
    private String humidHigh;

    /**
     * 最低湿度
     */
    private String humidLow;
}
