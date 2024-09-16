package com.smart.device.message.factory.fire.constant;

import com.smart.device.message.factory.fire.analysis.service.impl.*;
import java.util.concurrent.ConcurrentHashMap;

public class FactoryConstant {

    public static ConcurrentHashMap<String,Class> TYPE_MAP = new ConcurrentHashMap<>();

    static {
        /******************************header*********************************/
        TYPE_MAP.put("11", HQSmokeDeviceParse.class);
        TYPE_MAP.put("12", WLSmokeNB306DeviceParse.class);
        TYPE_MAP.put("13", HQWaterpressParse.class);
        TYPE_MAP.put("14", WLWaterpressDeviceParse.class);
        TYPE_MAP.put("15", LiquidlevelParse.class);
        TYPE_MAP.put("16", GasDeviceParse.class);
        TYPE_MAP.put("17", ElectricDeviceParse.class);
        TYPE_MAP.put("18", FirehostParse.class);
        TYPE_MAP.put("19", WLSmokeNB3011DeviceParse.class);
        TYPE_MAP.put("21", WanlinTempParse.class);

        TYPE_MAP.put("22", LiAnSmokeDeviceParse.class);//力安烟感设备
        TYPE_MAP.put("23", LiAnGasDeviceParse.class);// 力安气感设备
        TYPE_MAP.put("24", InfotransParse.class);// 用户信息传输装置

        TYPE_MAP.put("25", LiAnElectricDeviceParse.class);// 力安用电

    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }

    public static void main(String[] args) {

    }
}
