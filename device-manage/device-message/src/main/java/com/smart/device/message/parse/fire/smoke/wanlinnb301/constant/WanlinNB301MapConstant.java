package com.smart.device.message.parse.fire.smoke.wanlinnb301.constant;


import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 三多
 * @Time 2020/5/20
 */
public class WanlinNB301MapConstant {

    public static final ConcurrentHashMap<NB301Enum, ConcurrentHashMap<String, String>> WANLIN_MAP = new ConcurrentHashMap<>();
    public static final String NOTIFY_TYPE = "notifyType";
    public static final String DEVICE_ID = "deviceId";
    public static final String GATEWAY_ID = "gatewayId";
    public static final String REQUEST_ID = "requestId";
    public static final String SERVICE = "service";
    public static final String SERVICE_ID = "serviceId";
    public static final String SERVICE_TYPE = "serviceType";

    public static final String DATA = "data";
    public static final String DATA_IN = "Data";
    public static final String EVENT_TIME = "eventTime";

    static {
        /******************************data*********************************/
        //烟感类别
        ConcurrentHashMap<String, String> gen = getConcurrentHashMapInstance();
        gen.put("311", "烟感2代");
        WANLIN_MAP.put(NB301Enum.GEN, gen);
        //版本
        ConcurrentHashMap<String, String> ver = getConcurrentHashMapInstance();
        ver.put("V31", "V3.1");
        WANLIN_MAP.put(NB301Enum.VER, ver);
        // 状态
        ConcurrentHashMap<String, String> type = getConcurrentHashMapInstance();
        type.put("1", "发生火灾");
        type.put("2", "温度过高");
        type.put("3", "设备被拆");
        type.put("4", "低电");
        type.put("5", "测试");
        type.put("f", "报警恢复");

        type.put("10", "自检");
        type.put("01", "报警");
        type.put("02", "低电");
        type.put("11", "恢复");
        WANLIN_MAP.put(NB301Enum.TYPE, type);

    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }

}

