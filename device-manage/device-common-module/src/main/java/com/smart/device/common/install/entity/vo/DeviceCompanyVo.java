package com.smart.device.common.install.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

/**
 * 单位-设备-关联表
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DeviceCompanyVo implements Serializable{

    @ApiModelProperty(value = "设备ID")
    private Long deviceId;
    @ApiModelProperty(value = "设备名称")
    private String deviceName;
    @ApiModelProperty(value = "设备编号")
    private Long deviceCode;
    @ApiModelProperty(value = "imei")
    private Long imei;
    @ApiModelProperty(value = "设备类型")
    private Integer deviceType;
    @ApiModelProperty(value = "设备类型")
    private Integer parentType;
    @ApiModelProperty(value = "设备类型名称")
    private String deviceTypeName;

    @ApiModelProperty(value = "设备状态")
    private String deviceStateName;
    @ApiModelProperty(value = "最后上报时间")
    private String finalTime;

    @ApiModelProperty(value = "设备状态")
    private String state;
    @ApiModelProperty(value = "告警创建事件")
    private String time;
    @ApiModelProperty(value = "状态")
    private String type;

    @ApiModelProperty(value = "建筑物ID")
    private Long buildingId;
    @ApiModelProperty(value = "建筑物名称")
    private String buildingName;
    @ApiModelProperty(value = "楼层")
    private Integer buildingFloor;
    @ApiModelProperty(value = "位置")
    private String location;
    @ApiModelProperty(value = "经度")
    private String longitude;
    @ApiModelProperty(value = "纬度")
    private String latitude;
    @ApiModelProperty(value = " 备注")
    private String content;

    @ApiModelProperty(value = "单位ID")
    private Long companyId;
    @ApiModelProperty(value = " 单位名称")
    private String companyName;

    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "县")
    private String county;
    @ApiModelProperty(value = "乡村")
    private String town;
    @ApiModelProperty(value = "小区")
    private String housing;

    @ApiModelProperty(value = "消防责任人")
    private String inchargeName;
    @ApiModelProperty(value = "消防责任人电话")
    private String inchargePhone;
    @ApiModelProperty(value = "消防管理人")
    private String managerName;
    @ApiModelProperty(value = "消防管理人电话")
    private String managerPhone;
    @ApiModelProperty(value = "兼职消防管理人")
    private String parttimeName;
    @ApiModelProperty(value = "兼职消防管理人电话")
    private String parttimePhone;

    /**
     * 当前记录起始索引
     */
    private Integer pageNum = 1;
    /**
     * 每页显示记录数
     */
    private Integer pageSize = 10;

    private Long userId;
    private Date createTime;

    private String remarks;
    private String areaName;

    // 大屏统计用，如果大屏修改，则可以删除
    private String today;
    // 操作人
    private String username;
    private String phone;
    private Integer isXcx;
    private Long opUserId;

    private String dayStart;
    private String dayEnd;

}
