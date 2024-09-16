package com.smart.device.common.constant;

public final class DeviceConstant {

    // 烟感设备
    public static final Integer device_type_smoke = 11;
    // 水压 压力设备
    public static final Integer device_type_waterpress = 13;
    // 液位设备
    public static final Integer device_type_liquidlevel = 15;
    // 燃气设备
    public static final Integer device_type_gas = 16;
    // 电力设备
    public static final Integer device_type_electric = 17;
    // 摄像头
    public static final Integer device_type_cameras = 20;


    public static final String device_type_smoke_name = "烟感设备";
    public static final String device_type_waterpress_name = "压力表";
    public static final String device_type_liquidlevel_name = "液位计";
    public static final String device_type_gas_name = "燃气设备";
    public static final String device_type_electric_name = "智慧用电";
    public static final String device_type_cameras_name = "摄像头";

    /**
     * 处理状态
     */
    public static final Integer DEVICE_STATE_ONLINE = 1; // 正常在线
    public static final String DEVICE_STATE_ONLINE_NAME = "在线";
    public static final Integer DEVICE_STATE_OFFLINE = 2; // 异常离线
    public static final String DEVICE_STATE_OFFLINE_NAME = "异常";

    public static final Integer DEVICE_DEAL_STATUS_NOHANDEL = 0; //未处理
    public static final String DEVICE_DEAL_STATUS_NOHANDEL_NAME = "未处理"; //
    public static final Integer DEVICE_DEAL_STATUS_LOCK = 1; //被锁定
    public static final Integer DEVICE_DEAL_STATUS_ONHANDEL = 2; //处理中
    public static final Integer DEVICE_DEAL_STATUS_YESHANDEL = 3; //已处理
    public static final String DEVICE_DEAL_STATUS_YESHANDEL_NAME = "已处理"; //已处理
    public static final Integer DEVICE_DEAL_STATUS_AUTO = 9; //自动消警

    /**
     * 报警等级
     */
    public static final Integer LEVEL_NORMAL = 0; //正常
    public static final Integer LEVEL_JUST = 1; //一般
    public static final Integer LEVEL_SERIOUS = 2; //严重



}

