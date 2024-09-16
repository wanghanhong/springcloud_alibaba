package com.smart.device.message.common.dict;


import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 三多
 * @Time 2020/4/10
 */
public class DictMapConstant {

    public static final ConcurrentHashMap<DictEnum, ConcurrentHashMap<String, String>> DICT_MAP = new ConcurrentHashMap<>();

    static {
        /******************************header*********************************/

        ConcurrentHashMap<String, String> devicetype = getConcurrentHashMapInstance();
        devicetype.put("-1", "设备类型");
        devicetype.put("1", "烟感");
        DICT_MAP.put(DictEnum.DEVICE_TYPE, devicetype);

        ConcurrentHashMap<String, String> level = getConcurrentHashMapInstance();
        level.put("-1", "报警等级");
        level.put("1", "正常");
        level.put("2", "一般");
        level.put("3", "重要");
        level.put("4", "严重");
        DICT_MAP.put(DictEnum.LEVEL, level);

        ConcurrentHashMap<String, String> type = getConcurrentHashMapInstance();
        type.put("0", "正常");
        type.put("1", "火灾报警");
        type.put("2", "故障");
        type.put("3", "欠压");
        type.put("4", "拆卸");
        type.put("5", "自检");
        DICT_MAP.put(DictEnum.TYPE, type);

        ConcurrentHashMap<String, String> state = getConcurrentHashMapInstance();
        state.put("-1", "设备类型");
        state.put("0", "未处理");
        state.put("1", "被锁定");
        state.put("2", "已处理");
        state.put("3", "处理中");
        state.put("9", "自动消警");
        DICT_MAP.put(DictEnum.STATE, state);

    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> state = DICT_MAP.get(DictEnum.STATE);
        System.out.println(state.keys());
        for (String key : state.keySet()) {
            System.out.println(key);
        }

    }
}
