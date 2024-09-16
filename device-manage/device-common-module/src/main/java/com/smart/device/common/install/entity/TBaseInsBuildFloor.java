package com.smart.device.common.install.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 巡检详情
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_base_ins_build_floor")
@ApiModel(value = "TBaseInspectionSon对象", description = "巡检详情")
public class TBaseInsBuildFloor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "建筑物ID")
    private Long buildingId;

    @ApiModelProperty(value = "建筑物名称")
    private String buildingName;

    @ApiModelProperty(value = "巡检计划ID")
    private Long inspectionId;
    @ApiModelProperty(value = "巡检建筑物ID")
    private Long insBuildId;

    @ApiModelProperty(value = "层号")
    private Integer floorNum;

    @ApiModelProperty(value = "消防通道")
    private Integer escapeRouteNum;

    @ApiModelProperty(value = "消防栓数量")
    private Integer firehydrantNum;

    @ApiModelProperty(value = "设备数量")
    private Integer fireAllNum;

    @ApiModelProperty(value = "消防通道是否选择")
    private Integer escapeRouteIs;
    @ApiModelProperty(value = "消防栓是否选择")
    private Integer firehydrantIs;
    @ApiModelProperty(value = "设备是否选择")
    private Integer fireAllIs;

    @ApiModelProperty(value = "烟感数量")
    private Integer smokeNum;

    @ApiModelProperty(value = "水压数量")
    private Integer waterpressNum;

    @ApiModelProperty(value = "电气数量")
    private Integer electricNum;
    @ApiModelProperty(value = "巡检点数量")
    private Integer floorPointNum;

    @ApiModelProperty(value = " 备注")
    private String content;
    @ApiModelProperty(value = "是否巡检0 未巡检 2巡检完成")
    private Integer status;

    @ApiModelProperty(value = "新增时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "状态更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;

    @ApiModelProperty(value = "巡检计划层号ID")
    @TableField(exist = false)
    private Long insBuildFloorId;



}
