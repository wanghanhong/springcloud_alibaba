package com.smart.device.common.access.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 温湿度计设备基础信息
 * @author jungle
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TDeviceTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号")
    private Long deviceCode;

    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型")
    private Integer deviceType;

    @ApiModelProperty(value = "设备类型名称")
    private String deviceTypeName;
    @ApiModelProperty(value = "设备类型")
    private Integer parentType;
    /**
     * 设备型号
     */
    @ApiModelProperty(value = "设备型号")
    private String deviceModel;

    /**
     * 移动设备识别码
     */
    @ApiModelProperty(value = "移动设备识别码")
    private Long imei;

    /**
     * 厂商id
     */
    @ApiModelProperty(value = "厂商id")
    private Long productId;

    /**
     * 厂商名称
     */
    @ApiModelProperty(value = "厂商名称")
    private String productName;
    /**
     * 新增时间
     */
    @ApiModelProperty(value = "新增时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 删除标识(0 正常 1删除)
     */
    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;
    /**
     * 设备状态 1正常 2 异常
     */
    @ApiModelProperty(value = "设备状态")
    private Integer deviceState;
    /**
     * 设备状态  1正常 2 异常
     */
    @ApiModelProperty(value = "设备状态")
    private String deviceStateName;
    @ApiModelProperty(value = "最后上报时间")
    private LocalDateTime finalTime;

    @ApiModelProperty(value = "操作单位")
    private Long opCompanyId;
    @ApiModelProperty(value = "操作人")
    private Long opUserId;

}
