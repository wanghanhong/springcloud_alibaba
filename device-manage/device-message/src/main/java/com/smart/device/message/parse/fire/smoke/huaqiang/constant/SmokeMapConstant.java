package com.smart.device.message.parse.fire.smoke.huaqiang.constant;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 三多
 * @Time 2020/4/10
 */
public class SmokeMapConstant {

    public static final ConcurrentHashMap<SmokeEnum, ConcurrentHashMap<String, String>> SMOKE_MAP = new ConcurrentHashMap<>();

    static {
        /******************************header*********************************/
        //类型
        ConcurrentHashMap<String, String> type = getConcurrentHashMapInstance();
        type.put("10", "通用数据包");
        SMOKE_MAP.put(SmokeEnum.TYPE, type);
        //版本
        ConcurrentHashMap<String, String> version = getConcurrentHashMapInstance();
        version.put("2F", "4.2");
        SMOKE_MAP.put(SmokeEnum.VERSION, version);
        //设备
        ConcurrentHashMap<String, String> dev = getConcurrentHashMapInstance();
        dev.put("02", "电动自行车追踪器（W302）");
        dev.put("03", "温湿度卡片（W303）");
        dev.put("04", "光感卡片（W303）");
        dev.put("05", "震动检测卡片（W303）");
        dev.put("06", "消防栓设备（W306）");
        dev.put("08", "烟雾监测设备（W308）");
        dev.put("09", "地磁停车设备（V501）");
        dev.put("0A", "便携式定位设备（W310）");
        dev.put("0C", "智能插座（W312）");
        SMOKE_MAP.put(SmokeEnum.DEV, dev);
        //生产厂家
        ConcurrentHashMap<String, String> manu = getConcurrentHashMapInstance();
        manu.put("01", "华强技术");
        SMOKE_MAP.put(SmokeEnum.MANU, manu);
        //运营商
        ConcurrentHashMap<String, String> opt = getConcurrentHashMapInstance();
        opt.put("01", "中国移动");
        opt.put("02", "中国联通");
        opt.put("03", "中国电信");
        opt.put("FF", "未知");
        SMOKE_MAP.put(SmokeEnum.OPT, opt);
        //加密方式
        ConcurrentHashMap<String, String> encrypt = getConcurrentHashMapInstance();
        encrypt.put("00", "消息体不加密");
        encrypt.put("", "消息体经过 RSA 算法加密");
        encrypt.put("", "消息体经过 AES 算法加密");
        SMOKE_MAP.put(SmokeEnum.ENCRYPT, encrypt);
        /******************************data*********************************/
        /**
         * 告警
         *  0：表示解除相应报警事件
         *  1：表示发生相应报警事件
         *  bit0：烟雾报警 bit2：电池低压报警 bit7:设备
         *  故障报警
         */
        ConcurrentHashMap<String, String> alert = getConcurrentHashMapInstance();
        alert.put("0", "解除相应报警事件");
        alert.put("1", "烟雾报警");
        alert.put("4", "电池低电压");
        alert.put("128", "设备故障报警");
        SMOKE_MAP.put(SmokeEnum.ALERT, alert);
        //设备模式
        ConcurrentHashMap<String, String> devCode = getConcurrentHashMapInstance();
        devCode.put("0", "设备正常");
        devCode.put("01", "烟感自检失败");
        SMOKE_MAP.put(SmokeEnum.DEV_CODE, devCode);
        //设备模式
        ConcurrentHashMap<String, String> devMode = getConcurrentHashMapInstance();
        devMode.put("0", "正常模式");
        devMode.put("255", "设备开机");
        SMOKE_MAP.put(SmokeEnum.DEV_MODE, devMode);
        //设备自检
        ConcurrentHashMap<String, String> selfDetectRet = getConcurrentHashMapInstance();
        selfDetectRet.put("0", "自检通过");
        selfDetectRet.put("1", "自检失败");
        SMOKE_MAP.put(SmokeEnum.SELF_DETECT_RET, selfDetectRet);

        ConcurrentHashMap<String, String> alarmType = getConcurrentHashMapInstance();
        // 正常
        alarmType.put("0", "0");
        // 报警
        alarmType.put("1", "1");
        alarmType.put("128", "2");
        // 欠压
        alarmType.put("4", "3");
        // 自检
        alarmType.put("5", "5");

        SMOKE_MAP.put(SmokeEnum.ALARM_TYPE, alarmType);
    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }


}
