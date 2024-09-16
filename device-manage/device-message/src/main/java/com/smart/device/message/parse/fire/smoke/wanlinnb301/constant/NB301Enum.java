package com.smart.device.message.parse.fire.smoke.wanlinnb301.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 参考数据：
 * {"notifyType":"deviceDataChanged","deviceId":"10c60373-7a77-4712-883b-b1aaa31d18cc",
 * "gatewayId":"10c60373-7a77-4712-883b-b1aaa31d18cc","requestId":null,
 * "service":{"serviceId":"WPS","serviceType":"WPS","data":
 * {"Data":"N311V31I865815040244829H89861118280005212549X10T28U99S18"},
 * "eventTime":"20200520T021156Z"}}
 *
 * @author 三多
 * @Time 2020/4/9
 */

@Getter
@AllArgsConstructor
public enum NB301Enum {
    TYPE, GEN, VER;
}
