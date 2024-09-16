package com.smart.brd.manage.message.parse.device.constant;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author junglelocal
 */

public class DeviceMapConstant {

    public static final ConcurrentHashMap<DeviceEnum, ConcurrentHashMap<Integer, String>> DEVICE_MAP = new ConcurrentHashMap<>();

    static {
        //开盒
        ConcurrentHashMap<Integer, String> closingType = getConcurrentHashMapInstance();
        closingType.put(0, "未开");
        closingType.put(1, "打开");
        DEVICE_MAP.put(DeviceEnum.CLOSING, closingType);
        //信号状态
        ConcurrentHashMap<Integer, String> rssi = getConcurrentHashMapInstance();
        rssi.put(0, "信号正常");
        rssi.put(1, "信号弱或异常");
        DEVICE_MAP.put(DeviceEnum.RSSI, rssi);

    }

    private static ConcurrentHashMap<Integer, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }
}
