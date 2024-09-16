package com.smart.device.message.parse.fire.gas.lian.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 参考数据：
 * {"productId":"15017088","protocol":"LWM2M","payload":{"valve_state":2,"temperature":0,"medium":0,"gas_value":0,"gas_sensor_state":0,"fan_state":2,"battery_voltage":0.0,"battery_value":100}
 * ,"deviceId":"ce96e71f84764773afd9d559379c25a7","deviceCode":"869162052818257","IMSI":"869162052818257","IMEI":"869162052818257","event":"1","state":1,"signal_intensity":"1","content":"报警","eventdate":1589352628}
 */
@Getter
@AllArgsConstructor
public enum LiAnGasEnum {

    productId,
    protocol,
    payload,
    valve_state,
    temperature,
    medium,
    gas_value,
    gas_sensor_state,
    fan_state,
    battery_voltage,
    battery_value,
    error_code,
    deviceId,
    deviceCode,
    IMSI,
    IMEI,
    event,
    state,
    signal_intensity,
    eventdate,
    eventString,

    terminal_type,
    software_version,
    sinr,
    rsrp,
    pci,
    manufacturer_name,
    heartbeat_time,
    hardware_version,
    ecl,
    cell_id,
    ICCID;

}

