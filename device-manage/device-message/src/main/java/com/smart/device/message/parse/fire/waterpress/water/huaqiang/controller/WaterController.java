package com.smart.device.message.parse.fire.waterpress.water.huaqiang.controller;


import com.smart.device.message.factory.fire.DeviceParseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 三多
 * @Time 2020/5/9
 */
@RestController
public class WaterController {

    @Autowired
    private DeviceParseFactory deviceParseFactory;


    /**
     * 华强设备解析
     * <p>
     * {"timestamp":1589444231825,"tenantId":"10461180","protocol":"lwm2m","productId":"10053421","messageType":"deviceOnlineOfflineReport","iccid":"undefined","eventType":1,"deviceId":"44d0b9e48d404094bbbcf889e4e64ece"}
     * {"timestamp":1589444233258,"tenantId":"10461180","taskId":1,"result":{"resultDetail":"","resultCode":"DELIVERED"},"protocol":"lwm2m","productId":"10053421","messageType":"commandResponse","deviceId":"44d0b9e48d404094bbbcf889e4e64ece"}
     * {"upPacketSN":"","upDataSN":"","topic":"v1/up/ad","timestamp":1589444235861,"tenantId":"10461180","serviceId":"","protocol":"lwm2m","productId":"10053421","payload":{"APPdata":"UFUAPBAAAAYBAAMAJwMnuwpogDAwYGckCQYAAQAADjxkZAJVcVCnAAAAAAAAAEYAAAAAAAAAAAEDJ7sKAAAAAA=="},"messageType":"dataReport","deviceType":"","deviceId":"44d0b9e48d404094bbbcf889e4e64ece","assocAssetId":"","IMSI":"undefined","IMEI":"860803030676429"}


     接收参数处理{"upPacketSN":"","upDataSN":"","topic":"v1/up/ad","timestamp":1590657597046,"tenantId":"10461180","serviceId":"","protocol":"lwm2m","productId":"10053421","payload":{"APPdata":"UFUAPBAAAAYBAAMAJwM6PrpogDAwYGckCQYAAQAADgBkZAJVcVGaAAAAAAAAAEYAAAAAAAAAAAEDOj66AAAAAA=="},"messageType":"dataReport","deviceType":"","deviceId":"44d0b9e48d404094bbbcf889e4e64ece","assocAssetId":"","IMSI":"undefined","IMEI":"860803030676429"}

     */

    @GetMapping("/test")
    public String test() {

        String type = "3";
        String data = "{\"upPacketSN\":\"\",\"upDataSN\":\"\",\"topic\":\"v1/up/ad\",\"timestamp\":1589444235861,\"tenantId\":\"10461180\",\"serviceId\":\"\",\"protocol\":\"lwm2m\",\"productId\":\"10053421\",\"payload\":{\"APPdata\":\"UFUAPBAAAAYBAAMAJwMnuwpogDAwYGckCQYAAQAADjxkZAJVcVCnAAAAAAAAAEYAAAAAAAAAAAEDJ7sKAAAAAA==\"},\"messageType\":\"dataReport\",\"deviceType\":\"\",\"deviceId\":\"44d0b9e48d404094bbbcf889e4e64ece\",\"assocAssetId\":\"\",\"IMSI\":\"undefined\",\"IMEI\":\"860803030676429\"}";
        String result = deviceParseFactory.analysis(type, data);
        return result;
    }


}
