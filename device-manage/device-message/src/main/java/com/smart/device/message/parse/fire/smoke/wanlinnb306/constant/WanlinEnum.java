package com.smart.device.message.parse.fire.smoke.wanlinnb306.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 参考数据：
 * {"notifyType":"deviceDataChanged","service":
 * *     "deviceId":"e917e013-044b-4f32-8b53-588ef68c185a",
 * *     "gatewayId":"e917e013-044b-4f32-8b53-588ef68c185a"
 * {"serviceType":"Report","  eventTime":"20200401T182505Z","serviceId":"Report"
 * data":
 * {"devId":"10009305","electricQuantity":100,"devState":0,"temperature":219,
 * "signal":12,"cmdValue":"10","smokescope":0},"
 * } }
 *
 * @author 三多
 * @Time 2020/4/9
 */
@Getter
@AllArgsConstructor
public enum WanlinEnum {
    NOTIFY_TYPE, DEVICE_ID, SERVICE, EVENT_TIME, SERVICE_TYPE,
    DATA, ELECTRIC_QUANTITY, DEV_STATE, DEV_ID, TEMPERATURE, SIGNAL,
    CMDVALUE, SMOKE_SCOPE, TYPE;
}

