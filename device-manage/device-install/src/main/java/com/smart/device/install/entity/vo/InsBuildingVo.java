package com.smart.device.install.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "建筑物表")
public class InsBuildingVo implements Serializable{

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "巡检计划ID")
    private Long inspectionId;
    @ApiModelProperty(value = "关联的ID")
    private Long insBuildId;


    @ApiModelProperty(value = "建筑物ID")
    private Long buildingId;
    @ApiModelProperty(value = "建筑物名称")
    private String buildingName;
    @ApiModelProperty(value = "巡检点数量")
    private Integer pointNum;

    @ApiModelProperty(value = "关联属性")
    private List<InsBuildFloorVo> insBuildFloors = new ArrayList<>();

}

