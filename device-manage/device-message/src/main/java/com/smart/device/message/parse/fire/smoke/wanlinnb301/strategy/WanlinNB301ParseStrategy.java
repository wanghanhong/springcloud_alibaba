package com.smart.device.message.parse.fire.smoke.wanlinnb301.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.message.parse.fire.ParseStrategy;
import com.smart.device.message.parse.fire.smoke.wanlinnb301.constant.NB301Enum;
import com.smart.device.message.parse.fire.smoke.wanlinnb301.constant.WanlinNB301MapConstant;
import com.smart.device.message.parse.fire.smoke.wanlinnb301.entity.WanlinNbEntity;
import com.smart.device.message.parse.fire.smoke.wanlinnb301.entity.WanlinNbEntityVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Component
@Slf4j
public class WanlinNB301ParseStrategy implements ParseStrategy<WanlinNbEntity> {

    /**
     * {"notifyType":"deviceDataChanged","deviceId":"10c60373-7a77-4712-883b-b1aaa31d18cc",
     * "gatewayId":"10c60373-7a77-4712-883b-b1aaa31d18cc","requestId":null,
     * "service":{"serviceId":"WPS","serviceType":"WPS","data":
     * {"Data":"N311V31I865815040244829H89861118280005212549X10T28U99S18"},
     * "eventTime":"20200520T021156Z"}}
     * 将万林数据 整合为万林对象
     *
     * @param param str
     * @return entity
     */
    @Override
    public WanlinNbEntity assemblyData(String param) {
        JSONObject obj = JSONObject.parseObject(param);

        JSONObject service = obj.getJSONObject(WanlinNB301MapConstant.SERVICE);
        JSONObject data = service.getJSONObject(WanlinNB301MapConstant.DATA);

        WanlinNbEntity entity = new WanlinNbEntity();

        //事件类型
        entity.setNotifyType(obj.getString(WanlinNB301MapConstant.NOTIFY_TYPE));
        //设备ID
        entity.setDeviceId(obj.getString(WanlinNB301MapConstant.DEVICE_ID));
        //设备网关ID
        entity.setGatewayId(obj.getString(WanlinNB301MapConstant.GATEWAY_ID));
        //请求ID
        entity.setRequestId(obj.getString(WanlinNB301MapConstant.REQUEST_ID));

        //服务ID
        entity.setServiceId(service.getString(WanlinNB301MapConstant.SERVICE_ID));
        //服务类型
        entity.setServiceType(service.getString(WanlinNB301MapConstant.SERVICE_TYPE));
        //实际数据
        String nbData = data.getString(WanlinNB301MapConstant.DATA_IN);
        if (nbData != null && nbData.length() == 56) {
            //型号
            entity.setGen(nbData.substring(0, 4));
            //版本
            entity.setVersion(nbData.substring(4, 7));
            //设备IMEI号
            entity.setImei(nbData.substring(8, 23));
            //设备ICCID
            entity.setIccID(nbData.substring(24, 44));
            //状态
            entity.setType(nbData.substring(45, 47));
            //设备温度
            entity.setTemperature(nbData.substring(48, 50));
            //设备电量
            entity.setElectricQuantity(nbData.substring(51, 53));
            //信号强度
            entity.setSignal(nbData.substring(54, 56));
        }

        //事件时间
        entity.setEventTime(service.getString(WanlinNB301MapConstant.EVENT_TIME));
        return entity;
    }

    /**
     * {"notifyType":"deviceDataChanged","deviceId":"10c60373-7a77-4712-883b-b1aaa31d18cc",
     * "gatewayId":"10c60373-7a77-4712-883b-b1aaa31d18cc","requestId":null,
     * "service":{"serviceId":"WPS","serviceType":"WPS","data":
     * {"Data":"N311V31I865815040244829H89861118280005212549X10T28U99S18"},
     * "eventTime":"20200520T021156Z"}}
     * 将数据字典转换成可以识别的表达式
     *
     * @param deviceEntity 设备实体
     * @return 转换后的设备实体
     */
    public WanlinNbEntityVO convertVo(WanlinNbEntity deviceEntity) {
        WanlinNbEntityVO entity = new WanlinNbEntityVO();

        entity.setNotifyType(deviceEntity.getNotifyType());
        entity.setDeviceId(deviceEntity.getDeviceId());
        entity.setGatewayId(deviceEntity.getGatewayId());
        entity.setRequestId(deviceEntity.getRequestId());
        entity.setServiceId(deviceEntity.getServiceId());
        entity.setServiceType(deviceEntity.getServiceType());
        entity.setType(deviceEntity.getType());
        entity.setGen(WanlinNB301MapConstant.WANLIN_MAP.get(NB301Enum.GEN).get(deviceEntity.getGen()));
        entity.setVersion(WanlinNB301MapConstant.WANLIN_MAP.get(NB301Enum.VER).get(deviceEntity.getVersion()));

        entity.setElectricQuantity(deviceEntity.getElectricQuantity());
        entity.setTemperature(deviceEntity.getTemperature());
        entity.setSignal(deviceEntity.getSignal());
        entity.setImei(deviceEntity.getImei());
        entity.setIccid(deviceEntity.getIccID());
        entity.setEventTime(deviceEntity.getEventTime());
        return entity;
    }


    public static void main(String[] args) {
        String data = "{\"notifyType\":\"deviceDataChanged\",\"deviceId\":\"10c60373-7a77-4712-883b-b1aaa31d18cc\", \"gatewayId\":\"10c60373-7a77-4712-883b-b1aaa31d18cc\",\"requestId\":null, \"service\":{\"serviceId\":\"WPS\",\"serviceType\":\"WPS\",\"data\":{\"Data\":\"N311V31I865815040244829H89861118280005212549X10T28U99S18\"},\"eventTime\":\"20200520T021156Z\"}}";
        WanlinNB301ParseStrategy strategy = new WanlinNB301ParseStrategy();
        WanlinNbEntity e = strategy.assemblyData(data);
        WanlinNbEntityVO e2 = strategy.convertVo(e);

        System.out.println(e2);
    }
}
