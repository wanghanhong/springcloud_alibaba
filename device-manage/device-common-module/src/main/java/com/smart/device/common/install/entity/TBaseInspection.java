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
 * 巡检计划
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_base_inspection")
@ApiModel(value = "TBaseInspection对象", description = "巡检计划")
public class TBaseInspection implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "单位ID")
    private Long companyId;

    @ApiModelProperty(value = "单位名称")
    private String companyName;

    @ApiModelProperty(value = "巡检周期")
    private Integer period;

    @ApiModelProperty(value = "巡检要求")
    private String claim;

    @ApiModelProperty(value = "巡检点")
    private Integer pointNum;

    @ApiModelProperty(value = "巡检人ID")
    private Long firefighterId;

    @ApiModelProperty(value = "巡检人名称")
    private String firefighterName;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "是否巡检0 未巡检 1巡检完成")
    private Integer status;

    @ApiModelProperty(value = " 备注")
    private String content;

    @ApiModelProperty(value = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;

    @ApiModelProperty(value = "当前建筑物ID")
    private Long curBuildingId;
    @ApiModelProperty(value = "保存巡检的顺序1 从下到上，2从上到下")
    private Integer sortNo;
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime insTime;

    @ApiModelProperty(value = " 0 默认是计划(计划查询不需要此字段)，1的话就是巡检记录")
    private Integer type;

    @ApiModelProperty(value = "待修点位数")
    private int pointRepair;
    @ApiModelProperty(value = "代换点位数")
    private int pointReplace;
    @ApiModelProperty(value = "丢失点位数")
    private int pointLose;
    @ApiModelProperty(value = "堵塞数量")
    private int accessNum;

    @TableField(exist = false)
    private  Long inspectionId;
    @TableField(exist = false)
    private List<TBaseInsBuildFloor> insBuildFloors = new ArrayList<>();
    @TableField(exist = false)
    private List<TBaseInsBuild> insBuilds = new ArrayList<>();


}
