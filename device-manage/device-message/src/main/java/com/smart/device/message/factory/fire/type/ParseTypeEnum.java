package com.smart.device.message.factory.fire.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 三多
 * @Time 2020/5/9
 */
@Getter
@AllArgsConstructor
public enum ParseTypeEnum {
    HQ_NB_FIRE("11", "华强NB烟感设备"),
    WL_NB_FIRE("12", "万林NB烟感设备"),
    HQ_NB_WATERPRESSURE("13", "华强水压传感器"),
    WL_NB_WATERPRESSURE("14", "万林NB无线远传报警数显压力计"),
    WL_NB_LIQUIDLEVEL("15", "万林NB无线远传报警数显液位计"),

    WL_NB_GAS("16", "万林NB燃气泄漏报警器"),
    WL_NB_ELECTRIC("17", "万林智慧用电"),
    WL_LORA_FIREHOST("18", "万林LORA消防主机"),
    WL_NB301_FIRE("19", "万林NB301烟感设备"),
    WL_TEMP_WET("20","万林温湿度探测器");


    private String code;
    private String msg;
}
