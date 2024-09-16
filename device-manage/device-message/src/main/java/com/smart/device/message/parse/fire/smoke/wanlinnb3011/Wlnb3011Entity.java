package com.smart.device.message.parse.fire.smoke.wanlinnb3011;

import com.smart.device.message.parse.fire.smoke.entity.DeviceBaseInfo;
import lombok.Data;

/**
 * 万林可燃其他（甲烷）
 *
 * @author 三多
 * @Time 2020/4/8
 */
@Data
public class Wlnb3011Entity extends DeviceBaseInfo {
    /**
     * {"deviceCode":"311865815040244829","deviceType":"311","deviceStatus":"2","netStatus":"1","time":1604538938,"content":"",
     * "detail":{"edition":"3.1","event":"10","temperature":"20","bat_capacity":"84","signal_intensity":"17","iccid":"89860317452041182488"}}
     *
     */
    /**
     * 设备客户端ID
     */
    private String clientId;
    /**
     * 设备编码
     */
    private String deviceCode;

    private String event;
     /**
     * 上报时间
     */
    private Long eventdate;
    /**
     * 报警内容
     */
    private String content;

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
    private String signal_intensity;
    /**
     * 2登录 10事件 f报警恢复 7 复位
     */
    private String cmdValue;
    /**
     * 烟雾浓度
     */
    private String smokeScope;


}
