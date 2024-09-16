package com.smart.device.common.message.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author jungle
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_alarm_temp")
@ApiModel(value = "TAlarmTemp对象", description = "温湿度计报警日志")
public class TAlarmTemp {

    private static final long serialVersionUID = 1L;
    private Long id;
    @ApiModelProperty(value = "设备ID")
    private Long deviceId;
    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "设备编号")
    private Long deviceCode;

    @ApiModelProperty(value = "移动设备识别码")
    private Long imei;

    @ApiModelProperty(value = "设备类型名称")
    private String deviceTypeName;

    @ApiModelProperty(value = "设备类型")
    private Integer deviceType;
    @ApiModelProperty(value = "设备类型")
    private Integer parentType;
    @ApiModelProperty(value = "设备型号")
    private String deviceModel;

    @ApiModelProperty(value = "厂商id")
    private Long productId;

    @ApiModelProperty(value = "厂商名称")
    private String productName;

    @ApiModelProperty(value = "报警事件时间")
    private LocalDateTime eventTime;
    @ApiModelProperty(value = "事件code")
    private String eventCode;
    @ApiModelProperty(value = "报警等级 1 一般 2重要 3严重")
    private Integer level;

    @ApiModelProperty(value = "警报类型10温度报警12湿度报警")
    private Integer type;

    @ApiModelProperty(value = "报警处理状态0.未处理，1.被锁定，2.处理中，3.已处理，9.自动消警")
    private Integer state;
    private String stateName;
    @ApiModelProperty(value = "确认类型 0.未知 1.确认")
    private Integer confirmType;

    @ApiModelProperty(value = "处理时间")
    private LocalDateTime dealTime;

    @ApiModelProperty(value = "处理结果")
    private String result;

    @ApiModelProperty(value = "新增时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "状态更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;

    @ApiModelProperty(value = "备注")
    private String content;
}
