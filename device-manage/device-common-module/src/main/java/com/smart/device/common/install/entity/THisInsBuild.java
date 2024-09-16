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
import java.util.ArrayList;
import java.util.List;

/**
 * 巡检详情
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_his_ins_build")
@ApiModel(value = "TBaseInspectionSon对象", description = "巡检详情")
public class THisInsBuild implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "建筑物ID")
    private Long buildingId;

    @ApiModelProperty(value = "建筑物名称")
    private String buildingName;

    @ApiModelProperty(value = "巡检计划ID")
    private Long inspectionId;
    @ApiModelProperty(value = "层数")
    private Integer floorCount;
    @ApiModelProperty(value = "地上层数")
    private Integer floorMax;
    @ApiModelProperty(value = "地下层数")
    private Integer floorMin;

    @ApiModelProperty(value = "巡检点")
    private Integer buildPointNum;

    @ApiModelProperty(value = "是否巡检0 未巡检 1巡检")
    private Integer status;

    @ApiModelProperty(value = " 备注")
    private String content;

    @ApiModelProperty(value = "新增时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "状态更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;

    // 关联查询用到
    @TableField(exist = false)
    private List<THisInspectionDetails> insBuildFloors = new ArrayList<>();

    @ApiModelProperty(value = "单位ID")
    @TableField(exist = false)
    private Long companyId;
}
