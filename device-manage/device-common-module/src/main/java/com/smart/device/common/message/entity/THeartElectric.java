package com.smart.device.common.message.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 电力设备心跳日志
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_heart_electric")
@ApiModel(value = "THeartElectric对象", description = "电力设备心跳日志")
public class THeartElectric implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    @ApiModelProperty(value = "设备ID")
    private Long deviceId;
    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "设备编号")
    private Long deviceCode;

    @ApiModelProperty(value = "设备类型")
    private Integer deviceType;

    @ApiModelProperty(value = "设备类型名称")
    private String deviceTypeName;
    @ApiModelProperty(value = "设备类型")
    private Integer parentType;
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
    private Date outFactoryTime;

    @ApiModelProperty(value = "报废时间")
    private Date scrapTime;

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

    @ApiModelProperty(value = "A项电压")
    private Float voltageA;

    @ApiModelProperty(value = "B项电压")
    private Float voltageB;

    @ApiModelProperty(value = "C项电压")
    private Float voltageC;

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

    @ApiModelProperty(value = "报警数值")
    private Float alarmvalue;

    @ApiModelProperty(value = "电流报警值")
    private Float alarmCurrentValue;

    @ApiModelProperty(value = "温度报警值")
    private Float alarmTemperature;

    @ApiModelProperty(value = "事件类型")
    private Integer type;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "新增时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "状态更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;

    @ApiModelProperty(value = "环境温度")
    private Float currentTemperature;


}
