package com.smart.device.message.factory.fire.constant;

import java.util.concurrent.ConcurrentHashMap;

public class CtWingDeviceConstant {

    public static ConcurrentHashMap<String,String> TYPE_MAP = new ConcurrentHashMap<>();
    //  ctwing平台产品对应代码解析
    static {
        /******************************header*********************************/
        TYPE_MAP.put("10045600", "11"); // 华强烟感设备
        TYPE_MAP.put("10053421", "13"); // 华强水压设备

        TYPE_MAP.put("15014772", "22"); //力安烟感设备
        TYPE_MAP.put("15021100", "22"); //力安烟感设备
        TYPE_MAP.put("15023783", "22"); //力安烟感设备

        TYPE_MAP.put("15017088", "23"); // 力安气感设备
        TYPE_MAP.put("15017085", "24");// 用户信息传输装置

    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }

    public static void main(String[] args) {

    }
}
