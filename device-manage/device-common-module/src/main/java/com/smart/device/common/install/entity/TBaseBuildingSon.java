package com.smart.device.common.install.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 建筑物-设备合计
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_base_building_son")
@ApiModel(value = "建筑物-设备合计")
public class TBaseBuildingSon implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @ApiModelProperty(value = "建筑物编码")
    private Long buildingId;

    @ApiModelProperty(value = "建筑物名称")
    private String buildingName;

    @ApiModelProperty(value = "0地下，1地上")
    private Integer type;

    @ApiModelProperty(value = "层号")
    private Integer floorNum;

    @ApiModelProperty(value = "消防通道数量")
    private Integer escapeRouteNum;
    @ApiModelProperty(value = "消防栓数量")
    private Integer firehydrantNum;
    @ApiModelProperty(value = "设备数量")
    private Integer fireAllNum;

    @ApiModelProperty(value = "烟感设备数量")
    private Integer smokeNum;
    @ApiModelProperty(value = "电气设备数量")
    private Integer electricNum;
    @ApiModelProperty(value = "水压设备数量")
    private Integer waterpressNum;

    @ApiModelProperty(value = "图纸")
    private String picUrl;

    @ApiModelProperty(value = " 备注")
    private String content;

    @ApiModelProperty(value = "新增时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "状态更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;

    @TableField(exist = false)
    private TBaseBuilding parentId;		// 所属ID 父类


}
