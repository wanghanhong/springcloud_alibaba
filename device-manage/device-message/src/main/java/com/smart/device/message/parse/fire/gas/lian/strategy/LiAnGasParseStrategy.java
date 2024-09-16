package com.smart.device.message.parse.fire.gas.lian.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.message.parse.fire.ParseStrategy;
import com.smart.device.message.parse.fire.gas.lian.constant.LiAnGasEnum;
import com.smart.device.message.parse.fire.gas.lian.entity.GasLiAnEntity;
import com.smart.device.message.parse.fire.smoke.lian.constant.LiAnSmokeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class LiAnGasParseStrategy implements ParseStrategy<GasLiAnEntity> {

    public static final String gas_sensor_alarm = "1001";      //燃气报警
    public static final String error_code_alarm = "1002";      //故障报警
    public static final String battery_voltage_alarm = "1003";     //低压报警
    public static final String normal = "7";               //正常情况
    /*
    * {"deviceCode":"869162052818257","productId":"15017088","IMEI":"869162052818257","protocol":"LWM2M",
    * "payload":{"valve_state":2,"temperature":0,"medium":0,"gas_value":0,"gas_sensor_state":1,"fan_state":2,"battery_voltage":1.1,"battery_value":100}} * @Desc  将力安气体数据转换为力安的对象
     * @author yangfei
     * @Date 2020/12/3 9:59
    */

    @Override
    public GasLiAnEntity assemblyData(String param) {
        // 气感设备

        JSONObject obj = JSONObject.parseObject(param);
        GasLiAnEntity entity = new GasLiAnEntity();

        entity.setIMEI(String.valueOf(obj.get(LiAnGasEnum.IMEI.name())));// NB终端设备识别号
        entity.setDeviceCode(obj.getString(LiAnGasEnum.deviceCode.name()));

        String payloadStr = String.valueOf(obj.get(LiAnGasEnum.payload.name()));
        JSONObject payload = obj.getJSONObject(LiAnSmokeEnum.payload.name());

        if(payload!=null) {
            String s = String.valueOf(payload.get(LiAnGasEnum.valve_state.name()));
            String s1 = String.valueOf(payload.get(LiAnGasEnum.temperature.name()));
            String s2 = String.valueOf(payload.get(LiAnGasEnum.medium.name()));
            String s3 = String.valueOf(payload.get(LiAnGasEnum.gas_value.name()));
            String s4 = String.valueOf(payload.get(LiAnGasEnum.gas_sensor_state.name()));
            String s5 = String.valueOf(payload.get(LiAnGasEnum.fan_state.name()));
            String s6 = String.valueOf(payload.get(LiAnGasEnum.battery_value.name()));
            String s7 = String.valueOf(payload.get(LiAnGasEnum.battery_voltage.name()));
            String s8 = String.valueOf(payload.get(LiAnGasEnum.error_code.name()));
            String s9 = String.valueOf(payload.get(LiAnGasEnum.heartbeat_time.name()));
            String s10 = String.valueOf(payload.get(LiAnGasEnum.software_version.name()));
            String s11 = String.valueOf(payload.get(LiAnGasEnum.hardware_version.name()));
            String s12 = String.valueOf(payload.get(LiAnGasEnum.terminal_type.name()));
            String s13 = String.valueOf(payload.get(LiAnGasEnum.sinr.name()));
            String s14 = String.valueOf(payload.get(LiAnGasEnum.rsrp.name()));
            String s15 = String.valueOf(payload.get(LiAnGasEnum.pci.name()));
            String s16 = String.valueOf(payload.get(LiAnGasEnum.ecl.name()));
            String s17 = String.valueOf(payload.get(LiAnGasEnum.cell_id.name()));
            String s18 = String.valueOf(payload.get(LiAnGasEnum.ICCID.name()));
            if( !payloadStr.contains("ICCID")){
                entity.setValveState(Integer.valueOf(s));
                entity.setTemperature(Integer.valueOf(s1));
                entity.setMedium(Integer.valueOf(s2));
                entity.setGasValue(Integer.valueOf(s3));
                entity.setGasSensorState(Integer.valueOf(s4));
                entity.setFanState(Integer.valueOf(s5));
                if(s4 != null ){
                    //燃气报警
                    entity.setEvent(gas_sensor_alarm);
                }

            }else{
                entity.setHeartbeat_time(Float.parseFloat(s9));
                entity.setSoftware_version(s10);
                entity.setHardware_version(s11);
                entity.setTerminalType(s12);
                entity.setSinr(Integer.valueOf(s13));
                entity.setRsrp(Integer.valueOf(s14));
                entity.setPci(Integer.valueOf(s15));
                entity.setEcl(Integer.valueOf(s16));
                entity.setCell_id(Integer.valueOf(s17));
                entity.setICCID(s18);
            }
            entity.setBatteryValue(Integer.valueOf(s6));
            entity.setBatteryVoltage(Float.parseFloat(s7));

            if(new Double(Double.valueOf(s7)).intValue() < 1){
                //低压报警
                entity.setEvent(battery_voltage_alarm);
            }
            if(!StringUtils.isNotEmpty(s8)){
                entity.setErrorCode(Integer.valueOf(s8));
                if(Integer.parseInt(s8) != 0 ){
                    //故障
                    entity.setEvent(error_code_alarm);
                }
            }
        }

        entity.setPayload(payloadStr);
        log.info("力安气感-参数接收" + JSONObject.toJSONString(entity));
        return entity;
    }

}
