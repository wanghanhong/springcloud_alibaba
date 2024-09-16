package com.smart.device.message.parse.fire.smoke.lian.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smart.device.message.parse.fire.ParseStrategy;
import com.smart.device.message.parse.fire.smoke.lian.constant.LiAnSmokeEnum;
import com.smart.device.message.parse.fire.smoke.lian.entity.LiAnSmokeEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 烟感通用数据解析类
 *
 */
@Component
@Slf4j
public class LiAnSmokeParseStrategy implements ParseStrategy<LiAnSmokeEntity> {

    /**
     *  {"upPacketSN":-1,"upDataSN":-1,"topic":"v1/up/ad19prof","timestamp":1606982320277,"tenantId":"10461180","serviceId":"","protocol":"lwm2m","productId":"15014772","payload":{"serviceId":"notification","serviceData":{"TData":30,"SNR":1,"RSRP":114,"IMEI":"860374051096202","Cell_ID_Length":8,"Cell_ID":"38848339","CSQ":5,"Battery_Level":100,"Alarm_Status":2,"AlarmLevel":100}},"messageType":"dataReport","deviceType":"","deviceId":"acf7baaee65e431eadfb8f5fe50e8b81","assocAssetId":"","IMSI":"undefined","IMEI":"860374051096202"}
     *
     * @param
     * @return 返回值
     */
    @Override
    public LiAnSmokeEntity assemblyData(String dataStr) {
        JSONObject obj = JSONObject.parseObject(dataStr);
        LiAnSmokeEntity entity = new LiAnSmokeEntity();

        String IMEI = obj.getString(LiAnSmokeEnum.IMEI.name());// NB终端设备识别号
        String IMSI = obj.getString(LiAnSmokeEnum.IMSI.name());// NB终端sim卡标识
        String timestamp = obj.getString(LiAnSmokeEnum.timestamp.name());

        String payloadStr = obj.getString(LiAnSmokeEnum.payload.name());
        JSONObject payload = new JSONObject();
        if(StringUtils.isNotBlank(payloadStr) ){
            if(payloadStr.contains("[")){
                JSONArray array = obj.getJSONArray(LiAnSmokeEnum.payload.name());
                if(array != null && array.size() > 0){
                    payload = array.getJSONObject(0);
                }
            }else{
                payload = obj.getJSONObject(LiAnSmokeEnum.payload.name());
            }
        }
        JSONObject sData = payload.getJSONObject(LiAnSmokeEnum.serviceData.name());
        if(sData != null){
            String tData = sData.getString(LiAnSmokeEnum.TData.name());
            String snr = sData.getString(LiAnSmokeEnum.SNR.name());
            String rsrp = sData.getString(LiAnSmokeEnum.RSRP.name());
            String cell_ID = sData.getString(LiAnSmokeEnum.Cell_ID.name());
            String cell_ID_Length = sData.getString(LiAnSmokeEnum.Cell_ID_Length.name());

            String csq = sData.getString(LiAnSmokeEnum.CSQ.name());
            String battery_Level = sData.getString(LiAnSmokeEnum.Battery_Level.name());
            String alarm_Status = sData.getString(LiAnSmokeEnum.Alarm_Status.name());
            String alarmLevel = sData.getString(LiAnSmokeEnum.AlarmLevel.name());

            entity.setTData(tData);
            entity.setSNR(snr);
            entity.setRSRP(rsrp);
            entity.setCell_ID(cell_ID);
            entity.setCell_ID_Length(cell_ID_Length);

            entity.setCSQ(csq);
            entity.setBattery_Level(battery_Level);
            entity.setAlarm_Status(alarm_Status);
            entity.setAlarmLevel(alarmLevel);
        }
        entity.setIMEI(IMEI);
        entity.setIMSI(IMSI);
        entity.setTimestamp(timestamp);
        return entity;
    }


}
