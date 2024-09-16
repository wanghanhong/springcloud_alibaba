package com.smart.device.message.parse.fire.waterpress.pressure.wanlin.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.message.common.utils.DateUtils;
import com.smart.device.message.parse.fire.ParseStrategy;
import com.smart.device.message.parse.fire.waterpress.pressure.constant.PressureMapConstant;
import com.smart.device.message.parse.fire.waterpress.pressure.wanlin.entity.PressureEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 万林 压力表
 */
@Component
@Slf4j
public class PressureParseStrategy implements ParseStrategy<PressureEntity> {

    /**
     * @param
     * @return 返回值
     */
    @Override
    public PressureEntity assemblyData(String param) {
        JSONObject obj = JSONObject.parseObject(param);

        String notifyType = obj.getString(PressureMapConstant.NOTIFY_TYPE);
        String deviceId = obj.getString(PressureMapConstant.DEVICE_ID);

        JSONObject service = obj.getJSONObject(PressureMapConstant.SERVICE);
        JSONObject data = service.getJSONObject(PressureMapConstant.DATE);

        PressureEntity entity = new PressureEntity();
        // 报警时间

        entity.setEventTime(DateUtils.covertGMT8TimeStringT(service.getString(PressureMapConstant.EVENT_TIME)));
        entity.setServiceId(String.valueOf(service.get(PressureMapConstant.SERVICE_ID)));
        entity.setServiceType(String.valueOf(service.get(PressureMapConstant.SERVICE_TYPE)));

        String ReceivePiezoMeter = String.valueOf(data.get(service.get(PressureMapConstant.SERVICE_ID)));
       // NB900190186772603140297903000100400000NC
        if (ReceivePiezoMeter != null && ReceivePiezoMeter.length() == 40) {
            String devicemodel = ReceivePiezoMeter.substring(0, 6);
            String imei = ReceivePiezoMeter.substring(9, 24);
            String type = ReceivePiezoMeter.substring(24, 26);
            String lowValue = ReceivePiezoMeter.substring(26, 30);
            String hightValue = ReceivePiezoMeter.substring(30, 34);
            String presentValue = ReceivePiezoMeter.substring(34, 38);

            entity.setDeviceModel(devicemodel);
            entity.setImei(imei);
            entity.setType(type);
            entity.setLowValue(lowValue);
            entity.setHightValue(hightValue);
            entity.setPresentValue(presentValue);
        }
        log.info("万林NB压力表-参数接收" + JSONObject.toJSONString(entity));
        return entity;

    }


    public static void main(String[] args) {
        String data = "{\"notifyType\":\"deviceDataChanged\",\"deviceId\":\"e9d7261b-e1df-4adf-9ad7-0b7c59bc66b2\",\"gatewayId\":\"e9d7261b-e1df-4adf-9ad7-0b7c59bc66b2\",\"requestId\":null,\"service\":{\"serviceId\":\"ReceivePiezoMeter\",\"serviceType\":\"ReceivePiezoMeter\",\"data\":{\"ReceivePiezoMeter\":\"NB900190186772603140297903000100400000NC\"},\"eventTime\":\"20200515T023353Z\"}}";

        PressureParseStrategy strategy = new PressureParseStrategy();
        PressureEntity e = strategy.assemblyData(data);
        PressureStandar pressureStandar = new PressureStandar();

    }
}
