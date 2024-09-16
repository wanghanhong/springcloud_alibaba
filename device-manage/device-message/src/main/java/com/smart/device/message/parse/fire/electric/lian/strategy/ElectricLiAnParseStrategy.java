package com.smart.device.message.parse.fire.electric.lian.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smart.device.message.parse.fire.electric.lian.constant.ElectricLiAnMapConstant;
import com.smart.device.message.parse.fire.electric.lian.entity.ElectricLiAnEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 *  智慧用电
 */
@Component
@Slf4j
public class ElectricLiAnParseStrategy{

    /**
     *
     * {
     * 	"type": 2,
     * 	"time": "1536224934000",
     * 	"data": [
     *            {
     *     "deviceId": "1111",
     * 		"pointId": "1",
     * 		"value": "10",
     * " alarmType": "10"
     *     }
     * 	]
     * }
     *
     * @param
     * @return 返回值
     */
    public List<ElectricLiAnEntity> assemblyData(String param) {
        List<ElectricLiAnEntity> entitys = new ArrayList<>();

        JSONObject obj = JSONObject.parseObject(param);

        Integer type = obj.getInteger(ElectricLiAnMapConstant.TYPE);
        String time = obj.getString(ElectricLiAnMapConstant.TIME);
        String dataStr = obj.getString(ElectricLiAnMapConstant.DATA);
        if(dataStr.contains("[")){
            JSONArray array = obj.getJSONArray(ElectricLiAnMapConstant.DATA);
            if(array != null && array.size() > 0){
                for (int i=0;i<array.size();i++){
                    JSONObject data = array.getJSONObject(i);
                    ElectricLiAnEntity entity = new ElectricLiAnEntity();

                    String deviceId = data.getString(ElectricLiAnMapConstant.DEVICEID);
                    String pointId = data.getString(ElectricLiAnMapConstant.POINTID);
                    String value = data.getString(ElectricLiAnMapConstant.VALUE);
                    String alarmType = data.getString(ElectricLiAnMapConstant.ALARMTYPE);
                    String status = data.getString(ElectricLiAnMapConstant.STATUS);

                    entity.setType(type);
                    entity.setTime(time);

                    entity.setDeviceId(deviceId);
                    entity.setPointId(pointId);
                    entity.setValue(value);
                    entity.setAlarmType(alarmType);
                    entity.setStatus(status);
                    entitys.add(entity);
                }
            }
        }
        log.info("智慧用电-参数接收" + JSONObject.toJSONString(entitys));
        return entitys;
    }

    public static void main(String[] args) {
        String data = "{\"type\":\"0301\",\"chipcode\":\"701904012751507255\",\"signal_intensity\":19,\"current1\":\"0\",\"current2\":\"0_02\",\"current3\":\"0_01\",\"current4\":\"0_04\",\"temperature1\":\"0\",\"temperature2\":\"0\",\"temperature3\":\"0\",\"temperature4\":\"0\",\"client_id\":\"7f0000010bba00001ba9\",\"eventdate\":1589944732,\"event\":\"1361\"}\n";

    }

}
