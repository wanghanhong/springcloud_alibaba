package com.smart.device.message.parse.fire.userInfo.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smart.device.message.parse.fire.ParseStrategy;
import com.smart.device.message.parse.fire.userInfo.entity.InfotransEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 *用户信息传输解析
 */
@Component
@Slf4j
public class InfotransParseStrategy implements ParseStrategy<InfotransEntity> {

    /**
     * {"upPacketSN":-1,"upDataSN":-1,"topic":"ad","timestamp":1609231209882,"tenantId":"10461180","serviceId":"","protocol":"tcp","productId":"15017085",
     * "payload":{"APPdata":"ewogICAiYiIgOiBbCiAgICAgIHsKICAgICAgICAgImlkIiA6ICIyMjIiLAogICAgICAgICAidiIgOiAiMSIKICAgICAgfQogICBdLAogICAiYyIgOiAyNywKICAgImlkIiA6IDE2MDkyMzEyMDk3NjcsCiAgICJ0bSIgOiAxNjA5MjMxMjA5NzY3Cn0="},
     * "messageType":"dataReport","deviceType":"","deviceId":"1501708501","assocAssetId":"","IMSI":"","IMEI":""}
     *
     * {
     *    "b" : [
     *       {
     *          "id" : "222",
     *          "v" : "1"
     *       }
     *    ],
     *    "c" : 27,
     *    "id" : 1609231209767,
     *    "tm" : 1609231209767
     * }
     *
     * @param
     * @return 返回值
     */
    @Override
    public InfotransEntity assemblyData(String dataStr) {
        JSONObject obj = JSONObject.parseObject(dataStr);

        String upPacketSN = String.valueOf(obj.get("upPacketSN"));
        String upDataSN = String.valueOf(obj.get("upDataSN"));
        String topic = String.valueOf(obj.get("topic"));
        String messageType = String.valueOf(obj.get("messageType"));
        String deviceId = String.valueOf(obj.get("deviceId"));
        String tenantId = String.valueOf(obj.get("tenantId"));
        String productId = String.valueOf(obj.get("productId"));
        String protocol = String.valueOf(obj.get("protocol"));


        InfotransEntity entity = new InfotransEntity();
        String payload = String.valueOf(obj.get("payload"));
        JSONObject jsonObject2 = JSONObject.parseObject(payload);
        String apPdata = String.valueOf(jsonObject2.get("APPdata"));
        if(apPdata != null){
            String decodeStr = new String(Base64.getDecoder().decode(apPdata));
            System.out.println("转换后的数据：--"+decodeStr);
            JSONObject jsonObject = JSONObject.parseObject(decodeStr);
            String tm = String.valueOf(jsonObject.get("tm"));// 时间戳，毫秒数
            String tid = jsonObject.getString("tid"); // 装置唯一码
            Integer c = jsonObject.getInteger("c");

            String b = String.valueOf(jsonObject.get("b"));
            JSONObject bobject = new JSONObject();
            if(b != null && b.contains("[")){
                JSONArray array = jsonObject.getJSONArray("b");
                bobject = array.getJSONObject(0);
            }else{
                bobject = jsonObject.getJSONObject("b");
            }
            String v = String.valueOf(bobject.get("v"));
            String pid = String.valueOf(bobject.get("id"));
            /** 0181091701068001000001010100010133
             *  装置唯一编号	方法名
             * 01810917010680
             * 通道号	机号	回路模块号	回路号	地址号（用户编码）	设备类型	注释信息
             * 01		0	1	001	光电烟感	5层办公室南
             * 01		0	1	002	光电烟感	5层办公室北
             *  所以这个只用取 最后5位
             */

            String partId = pid.substring(pid.length()-5);
            if(StringUtils.isNotBlank(partId)){
                entity.setImei(Long.parseLong(partId));
            }
            entity.setC(c);
            entity.setV(v);
            entity.setTm(Long.valueOf(tm));
        }

        entity.setUpPacketSN(Integer.valueOf(upPacketSN));
        entity.setUpDataSN(Integer.valueOf(upDataSN));
        entity.setTopic(topic);
        entity.setMessageType(messageType);
        entity.setDeviceId(deviceId);
        entity.setProductId(productId);
        entity.setProtocol(protocol);
        entity.setTenantId(tenantId);
        entity.setPayload(payload);

        return entity;
    }

    public static void main(String[] args) {
        String str = "{\"b\":[{\"id\":\"01000917010374\",\"v\":\"1\",\"t\":\"1\"}],\"c\":22,\"id\":1609293816509,\"tm\":1609293816509}";
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] textByte = str.getBytes("UTF-8");
            //编码
            String encodedText = encoder.encodeToString(textByte);
            System.out.println(encodedText);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("11");

    }


}
