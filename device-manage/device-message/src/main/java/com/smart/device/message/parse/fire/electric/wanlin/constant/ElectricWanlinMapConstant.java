package com.smart.device.message.parse.fire.electric.wanlin.constant;

import java.util.concurrent.ConcurrentHashMap;

/**
 * * 参考数据：
 * *
 * 心跳数
 * 据示例	{"type":"0301","chipcode":"701809283275554264","signal_intensity":31,"
 * current1":0,"current2":1,"current3":0,"current4":1,"temperature1":30,"temp
 * erature2":13,"temperature3":30,"temperature4":10,"client_id":"7f0000010b
 * 5700000e25","eventdate":1547280790}
 *
 * @author 三多
 * @Time 2020/4/10
 */
public class ElectricWanlinMapConstant {

    public static final String TYPE = "type";
    public static final String CHIPCODE = "chipcode";
    public static final String SIGNAL_INTENSITY = "signal_intensity";
    public static final String CURRENT1 = "current1";
    public static final String CURRENT2 = "current2";

    public static final String CURRENT3 = "current3";
    public static final String CURRENT4 = "current4";
    public static final String TEMPERATURE1 = "temperature1";
    public static final String TEMPERATURE2 = "temperature2";
    public static final String TEMPERATURE3 = "temperature3";
    public static final String TEMPERATURE4 = "temperature4";

    public static final String CLIENT_ID = "client_id";
    public static final String EVENTDATE = "eventdate";
    public static final String EVENT = "event";

    public static final String TYPE_HEARTBEAT = "0301";
    public static final String TYPE_EVENT = "0302";


    public static final ConcurrentHashMap<ElectricWanlinEnum, ConcurrentHashMap<String, String>> WANLIN_MAP = new ConcurrentHashMap<>();

    static {
        /******************************header*********************************/
        ConcurrentHashMap<String, String> type = getConcurrentHashMapInstance();
        type.put("0301", "心跳");
        type.put("0302", "事件");
        WANLIN_MAP.put(ElectricWanlinEnum.TYPE, type);
        ConcurrentHashMap<String, String> event = getConcurrentHashMapInstance();
        event.put("1361", "线路过流报警");
        event.put("3361", "过流恢复");
        event.put("1360", "线路断电报警");
        event.put("3360", "断电恢复");
        event.put("1364", "现场温度过高报警");
        event.put("3364", "现场温度过高恢复");
        event.put("1363", "现场温度过低报警");
        event.put("3363", "现场温度过低恢复");

        event.put("1366", "电力线监测器离线代码");
        event.put("3366", "电力线监测器恢复在线");
        event.put("3921", "智慧用电监测器消音事件代码");
        event.put("3922", "智慧用电监测器消警事件代码");
        event.put("1941", "PGM 开关输出打开事件代码");
        event.put("3941", "PGM 开关输出关闭事件代码");

        WANLIN_MAP.put(ElectricWanlinEnum.EVENT, event);
//
        ConcurrentHashMap<String, String> eventtype = getConcurrentHashMapInstance();
        eventtype.put("1361", "1");
        eventtype.put("3361", "6");
        eventtype.put("1360", "1");
        eventtype.put("3360", "6");
        eventtype.put("1364", "1");
        eventtype.put("3364", "6");
        eventtype.put("1363", "1");
        eventtype.put("3363", "6");

        eventtype.put("1366", "1");
        eventtype.put("3366", "6");
        eventtype.put("3921", "6");
        eventtype.put("3922", "6");
        eventtype.put("1941", "6");
        eventtype.put("3941", "6");

        WANLIN_MAP.put(ElectricWanlinEnum.EVENTTYPE, eventtype);
    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }

    public static void main(String[] args) {

        System.out.println(12);
    }
}
