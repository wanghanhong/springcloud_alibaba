package com.smart.device.message.parse.fire.temp.constants;

import java.util.concurrent.ConcurrentHashMap;
/**
 * @author 三多
 * @Time 2020/4/10
 */
public class WanlinTempConstants {

    public static final ConcurrentHashMap<WanlinTempEnum, ConcurrentHashMap<String, String>> WANLIN_MAP = new ConcurrentHashMap<>();
    public static final String DEVICE_ID = "mac";
    public static final String EVENT_DATE = "eventdate";
    public static final String EVENT = "event";
    public static final String HUMID = "humidity";
    public static final String CONTENT = "content";
    public static final String HUMID_HIGH = "humidity_high";
    public static final String HUMID_LOW = "humidity_low";
    public static final String ICCID = "iccid";
    public static final String CLIENT_ID = "client_id";

    static {
        // 事件类型

        ConcurrentHashMap<String, String> event = getConcurrentHashMapInstance();
        event.put("10", "温度报警");
        event.put("12", "湿度报警");
        event.put("0012", "心跳信息");
        event.put("0010", "消音提示");
        event.put("0007", "复位提示");

        WANLIN_MAP.put(WanlinTempEnum.CONTENT, event);
    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }
}
