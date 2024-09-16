package com.smart.device.message.parse.fire.smoke.lian.entity;


import lombok.Data;

@Data
public class LiAnSmokeEntity {

    /**
     *     {"upPacketSN":-1,"upDataSN":-1,"topic":"v1/up/ad19prof","timestamp":1606982320277,"tenantId":"10461180","serviceId":"","protocol":"lwm2m","productId":"15014772","payload":{"serviceId":"notification","serviceData":{"TData":30,"SNR":1,"RSRP":114,"IMEI":"860374051096202","Cell_ID_Length":8,"Cell_ID":"38848339","CSQ":5,"Battery_Level":100,"Alarm_Status":2,"AlarmLevel":100}},"messageType":"dataReport","deviceType":"","deviceId":"acf7baaee65e431eadfb8f5fe50e8b81","assocAssetId":"","IMSI":"undefined","IMEI":"860374051096202"}
     *     　　
     *
     *
     */

    private String notifyType;

    private String upPacketSN;
    private String upDataSN;
    private String topic;
    private String timestamp;
    private String protocol;
    private String productId;

    private String messageType;
    private Integer deviceType;
    private String deviceId;
    private String assocAssetId;
    private String iMEI;
    private String iMSI;

    private String payload;
    private String  serviceId;
    private String serviceData;
    private String tData;
    private String sNR;
    private String rSRP;
    private String cell_ID_Length;
    private String cell_ID;
    private String cSQ;
    private String battery_Level;
    private String alarm_Status;
    private String  alarmLevel;

}
