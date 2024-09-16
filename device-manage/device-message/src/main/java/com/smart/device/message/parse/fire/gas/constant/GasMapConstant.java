package com.smart.device.message.parse.fire.gas.constant;

import java.util.concurrent.ConcurrentHashMap;

public class GasMapConstant {

    public static final String deviceCode = "deviceCode";
    public static final String EVENT = "event";
    public static final String STATE = "state";
    public static final String EVENTDATE = "eventdate";

    public static final String CONTENT = "content";
    public static final String SIGNAL_INTENSITY = "signal_intensity";


    public static final ConcurrentHashMap<GasEnum, ConcurrentHashMap<Object, Object>> GAS_MAP = new ConcurrentHashMap<>();

    static {
        /******************************header*********************************/
        //类型
        ConcurrentHashMap<Object, Object> state = getConcurrentHashMapInstance();
        state.put(1, "打开");
        state.put(2, "关闭");
        GAS_MAP.put(GasEnum.STATE, state);


        ConcurrentHashMap<Object, Object> event = getConcurrentHashMapInstance();
        event.put("-1", "心跳");
        event.put("7", "正常");
        event.put("1001", "燃气报警");
        event.put("1002", "故障报警");
        event.put("1003", "低压警告");
        /*event.put("0006", "故障");
        event.put("0007", "复位");
        event.put("0008", "机械手控制");*/

        // 力安的事件编码
        /*event.put("1", "报警");
        event.put("2", "静音");
        event.put("3", "保留");
        event.put("4", "低压");
        event.put("5", "故障");
        event.put("6", "数据异常");
        event.put("7", "正常");
        event.put("8", "设备收到单次静音指令");
        event.put("9", "设备收到连续静音指令");
        event.put("10", "拆卸报警");
        event.put("11", "拆卸恢复");
        event.put("12", "在线");
        event.put("13", "异常");
        event.put("14", "模拟报警");*/


        GAS_MAP.put(GasEnum.EVENT, event);

    }

    private static ConcurrentHashMap<Object, Object> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }

  /*  public static void main(String[] args) {

        System.out.println(SmokeEnum.TYPE);

    }*/
}
