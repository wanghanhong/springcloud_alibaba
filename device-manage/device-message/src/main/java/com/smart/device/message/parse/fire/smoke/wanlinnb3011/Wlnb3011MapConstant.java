package com.smart.device.message.parse.fire.smoke.wanlinnb3011;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 三多
 * @Time 2020/4/10
 */
public class Wlnb3011MapConstant {

    public static final ConcurrentHashMap<Wlnb3011Enum, ConcurrentHashMap<String, String>> WANLIN_MAP = new ConcurrentHashMap<>();

    static {
        /******************************header*********************************/
        // 事件类型

        ConcurrentHashMap<String, String> event = getConcurrentHashMapInstance();
        event.put("offline", "离线");
        event.put("online", "在线");
        event.put("abnormal", "异常");
        event.put("01", "设备报警");
        event.put("10", "设备自检");
        event.put("11", "报警恢复");
        event.put("02", "电量不足");

        WANLIN_MAP.put(Wlnb3011Enum.event, event);

    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }


}
