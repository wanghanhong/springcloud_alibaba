package com.smart.device.install.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.smart.device.common.install.entity.TBaseFirehydrant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 建筑物-设备合计
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "建筑物-设备")
public class InsBuildFloorVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "巡检计划层号ID")
    private Long insBuildFloorId;
    @ApiModelProperty(value = "巡检计划ID")
    private Long inspectionId;
    @ApiModelProperty(value = "关联的ID")
    private Long insBuildId;

    @ApiModelProperty(value = "单位ID")
    private Long companyId;
    @ApiModelProperty(value = "单位名称")
    private String companyName;
    @ApiModelProperty(value = "建筑物编码")
    private Long buildingId;

    @ApiModelProperty(value = "建筑物名称")
    private String buildingName;

    @ApiModelProperty(value = "0地下，1地上")
    private Integer type;

    @ApiModelProperty(value = "层号")
    private Integer floorNum;
    private Integer status;

    @ApiModelProperty(value = "消防通道数量")
    private int escapeRouteNum;
    @ApiModelProperty(value = "消防栓数量")
    private int firehydrantNum;
    @ApiModelProperty(value = "设备数量")
    private int fireAllNum;


    @ApiModelProperty(value = "消防通道是否选择")
    @TableField(exist = false)
    private Integer escapeRouteIs;
    @ApiModelProperty(value = "消防栓是否选择")
    @TableField(exist = false)
    private Integer firehydrantIs;
    @ApiModelProperty(value = "设备是否选择")
    @TableField(exist = false)
    private Integer fireAllIs;

    @ApiModelProperty(value = "设备列表")
    private List<DeviceVo> deviceVos = new ArrayList<>();
    @ApiModelProperty(value = "消防栓列表")
    private List<TBaseFirehydrant> firehydrantVos = new ArrayList<>();

    @TableField(exist = false)
    private String sortno;

    private Integer smokeNum;
    private Integer waterpressNum;
    private Integer electricNum;



}
