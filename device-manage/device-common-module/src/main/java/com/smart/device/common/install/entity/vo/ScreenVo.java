package com.smart.device.common.install.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScreenVo {

    @ApiModelProperty(value = "设备状态")
    private Integer deviceState;
    @ApiModelProperty(value = "设备状态")
    private String deviceStateName;

    @ApiModelProperty(value = "设备类型")
    private Integer deviceType;
    @ApiModelProperty(value = "设备类型")
    private String deviceTypeName;
    @ApiModelProperty(value = "设备大类类型")
    private Integer parentType;

    @ApiModelProperty(value = "数量")
    private int countNum;

    @ApiModelProperty(value = "事件")
    private String eventCode;
    @ApiModelProperty(value = "事件名称")
    private String eventCodeName;
    @ApiModelProperty(value = "时间")
    private LocalDateTime eventTime;

    @ApiModelProperty(value = "百分比")
    private float percentNum;
    @ApiModelProperty(value = "合计总数")
    private int totalNum;

    @ApiModelProperty(value = "单位ID")
    private Long companyId;
    @ApiModelProperty(value = " 单位名称")
    private String companyName;
    @ApiModelProperty(value = "消防责任人")
    private String inchargeName;
    @ApiModelProperty(value = "消防责任人电话")
    private String inchargePhone;
    @ApiModelProperty(value = "建筑物名称")
    private String buildingName;
    @ApiModelProperty(value = "楼层")
    private Integer buildingFloor;
    @ApiModelProperty(value = "安装位置")
    private String location;
    @ApiModelProperty(value = "设备Id")
    private String deviceId;
    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "报警数量")
    private Integer alarmNum;
    @ApiModelProperty(value = "其他数量")
    private Integer otherNum;

    @ApiModelProperty(value = "安装位置")
    private String screenLocation;

    @ApiModelProperty(value = " 1 消防单位 2社会单位 3九小场所 4维保单位")
    private String typeName;
    @ApiModelProperty(value = " 1 消防单位 2社会单位 3九小场所 4维保单位")
    private Integer type;
    //  设备 ids
    private String ids;
    private String content;
    // 1 报警类 2 故障类
    private Integer strategyType;


}
