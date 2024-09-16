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
 * 设备-消防栓表
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_base_firehydrant")
@ApiModel(value = "TBaseFirehydrant对象", description = "设备-消防栓表")
public class TBaseFirehydrant implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    @ApiModelProperty(value = "单位ID")
    private Long companyId;

    @ApiModelProperty(value = "单位名称")
    private String companyName;

    @ApiModelProperty(value = "建筑物编号")
    private Long buildingId;

    @ApiModelProperty(value = "建筑物")
    private String buildingName;

    @ApiModelProperty(value = "楼层")
    private Integer buildingFloor;

    @ApiModelProperty(value = "安装位置")
    private String location;

    @ApiModelProperty(value = "消防栓类型")
    private Integer type;
    @ApiModelProperty(value = "消防栓类型名称")
    private String typeName;

    @ApiModelProperty(value = "是否安装压力表")
    private Integer isWaterpress;

    @ApiModelProperty(value = "是否安装液位计")
    private Integer isLiquidlevel;

    @ApiModelProperty(value = "压力表ID")
    private Long waterpressId;
    @ApiModelProperty(value = "压力表名称")
    private String waterpressName;

    @ApiModelProperty(value = "液位计ID")
    private Long liquidlevelId;
    @ApiModelProperty(value = "液位计名称")
    private String liquidlevelName;


    @ApiModelProperty(value = "消防材料ID")
    private String materialId;

    @ApiModelProperty(value = "消防材料内容")
    private String materialName;

    @ApiModelProperty(value = "安装图")
    private String fileUrl;

    @ApiModelProperty(value = " 备注")
    private String content;
    @ApiModelProperty(value = " 状态")
    private Integer deviceStatus;

    @ApiModelProperty(value = "新增时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "状态更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;


    @ApiModelProperty(value = "巡检码")
    private Integer isCode;
    @ApiModelProperty(value = "巡检码地址")
    private String codeUrl;

    @TableField(exist = false)
    private List<TBaseDict> materialList = new ArrayList<>();
    @TableField(exist = false)
    private String codeUrlString;
    @TableField(exist = false)
    private String fileUrlString;
    @TableField(exist = false)
    private Long oldbuildingId;
    @TableField(exist = false)
    private Integer oldbuildingFloor;

    // 操作人
    private Long opUserId;

}
