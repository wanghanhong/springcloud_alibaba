package com.smart.device.message.parse.fire.smoke.huaqiang.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 参考数据：
 * type=10, index=02, ver=2F, dev=08, manu=01, initiator=00, opt=03, attri=0000000000011011,
 * dataLength=27, encryptType=00, time=49734088, imei=865484029251022, cmd=2048, alert=0,
 * devCode=0, devMode=0, devVolt=2.91, devVoltPer=65, devTemp=24, cellId=128502100, rsrp=-92,
 * smokeTime=0, selfDetectTime=49734088, selfDetectRet=0, reserve=0
 *
 * @author 三多
 * @Time 2020/4/9
 */
@Getter
@AllArgsConstructor
public enum SmokeEnum {
    TYPE, MANU, VERSION, DEV, OPT, ENCRYPT, ALERT, DEV_CODE, DEV_MODE, SELF_DETECT_RET, PAYLOAD, APP_DATA, ALARM_TYPE;

}

