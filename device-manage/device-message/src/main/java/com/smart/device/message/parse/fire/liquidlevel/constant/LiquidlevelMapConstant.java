package com.smart.device.message.parse.fire.liquidlevel.constant;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 三多
 * @Time 2020/4/10
 */
public class LiquidlevelMapConstant {

    public static final ConcurrentHashMap<LiquidlevelEnum, ConcurrentHashMap<String, String>> WANLIN_MAP = new ConcurrentHashMap<>();

    static {
        /******************************header*********************************/
        // 事件类型

        ConcurrentHashMap<String, String> content = getConcurrentHashMapInstance();
        content.put("10", "设备自检");
        content.put("02", "'电量不足'");
        content.put("03", "低水位报警");
        content.put("13", "低水位报警恢复");
        content.put("04", "高水位报警");
        content.put("14", "'高水位报警恢复");

        WANLIN_MAP.put(LiquidlevelEnum.CONTENT, content);

    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }


}
