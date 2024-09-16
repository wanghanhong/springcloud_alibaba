package com.smart.device.message.parse.fire.electric.lian.constant;

import java.util.concurrent.ConcurrentHashMap;

/**
 * * 参考数据：
 * *{
 *      * 	"type": 2,
 *      * 	"time": "1536224934000",
 *      * 	"data": [
 *      *            {
 *      *     "deviceId": "1111",
 *      * 		"pointId": "1",
 *      * 		"value": "10",
 *      * " alarmType": "10"
 *      *     }
 *      * 	]
 *      * }
 *
 */
public class ElectricLiAnMapConstant {

    public static final String TYPE = "type";
    public static final String TIME = "time";
    public static final String DATA = "data";

    public static final String DEVICEID = "deviceId";
    public static final String POINTID = "pointId";
    public static final String VALUE = "value";
    public static final String ALARMTYPE = "alarmType";
    public static final String STATUS = "status";

    public static final String  TYPE_DATA= "1";
    public static final String  TYPE_ALARM= "2";
    public static final String  TYPE_ONLINE= "3";

    public static final ConcurrentHashMap<ElectricLiAnEnum, ConcurrentHashMap<String, String>> MAP = new ConcurrentHashMap<>();

    static {
        /******************************header*********************************/
        ConcurrentHashMap<String, String> event = getConcurrentHashMapInstance();
        event.put("100", "隐患");
        event.put("201", "漏电报警");
        event.put("202", "超温报警");
        event.put("206", "离岗");
        event.put("603", "短路");
        event.put("604", "过载");
        event.put("605", "过压");
        event.put("606", "欠压");

        event.put("607", "过温或者接触不良");
        event.put("608", "缺相");
        event.put("609", "三相不平衡");
        event.put("610", "零线混接或零线混用");
        event.put("615", "PGM 电池电压低");

        event.put("205", "报警");
        event.put("207", "感烟报警");
        event.put("208", "手动报警");
        event.put("209", "气体浓度高报警");

        event.put("80101", "屏蔽");
        event.put("80102", "取消屏蔽");
        event.put("80201", "启动");
        event.put("80202", "停止");
        event.put("80301", "复位");
        event.put("80302", "正常");
        event.put("80401", "延时");
        event.put("80402", "无延时");
        event.put("80501", "监管");
        event.put("80502", "无监管");
        event.put("80601", "反馈");
        event.put("80602", "无反馈");
        event.put("80801", "动作");
        event.put("80802", "动作恢复");
        MAP.put(ElectricLiAnEnum.ALARM_TYPE, event);

        ConcurrentHashMap<String, String> eventtype = getConcurrentHashMapInstance();
        eventtype.put("1361", "1");

    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }

    
    public static void main(String[] args) {

        System.out.println(12);
    }
}
