package com.smart.device.message.parse.fire.smoke.wanlinnb306.constant;

import java.util.concurrent.ConcurrentHashMap;

/**
 * * 参考数据：
 * *     {"notifyType":"deviceDataChanged","service":
 * *      *     "deviceId":"e917e013-044b-4f32-8b53-588ef68c185a",
 * *  *     "gatewayId":"e917e013-044b-4f32-8b53-588ef68c185a"
 * *     {"serviceType":"Report","  eventTime":"20200401T182505Z","serviceId":"Report"
 * *     data":
 * *     {"devId":"10009305","electricQuantity":100,"devState":0,"temperature":219,
 * *     "signal":12,"cmdValue":"10","smokescope":0},"
 * *    } }
 *
 * @author 三多
 * @Time 2020/4/10
 */
public class WanlinMapConstant {

    public static final ConcurrentHashMap<WanlinEnum, ConcurrentHashMap<String, String>> WANLIN_MAP = new ConcurrentHashMap<>();

    public static final String NOTIFY_TYPE = "notifyType";
    public static final String DEVICE_ID = "deviceId";
    public static final String SERVICE = "service";
    public static final String SERVICE_ID = "serviceId";
    public static final String SERVICE_TYPE = "serviceType";

    public static final String DATA = "data";
    public static final String EVENT_TIME = "eventTime";
    public static final String DEVICE_INFO = "deviceInfo";
    public static final String STATUS = "status";

    public static final String ELECTRIC_QUANTITY = "electricQuantity";
    public static final String DEV_STATE = "devState";
    public static final String DEV_ID = "devId";
    public static final String TEMPERATURE = "temperature";
    public static final String SIGNAL = "signal";

    public static final String CMDVALUE = "cmdValue";
    public static final String SMOKE_SCOPE = "smokescope";

    static {
        /******************************header*********************************/
        ConcurrentHashMap<String, String> notifyType = getConcurrentHashMapInstance();
        notifyType.put("0", "设备事件类型");
        WANLIN_MAP.put(WanlinEnum.NOTIFY_TYPE, notifyType);
        ConcurrentHashMap<String, String> deviceId = getConcurrentHashMapInstance();
        deviceId.put("0", "设备deviceId");
        WANLIN_MAP.put(WanlinEnum.DEVICE_ID, deviceId);
        ConcurrentHashMap<String, String> service = getConcurrentHashMapInstance();
        service.put("0", "service层");
        WANLIN_MAP.put(WanlinEnum.SERVICE, service);

        ConcurrentHashMap<String, String> eventTime = getConcurrentHashMapInstance();
        eventTime.put("0", "报警时间");
        WANLIN_MAP.put(WanlinEnum.EVENT_TIME, eventTime);

        ConcurrentHashMap<String, String> serviceType = getConcurrentHashMapInstance();
        serviceType.put("0", "Report类型");
        serviceType.put("Report", "Report");
        WANLIN_MAP.put(WanlinEnum.SERVICE_TYPE, serviceType);

        ConcurrentHashMap<String, String> data = getConcurrentHashMapInstance();
        data.put("0", "报警数据");
        WANLIN_MAP.put(WanlinEnum.DATA, data);
        ConcurrentHashMap<String, String> electricQuantity = getConcurrentHashMapInstance();
        electricQuantity.put("0", "电池电量");
        WANLIN_MAP.put(WanlinEnum.ELECTRIC_QUANTITY, electricQuantity);

        ConcurrentHashMap<String, String> devState = getConcurrentHashMapInstance();
        devState.put("0", "报警类型：心跳");
        devState.put("1", "发生火警");
        devState.put("2", "温度过高");
        devState.put("3", "设备被拆");
        devState.put("4", "低电压");
        devState.put("5", "自检");

        WANLIN_MAP.put(WanlinEnum.DEV_STATE, devState);

        ConcurrentHashMap<String, String> devId = getConcurrentHashMapInstance();
        devId.put("0", "设备ID");
        WANLIN_MAP.put(WanlinEnum.DEV_ID, devId);

        ConcurrentHashMap<String, String> temperature = getConcurrentHashMapInstance();
        temperature.put("0", "温度");
        WANLIN_MAP.put(WanlinEnum.TEMPERATURE, temperature);

        ConcurrentHashMap<String, String> signal = getConcurrentHashMapInstance();
        signal.put("0", "信号强度");
        WANLIN_MAP.put(WanlinEnum.SIGNAL, signal);

        ConcurrentHashMap<String, String> smokescope = getConcurrentHashMapInstance();
        smokescope.put("0", "烟雾浓度");
        WANLIN_MAP.put(WanlinEnum.SMOKE_SCOPE, smokescope);

        ConcurrentHashMap<String, String> cmdValue = getConcurrentHashMapInstance();
        cmdValue.put("0", "设备事件");
        cmdValue.put("2", "登录");
        cmdValue.put("10", "事件");
        cmdValue.put("f", "复位");
        WANLIN_MAP.put(WanlinEnum.CMDVALUE, cmdValue);

        ConcurrentHashMap<String, String> type = getConcurrentHashMapInstance();
        // 正常
        type.put("0", "0");
        // 报警
        type.put("1", "1");
//        type.put("2","2");0
        // 拆卸

        type.put("3", "4");
        // 欠压
        type.put("4", "3");
        // 自检
        type.put("5", "5");

        WANLIN_MAP.put(WanlinEnum.TYPE, type);

    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }

    public static void main(String[] args) {

        System.out.println(WanlinEnum.CMDVALUE);
        Object obj = WANLIN_MAP.get(WanlinEnum.CMDVALUE);

        System.out.println(obj);
    }
}
