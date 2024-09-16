package com.smart.device.common.install.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 巡检记录
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_his_inspection")
@ApiModel(value = "THisInspection对象", description = "巡检记录")
public class THisInspection implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "单位ID")
    private Long companyId;

    @ApiModelProperty(value = " 单位名称")
    private String companyName;

    @ApiModelProperty(value = "巡检人ID")
    private Long firefighterId;

    @ApiModelProperty(value = "巡检人")
    private String firefighterName;

    @ApiModelProperty(value = "巡检点")
    private String point;

    @ApiModelProperty(value = "巡检点")
    private String pointRepair;

    @ApiModelProperty(value = "巡检点")
    private String pointReplace;

    @ApiModelProperty(value = "巡检点")
    private String pointLose;

    @ApiModelProperty(value = "巡检计划id")
    private  Long inspectionId;
     @Transient
    private  Long phone;

    @Transient
     private String name;
    @ApiModelProperty(value = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;


}
