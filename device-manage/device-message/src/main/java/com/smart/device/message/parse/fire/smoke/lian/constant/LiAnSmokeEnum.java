package com.smart.device.message.parse.fire.smoke.lian.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 参考数据：
 * {"upPacketSN":-1,"upDataSN":-1,"topic":"v1/up/ad19prof","timestamp":1606982320277,"tenantId":"10461180","serviceId":"","protocol":"lwm2m","productId":"15014772","payload":{"serviceId":"notification","serviceData":{"TData":30,"SNR":1,"RSRP":114,"IMEI":"860374051096202","Cell_ID_Length":8,"Cell_ID":"38848339","CSQ":5,"Battery_Level":100,"Alarm_Status":2,"AlarmLevel":100}},"messageType":"dataReport","deviceType":"","deviceId":"acf7baaee65e431eadfb8f5fe50e8b81","assocAssetId":"","IMSI":"undefined","IMEI":"860374051096202"}
 *
 */
@Getter
@AllArgsConstructor
public enum LiAnSmokeEnum {

    upPacketSN,
    upDataSN,
    topic,
    timestamp,
    protocol,
    productId,
    payload,
    serviceId,
    serviceData,
    TData,
    SNR,
    RSRP,
    IMEI,
    Cell_ID_Length,
    Cell_ID,
    CSQ,
    Battery_Level,
    Alarm_Status,
    AlarmLevel,
    messageType,
    deviceType,
    deviceId,
    assocAssetId,
    IMSI;


}

