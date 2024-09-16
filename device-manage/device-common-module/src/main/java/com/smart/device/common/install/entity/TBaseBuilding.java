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
import java.util.ArrayList;
import java.util.List;

/**
 * 设备-建筑物表
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_base_building")
@ApiModel(value = "建筑物表")
public class TBaseBuilding implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "建筑物编码")
    private String buildingCode;

    @ApiModelProperty(value = "建筑物名称")
    private String buildingName;

    @ApiModelProperty(value = "建筑物类型 1民用2厂房3仓库")
    private Integer type;
    @ApiModelProperty(value = "建筑物类型名称")
    private String typeName;

    @ApiModelProperty(value = "单位ID")
    private Long companyId;

    @ApiModelProperty(value = "单位名称")
    private String companyName;

    @ApiModelProperty(value = "建筑物高度类型1超高层2高层3多层")
    private Integer heightType;

    @ApiModelProperty(value = "最高层")
    private Integer floorMax;

    @ApiModelProperty(value = "最底层")
    private Integer floorMin;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "县")
    private String county;

    @ApiModelProperty(value = "乡村")
    private String town;

    @ApiModelProperty(value = "建筑物地址")
    private String address;

    @ApiModelProperty(value = "负责人")
    private String buildingMan;

    @ApiModelProperty(value = "建筑物单元数")
    private String unitNum;

    @ApiModelProperty(value = "楼层数")
    private Integer floorCount;

    @ApiModelProperty(value = "占地面积")
    private String coverArea;

    @ApiModelProperty(value = "建筑面积")
    private String buildingArea;

    @ApiModelProperty(value = "危险等级")
    private Long dangerLevel;

    @ApiModelProperty(value = "耐火等级")
    private Long fireratingLevel;

    @ApiModelProperty(value = "结构类型")
    private Long structure;

    @ApiModelProperty(value = "建筑高度")
    private Double height;

    @ApiModelProperty(value = "日常工作人数")
    private Integer workNum;

    @ApiModelProperty(value = "使用性质")
    private Integer useNature;

    @ApiModelProperty(value = "建筑时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime buildingTime;

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

    // 显示区域时候用到
    @TableField(exist = false)
    private String areaName;
    // 关联查询用到
    @TableField(exist = false)
    private List<TBaseBuildingSon> buildingSons = new ArrayList<>();

}
