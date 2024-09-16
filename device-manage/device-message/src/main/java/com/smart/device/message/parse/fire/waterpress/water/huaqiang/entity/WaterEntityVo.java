package com.smart.device.message.parse.fire.waterpress.water.huaqiang.entity;

import com.smart.device.common.message.entity.THeartWaterpressSon;
import lombok.Data;

/**
 * 华强通信设备
 *
 * @author 三多
 * @Time 2020/4/8
 */
@Data
public class WaterEntityVo {

    /**
     * 包类型（Type）
     */
    private String type;
    /**
     * 包序号(Index),从 0-255 循环填充
     */
    private String index;
    /**
     * 协议版本号(Ver),表示当前数据协议版本号 4.2 表示为 42
     */
    private String ver;
    /**
     * 设备类型(Dev),未注册的设备类型数据将被丢弃处理。
     */
    private String dev;
    /**
     * 生产厂商(Manu),未注册的厂商数据将被丢弃处理。
     */
    private String manu;
    /**
     * 原始包（Initiator）,表示原始包序号，只有当包类型为应答包时该字段有意义。
     */
    private String initiator;
    /**
     * 运营商（Opt）,当前设备所驻留网络的运营商。
     */
    private String opt;
    /**
     * 属性字段（Attri),按位表示当前消息属性。
     */
    private String attri;
    /**
     * 消息长度
     */
    private Integer dataLength;
    /**
     * 加密方式
     * 00：表示消息体不加密
     * 01：表示消息体经过 RSA 算法加密
     * 10：表示消息体经过 AES 算法加密
     */
    private String encryptType;
    /**
     * 时间字段（Time）,表示消息打包时间
     */
    private String time;
    /**
     * 设备识别码（IMEI）
     */
    private String imei;
    /**
     * data
     */
    /**
     * 通用数据上报 0x0600
     */
    private String cmd;
    /**
     * 告警
     * 0：表示解除相应报警事件
     * 1：表示发生相应报警事件
     * bit0：水压低压报警 bit1：水压高压报警 bit5:电池低压报警 bit7：设备故障报警
     * 故障报警
     */
    private Integer alert;
    /**
     * 设备故障码 0-设备正常 01 - 水压传感器故障
     */
    private String devCode;
    /**
     * 设备模式 0-正常模式 255 -设备开机
     */
    private String devMode;
    /**
     * 设备电池电压 绝对电压值*100.例如采集到电池电压为 3.15V,
     * 则该字段填 315. 如不支持，则填 0x00
     */
    private Float devVolt;
    /**
     * 设备电池电压百分比
     * 数值范围 0~100，单位为百分比，电池电量剩余
     * 20%编码 0x14
     */
    private Integer devVoltPer;
    /**
     * 设备摄氏温度 数值范围-100~100，单位为度，原始数据+100，
     * 举例 35 摄氏度表示为 135，编码为 0x87
     */
    private Integer devTemp;
    /**
     * 小区信息 数值范围 0~0xFFFFFFFF
     */
    private Integer cellId;
    /**
     * 信号强度
     * RSRP 数值范围 0~200，原始数值*（-1）表示参考信
     * 号接收功率，为负数，范围为-44~-140dBm
     */
    private Integer rsrp;
    /**
     * 1 采集间隔时间单位       0-秒，1-分，2-时
     */
    private String colletUint;
    /**
     * 采集间隔时间值
     * 时间单位 x 时间值表示采集时间，如 2 小时，
     * 0x0202， 0 表示不设置
     */
    private Integer colletValue;
    /**
     * 上传间隔时间单位 0-秒，1-分，2-时
     */
    private String transUint;
    /**
     * 上传间隔时间值
     * 时间单位 x 时间值表示采集时间，如 2 小时，
     * 0x0202， 0 表示不设置
     */
    private Integer transValue;
    /**
     * 设备水压低压报警门限
     * 数值范围 0~2.5mpa，单位 kpa，如 1Mpa 水压，
     * 为 1000kpa,表示为 0x000003E8
     */
    private Integer wpLowPhd;
    /**
     * 设备水压高压报警门限
     * 数值范围 0~2.5mpa，单位 kpa，如 1Mpa 水压，
     * 为 1000kpa,表示为 0x000003E8
     */
    private Integer wpHighPhd;
    /**
     * Reserve 保留
     */
    private Long reserve;
    /**
     * 水压数据包数量
     * 数值范围 0~48，表示本包数据包含的有效水压数据量
     */
    private Integer wpNum;
    /**
     * 水压采集时间 16 进制（距离 2018/9/10 0:0:0 的时间秒）（数
     * 值+1536508800 为真实时间）
     * 数值范围 0~2086 年，UTC+8 东 8 区时间，单位
     * 为秒,举例 2018/9/12 11:15:35（对应的时间秒是1536722135）距离 2018/9/10 0:0:0（对应的时间
     * 秒 1536508800 ），值为 213335 ，编码为0x00034157
     */
    private String wpTime1;
    /**
     * 水压数据 16 进制（原始数值）
     * 数值范围 0~2.5mpa，单位 kpa，如 1Mpa 水压，
     * 为 1000kpa,表示为 0x000003E8
     */
    private Integer wpData1;
    /**
     * 水压采集时间 16 进制（距离 2018/9/10 0:0:0 的时间秒）（数
     * 值+1536508800 为真实时间）
     * 数值范围 0~2086 年，UTC+8 东 8 区时间，单位
     * 为秒,举例 2018/9/12 11:15:35（对应的时间秒是
     * 1536722135）距离 2018/9/10 0:0:0（对应的时间
     * 秒 1536508800 ） ， 值 为 213335 ， 编 码 为
     * 0x00034157
     */
    private String wpTime2;

    /**
     * 水压数据 16 进制（原始数值）
     * 数值范围 0~2.5mpa，单位 kpa，如 1Mpa 水压，
     * 为 1000kpa,表示为 0x000003E8
     */
    private Integer wpData2;    /**
     * 水压采集时间 16 进制（距离 2018/9/10 0:0:0 的时间秒）（数
     * 值+1536508800 为真实时间）
     * 数值范围 0~2086 年，UTC+8 东 8 区时间，单位
     * 为秒,举例 2018/9/12 11:15:35（对应的时间秒是
     * 1536722135）距离 2018/9/10 0:0:0（对应的时间
     * 秒 1536508800 ） ， 值 为 213335 ， 编 码 为
     * 0x00034157
     */
    private String wpTime3;

    /**
     * 水压数据 16 进制（原始数值）
     * 数值范围 0~2.5mpa，单位 kpa，如 1Mpa 水压，
     * 为 1000kpa,表示为 0x000003E8
     */
    private Integer wpData3;    /**
     * 水压采集时间 16 进制（距离 2018/9/10 0:0:0 的时间秒）（数
     * 值+1536508800 为真实时间）
     * 数值范围 0~2086 年，UTC+8 东 8 区时间，单位
     * 为秒,举例 2018/9/12 11:15:35（对应的时间秒是
     * 1536722135）距离 2018/9/10 0:0:0（对应的时间
     * 秒 1536508800 ） ， 值 为 213335 ， 编 码 为
     * 0x00034157
     */
    private String wpTime4;

    /**
     * 水压数据 16 进制（原始数值）
     * 数值范围 0~2.5mpa，单位 kpa，如 1Mpa 水压，
     * 为 1000kpa,表示为 0x000003E8
     */
    private Integer wpData4;
    /**
     * 水压数据子类，超过4个数据时存储到此对象里
     * 否则为null
     */
    private THeartWaterpressSon wpSon;


}
