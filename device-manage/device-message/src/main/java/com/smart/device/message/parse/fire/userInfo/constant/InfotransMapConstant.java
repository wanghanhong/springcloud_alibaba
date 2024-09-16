package com.smart.device.message.parse.fire.userInfo.constant;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InfotransMapConstant {

    public static final ConcurrentHashMap<InfotransEnum, ConcurrentHashMap<String, String>> Infotrans_Map = new ConcurrentHashMap<>();

    public static final Integer event_21 = 21; //报警事件上传
    public static final Integer event_22 = 22; // 预警事件上传
    public static final Integer event_23 = 23; // 故障事件上传
    public static final Integer event_24 = 24; // 装置状态上传
    public static final Integer event_25 = 25; // 消防主机（设施系统）状态上传
    public static final Integer event_26 = 26; // 消防主机下装置动作事件上传（公司ARM接消防主机）
    public static final Integer event_27 = 27; //  消防设施部件状态上传（无锡蓝天用户传输装置,433网关,新ARM接消防主机）

    public static final String event_code_02= "-2";
    public static final String event_code_01= "-1";
    public static final String event_code_0= "0";
    public static final String event_code_1= "1";
    public static final String event_code_2= "2";
    public static final String event_code_3= "3";
    public static final String event_code_4= "4";
    public static final String event_code_5= "5";

    public static Map<String,String> event_Map = new HashMap<>();

    static {
        /******************************header*********************************/

        //命令类型
        ConcurrentHashMap<String, String> type = getConcurrentHashMapInstance();

        type.put("92", "用户传输装置");
        type.put("21", "报警事件");
        type.put("22", "预警事件");
        type.put("23", "故障事件");
        type.put("24", "装置状态");
        type.put("26", "消防设施部件事件");
        type.put("27", "消防设施部件状态");
        Infotrans_Map.put(InfotransEnum.c, type);

        event_Map.put(event_code_02,"设备自检");
        event_Map.put(event_code_01,"心跳");
        event_Map.put(event_code_0,"解除报警");
        event_Map.put(event_code_1,"烟雾报警");
        event_Map.put(event_code_2,"温度过高");
        event_Map.put(event_code_3,"设备故障");
        event_Map.put(event_code_4,"低电压");
        event_Map.put(event_code_5,"拆卸报警");

    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }


}
