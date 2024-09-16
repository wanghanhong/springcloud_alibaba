package com.smart.device.message.data.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 电力设备心跳日志
 *
 * @author f
 */
@Data
public class THeartElectricVo {

    private Long id;
    @ApiModelProperty(value = "设备")
    private Long deviceId;
    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "设备编号")
    private Long deviceCode;

    @ApiModelProperty(value = "设备类型")
    private Integer deviceType;
    @ApiModelProperty(value = "设备类型")
    private Integer parentType;
    @ApiModelProperty(value = "设备类型名称")
    private String deviceTypeName;

    @ApiModelProperty(value = "设备型号")
    private String deviceModel;

    @ApiModelProperty(value = "移动设备识别码")
    private Long imei;

    @ApiModelProperty(value = "移动用户识别码")
    private Long imsi;

    @ApiModelProperty(value = "厂商id")
    private Long productId;

    @ApiModelProperty(value = "厂商名称")
    private String productName;

    @ApiModelProperty(value = "协议")
    private String protocol;

    @ApiModelProperty(value = "出厂时间")
    private LocalDateTime outFactoryTime;

    @ApiModelProperty(value = "报废时间")
    private LocalDateTime scrapTime;

    @ApiModelProperty(value = "硬件版本号")
    private String hardVersion;

    @ApiModelProperty(value = "软件版本号")
    private String softVersion;

    @ApiModelProperty(value = "心跳时间")
    private LocalDateTime heartTime;
    @ApiModelProperty(value = "事件code")
    private String eventCode;
    @ApiModelProperty(value = "信号强度")
    private Integer signalStrength;

    @ApiModelProperty(value = "A项电流")
    private Float currentA;

    @ApiModelProperty(value = "B项电流")
    private Float currentB;

    @ApiModelProperty(value = "C项电流")
    private Float currentC;

    @ApiModelProperty(value = "N项电流")
    @TableField("current_N")
    private Float currentN;

    @ApiModelProperty(value = "A项温度")
    private Float temperatureA;

    @ApiModelProperty(value = "B项温度")
    private Float temperatureB;

    @ApiModelProperty(value = "C项温度")
    private Float temperatureC;

    @ApiModelProperty(value = "N项温度")
    private Float temperatureN;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "新增时间")
    private LocalDateTime createTime;


    @ApiModelProperty(value = "报警温度设置的值")
    private Float alarmTemperature;
    @ApiModelProperty(value = "最大电流设置")
    private String alarmElectric;
    @ApiModelProperty(value = "漏电电流设置")
    private String alarmElectricLeakage;
     /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    private Long companyId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String companyName;

    /**
     * 建筑物编号
     */
    @ApiModelProperty(value = "建筑物编号")
    private Long buildingId;

    /**
     * 建筑物
     */
    @ApiModelProperty(value = "建筑物名称")
    private String buildingName;

    /**
     * 楼层
     */
    @ApiModelProperty(value = "楼层")
    private Integer buildingFloor;

    /**
     * 安装位置
     */
    @ApiModelProperty(value = "安装位置")
    private String location;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private String longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "设备安装图地址")
    private String fileUrl;

    /**
     * 设备状态 0 新安装 1 在线 2 离线 3  报废
     */
    @ApiModelProperty(value = "设备状态")
    private Integer deviceState;

    /**
     * 设备状态 0 新安装 1 在线 2 离线 3  报废
     */
    @ApiModelProperty(value = "设备状态")
    private String deviceStateName;

    @ApiModelProperty(value = "环境温度")
    private Float currentTemperature;

}
