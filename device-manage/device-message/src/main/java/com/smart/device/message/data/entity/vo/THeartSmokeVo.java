package com.smart.device.message.data.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 烟感设备心跳日志
 *
 * @author f
 */
@Data
@ApiModel(value = "THeartSmoke对象")
public class THeartSmokeVo{

    private static final long serialVersionUID = 1L;
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

    @ApiModelProperty(value = "设备电压")
    private Float deviceVolt;

    @ApiModelProperty(value = "设备剩余电量(百分比)")
    private Float electricQuantity;

    @ApiModelProperty(value = "设备摄氏温度(摄氏度)")
    private Float temperature;

    @ApiModelProperty(value = "烟雾浓度")
    private Float smokeScope;

    @ApiModelProperty(value = "最新自检时间")
    private LocalDateTime selfDetectTime;

    @ApiModelProperty(value = "最新自检结果")
    private String selfDetectRet;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "新增时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "状态更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;

    @ApiModelProperty(value = "心跳间隔")
    private Integer heartInterval;
    @ApiModelProperty(value = "iccid 号")
    private String iccid;
    @ApiModelProperty(value = "最后上报时间")
    private LocalDateTime finalTime;

    /**
     * 设备状态 0 新安装 1 在线 2 离线 3  报废
     */
    @ApiModelProperty(value = "设备状态")
    private Integer deviceState;
    @ApiModelProperty(value = "设备状态")
    private String deviceStateName;

}
