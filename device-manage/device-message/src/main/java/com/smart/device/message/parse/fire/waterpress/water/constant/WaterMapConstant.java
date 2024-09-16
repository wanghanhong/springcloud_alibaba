package com.smart.device.message.parse.fire.waterpress.water.constant;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 三多
 * @Time 2020/4/10
 */
public class WaterMapConstant {

    public static final ConcurrentHashMap<WaterEnum, ConcurrentHashMap<String, String>> HQ_MAP = new ConcurrentHashMap<>();

    static {
        /******************************header*********************************/
        //类型
        ConcurrentHashMap<String, String> type = getConcurrentHashMapInstance();
        type.put("10", "通用数据包");
        HQ_MAP.put(WaterEnum.TYPE, type);
        //版本
        ConcurrentHashMap<String, String> version = getConcurrentHashMapInstance();
        version.put("2F", "4.2");
        HQ_MAP.put(WaterEnum.VERSION, version);
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
        HQ_MAP.put(WaterEnum.DEV, dev);
        //生产厂家
        ConcurrentHashMap<String, String> manu = getConcurrentHashMapInstance();
        manu.put("01", "华强技术");
        HQ_MAP.put(WaterEnum.MANU, manu);
        //运营商
        ConcurrentHashMap<String, String> opt = getConcurrentHashMapInstance();
        opt.put("01", "中国移动");
        opt.put("02", "中国联通");
        opt.put("03", "中国电信");
        opt.put("FF", "未知");
        HQ_MAP.put(WaterEnum.OPT, opt);
        //加密方式
        ConcurrentHashMap<String, String> encrypt = getConcurrentHashMapInstance();
        encrypt.put("00", "消息体不加密");
        encrypt.put("01", "消息体经过 RSA 算法加密");
        encrypt.put("10", "消息体经过 AES 算法加密");
        HQ_MAP.put(WaterEnum.ENCRYPT, encrypt);
        /******************************data*********************************/
        //数据类型
        ConcurrentHashMap<String, String> dataType = getConcurrentHashMapInstance();
        dataType.put("1536", "通用数据上报命令");
        dataType.put("1728", "设备远程设置命令");
        HQ_MAP.put(WaterEnum.DATA_TYPE, dataType);
        /**
         * 告警
         *  0：表示解除相应报警事件
         *  1：表示发生相应报警事件
         *  bit0：水压低压报警 bit1：水压高压报警 bit5:电池低压报警 bit7：设备故障报警
         *  故障报警
         */
        ConcurrentHashMap<String, String> alert = getConcurrentHashMapInstance();
        alert.put("0", "解除相应报警事件");
        alert.put("1", "水压低压报警");
        alert.put("2", "水压高压报警");
        alert.put("32", "电池低电压");
        alert.put("128", "设备故障报警");
        HQ_MAP.put(WaterEnum.ALERT, alert);
        //设备模式
        ConcurrentHashMap<String, String> devCode = getConcurrentHashMapInstance();
        devCode.put("0", "设备正常");
        devCode.put("01", "水压传感器故障");
        HQ_MAP.put(WaterEnum.DEV_CODE, devCode);
        //设备模式
        ConcurrentHashMap<String, String> devMode = getConcurrentHashMapInstance();
        devMode.put("0", "正常模式");
        devMode.put("255", "设备开机");
        HQ_MAP.put(WaterEnum.DEV_MODE, devMode);
        //设备自检
        ConcurrentHashMap<String, String> selfDetectRet = getConcurrentHashMapInstance();
        selfDetectRet.put("0", "自检通过");
        selfDetectRet.put("1", "自检失败");
        HQ_MAP.put(WaterEnum.SELF_DETECT_RET, selfDetectRet);

        //时间单位
        ConcurrentHashMap<String, String> uInt = getConcurrentHashMapInstance();
        uInt.put("0", "秒");
        uInt.put("1", "分");
        uInt.put("2", "时");
        HQ_MAP.put(WaterEnum.UINT, uInt);

    }

    private static ConcurrentHashMap<String, String> getConcurrentHashMapInstance() {
        return new ConcurrentHashMap<>();
    }


}
