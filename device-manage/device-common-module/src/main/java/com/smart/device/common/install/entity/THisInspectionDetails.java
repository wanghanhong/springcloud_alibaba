package com.smart.device.common.install.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_his_inspection_details")
@ApiModel(value = "THisInspectionDetails对象", description = "")
public class THisInspectionDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "巡检计划ID")
    private Long inspectionId;
    @ApiModelProperty(value = "关联的ID")
    private Long insBuildId;
    @ApiModelProperty(value = "关联的ID")
    private Long insBuildFloorId;
    @ApiModelProperty(value = "单位ID")
    private Long companyId;
    @ApiModelProperty(value = "单位名称")
    private String companyName;
    @ApiModelProperty(value = "建筑物编号")
    private Long buildingId;
    @ApiModelProperty(value = "建筑物名称")
    private String buildingName;
    @ApiModelProperty(value = "层号")
    private Integer floorNum;

    @ApiModelProperty(value = "设备id")
    private Long deviceId;
    @ApiModelProperty(value = "设备类型")
    private Long deviceType;
    private String deviceName;
    @ApiModelProperty(value = "设备编码")
    private Long deviceCode;
    @ApiModelProperty(value = "设备状态1.正常2.待修3.呆换4.丢失")
    private Integer deviceStatus;

    @ApiModelProperty(value = "消防通道是否阻塞 0 否 1 是 ")
    private Integer fireEngineAccess;
    @ApiModelProperty(value = "巡检问题描述")
    private String deviceQuestion;
    @ApiModelProperty(value = "消防栓巡检状态 设备状态1.正常2.待修3.呆换4.丢失")
    private Integer fireEngineStatus;
    @ApiModelProperty(value = "消防栓内容")
    private String materialName;
    @ApiModelProperty(value = "消防栓安装位置")
    private String location;
    @ApiModelProperty(value = "巡检人id")
    private Long firefighterId;
    @ApiModelProperty(value = "巡检人姓名")
    private String firefighterName;

    @ApiModelProperty(value = "巡检人电话")
    private String fireFighterPhone;
    @ApiModelProperty(value = "消防栓id")
    private Long fireId;
    @ApiModelProperty(value = "总巡检点")
    private String point;

    @ApiModelProperty(value = "提交时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;


}
