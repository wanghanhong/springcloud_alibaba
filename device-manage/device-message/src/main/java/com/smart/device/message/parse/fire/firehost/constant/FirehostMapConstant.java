package com.smart.device.message.parse.fire.firehost.constant;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 三多
 * @Time 2020/4/10
 */
public class FirehostMapConstant {

    public static final ConcurrentHashMap<FirehostEnum, ConcurrentHashMap<String, String>> WANLIN_MAP = new ConcurrentHashMap<>();

    static {
        /******************************header*********************************/
        // 事件类型

        ConcurrentHashMap<String, String> event = getConcurrentHashMapInstance();
        event.put("0001", "心跳");
        event.put("0002", "登录");
        event.put("0003", "报警");
        event.put("0006", "故障");
        event.put("0007", "复位");
        event.put("000c", "主机上传配件情况");
        event.put("000d", "校准主机时间");

        WANLIN_MAP.put(FirehostEnum.event, event);

        ConcurrentHashMap<String, String> type = getConcurrentHashMapInstance();
        type.put("0001", "0");
//      type.put("0002","");
        type.put("0003", "1");
        type.put("0006", "2");
        type.put("0007", "6");
//      type.put("000c","");
//      type.put("000d","");

        WANLIN_MAP.put(FirehostEnum.TYPE, type);

    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }


}
