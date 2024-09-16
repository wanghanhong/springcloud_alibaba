package com.smart.device.message.parse.fire.waterpress.pressure.constant;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 三多
 * @Time 2020/4/10
 */
public class PressureMapConstant {

    public static final ConcurrentHashMap<PressureEnum, ConcurrentHashMap<String, String>> WANLIN_MAP = new ConcurrentHashMap<>();

    public static final String NOTIFY_TYPE = "notifyType";
    public static final String DEVICE_ID = "deviceId";
    public static final String SERVICE = "service";
    public static final String SERVICE_ID = "serviceId";
    public static final String SERVICE_TYPE = "serviceType";

    public static final String DATE = "data";
    public static final String EVENT_TIME = "eventTime";
    public static final String DEVICE_INFO = "deviceInfo";
    public static final String status = "status";

    static {
        /******************************header*********************************/
        // 事件类型

        ConcurrentHashMap<String, String> notifyType = getConcurrentHashMapInstance();
        // 正常
        notifyType.put("deviceDataChanged", "事件推送信息");
        // 报警
        notifyType.put("deviceInfoChanged", "设备在线离线状态");
        WANLIN_MAP.put(PressureEnum.NOTIFY_TYPE, notifyType);

        ConcurrentHashMap<String, String> status = getConcurrentHashMapInstance();
        status.put("ONLINE", "ONLINE");
        status.put("OFFLINE", "6");
        status.put("INBOX", "INBOX");
        status.put("ABNORMAL", "2");
        WANLIN_MAP.put(PressureEnum.STATUS, status);

        ConcurrentHashMap<String, String> content = getConcurrentHashMapInstance();
        content.put("10", "设备自检");
        content.put("02", "'电量不足'");
        content.put("03", "低压报警");
        content.put("13", "低压报警恢复");
        content.put("04", "高压报警");
        content.put("14", "'高压报警恢复");
        WANLIN_MAP.put(PressureEnum.CONTENT, content);

    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }


}
