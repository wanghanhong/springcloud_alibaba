package com.smart.device.common.message.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author jungle
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_heart_temp")
@ApiModel(value = "THeartTemp对象", description = "温湿度计心跳日志")
public class THeartTemp implements Serializable {

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

    @ApiModelProperty(value = "心跳时间")
    private LocalDateTime heartTime;
    @ApiModelProperty(value = "事件code")
    private String eventCode;
    @ApiModelProperty(value = "IC卡ID")
    private String iccid;

    @ApiModelProperty(value = "新增时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "状态更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;
    @ApiModelProperty(value = "点位编码")
    private String address;
    @ApiModelProperty(value = "事件类型")
    private Integer type;

    @ApiModelProperty(value = "湿度比例")
    private String humidity;

    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "设备状态")
    private Integer deviceState;
    @ApiModelProperty(value = "设备状态")
    private String deviceStateName;
}
