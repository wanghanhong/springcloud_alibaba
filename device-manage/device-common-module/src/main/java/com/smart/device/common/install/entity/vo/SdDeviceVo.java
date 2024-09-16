package com.smart.device.common.install.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class SdDeviceVo implements Serializable {
    private static final long serialVersionUID = -97522868494947073L;


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
    @ApiModelProperty(value = "新增时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最新自检时间")
    private LocalDateTime selfDetectTime;

    @ApiModelProperty(value = "最新自检结果")
    private String selfDetectRet;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "状态更新时间")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "心跳间隔")
    private Integer heartInterval;
    @ApiModelProperty(value = "iccid 号")
    private String iccid;
    @ApiModelProperty(value = "最后上报时间")
    private LocalDateTime finalTime;

    private String userId;
    private String province;
    private String city;
    private String county;
    private String town;


}
