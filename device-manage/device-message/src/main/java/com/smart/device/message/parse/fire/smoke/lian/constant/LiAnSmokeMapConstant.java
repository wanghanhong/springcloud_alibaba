package com.smart.device.message.parse.fire.smoke.lian.constant;

import com.smart.device.message.parse.fire.liquidlevel.constant.LiquidlevelEnum;
import com.smart.device.message.parse.fire.liquidlevel.constant.LiquidlevelMapConstant;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 三多
 * @Time 2020/4/10
 */
public class LiAnSmokeMapConstant {

    public static final ConcurrentHashMap<LiAnSmokeEnum, ConcurrentHashMap<String, String>> DICT_MAP = new ConcurrentHashMap<>();

    static {
        /******************************header*********************************/
        // 事件类型

        ConcurrentHashMap<String, String> alarm_Status = getConcurrentHashMapInstance();
        alarm_Status.put("1", "报警");
        alarm_Status.put("2", "静音");
        alarm_Status.put("3", "保留");
        alarm_Status.put("4", "低压");
        alarm_Status.put("5", "故障");

        alarm_Status.put("6", "数据异常");
        alarm_Status.put("7", "正常");
        alarm_Status.put("8", "设备收到单次静音指令");
        alarm_Status.put("9", "设备收到连续静音指令");
        alarm_Status.put("10", "拆卸报警");

        alarm_Status.put("11", "拆卸恢复");
        alarm_Status.put("12", "在线");
        alarm_Status.put("13", "异常");
        alarm_Status.put("14", "模拟报警");

        DICT_MAP.put(LiAnSmokeEnum.Alarm_Status, alarm_Status);

    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }

    public static void main(String[] args) {
        System.out.println(LiAnSmokeEnum.Alarm_Status.toString());

        System.out.println(LiAnSmokeMapConstant.DICT_MAP.get(LiAnSmokeEnum.Alarm_Status));
        System.out.println(LiquidlevelMapConstant.WANLIN_MAP.get(LiquidlevelEnum.CONTENT));

        String str = LiAnSmokeMapConstant.DICT_MAP.get(LiAnSmokeEnum.Alarm_Status).get(1);
        System.out.println(12);
        System.out.println(str);
    }





}
