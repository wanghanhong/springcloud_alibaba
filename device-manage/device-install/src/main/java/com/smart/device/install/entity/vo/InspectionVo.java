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
@ApiModel(value = "巡检计划接收对象")
public class InspectionVo implements Serializable {

    @ApiModelProperty(value = "巡检计划ID")
    private Long inspectionId;
    @ApiModelProperty(value = "关联属性")
    private List<InsBuildingVo> insBuilds = new ArrayList<>();

}
