package com.smart.device.install.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart.device.common.install.entity.THisInspectionDetails;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "巡检记录接收对象")
public class HisInspectionVo implements Serializable {

    @ApiModelProperty(value = "保存巡检的顺序1 从下到上，2从上到下")
    private Integer sortNo;

    @ApiModelProperty(value = "巡检计划ID")
    private Long inspectionId;
    @ApiModelProperty(value = "关联的ID")
    private Long insBuildId;
    @ApiModelProperty(value = "巡检计划层号ID")
    private Long insBuildFloorId;

    @ApiModelProperty(value = "单位ID")
    private Long companyId;
    @ApiModelProperty(value = "单位名称")
    private String companyName;
    @ApiModelProperty(value = "建筑物ID")
    private Long buildingId;
    @ApiModelProperty(value = "建筑物名称")
    private String buildingName;
    @ApiModelProperty(value = "巡检人ID")
    private Long firefighterId;
    @ApiModelProperty(value = "巡检人名称")
    private String firefighterName;
    @ApiModelProperty(value = "联系电话")
    private String firefighterphone;
    @ApiModelProperty(value = "巡检点")
    private Integer pointNum;
    @ApiModelProperty(value = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "消防通道是否阻塞 1 是 0 否")
    private Integer fireEngineAccess;

    private List<THisInspectionDetails> details = new ArrayList<>();

    private List<THisInspectionDetails> repairList = new ArrayList<>();
    private List<THisInspectionDetails> replaceList = new ArrayList<>();
    private List<THisInspectionDetails> loseList = new ArrayList<>();

    private List<THisInspectionDetails> accessList = new ArrayList<>();
}
