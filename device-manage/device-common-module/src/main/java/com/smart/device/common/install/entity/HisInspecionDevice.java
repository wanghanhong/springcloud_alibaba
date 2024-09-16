package com.smart.device.common.install.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class HisInspecionDevice implements Serializable {

    @ApiModelProperty(value = "设备id")
    private Long deviceId;

    @ApiModelProperty(value = "设备编码")
    private Long deviceCode;

    @ApiModelProperty(value = "巡检问题描述")
    private String deviceQuestion;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "楼层")
    private String  floor;

    @ApiModelProperty(value = "楼号")
    private String buildingNo;

    @ApiModelProperty(value = "提交时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "公司id")
    private Integer companyId;

    @ApiModelProperty(value = "消防栓巡检状态 设备状态1.正常2.待修3.呆换4.丢失")
    private Integer fireEngineStatus;

    private  String deviceName;
    @ApiModelProperty(value = "消防栓安装位置")
    private String location;

    private Long inspectionId;

    @ApiModelProperty(value = "设备类型")
    private Integer DeviceType;

    private  Long id;







}
