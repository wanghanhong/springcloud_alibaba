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
import java.util.List;

/**
 * 单位表
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_base_company")
@ApiModel(value = "TBaseCompany对象", description = "单位表")
public class TBaseCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "上级单位id")
    private Long parentId;
    @ApiModelProperty(value = " 单位名称")
    private String companyName;
    @ApiModelProperty(value = " 单位编码")
    private String companyCode;

    @ApiModelProperty(value = "机构地址")
    private String address;

    @ApiModelProperty(value = " 1 消防单位 2社会单位 3九小场所 4维保单位")
    private Integer type;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "拨打方式 1拨打全部 2顺序拨打 3 语音顺序发送短信一次性发送")
    private Integer phoneType;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "县")
    private String county;

    @ApiModelProperty(value = "乡村")
    private String town;

    @ApiModelProperty(value = "消防责任人")
    private String inchargeName;

    @ApiModelProperty(value = "消防责任人电话")
    private String inchargePhone;

    @ApiModelProperty(value = "消防管理人")
    private String managerName;

    @ApiModelProperty(value = "消防管理人电话")
    private String managerPhone;

    @ApiModelProperty(value = "兼职消防管理人")
    private String parttimeName;

    @ApiModelProperty(value = "兼职消防管理人电话")
    private String parttimePhone;

    @ApiModelProperty(value = "消防责任人身份证")
    private String inchargeCardid;

    @ApiModelProperty(value = "消防管理人身份证")
    private String managerCardid;

    @ApiModelProperty(value = "兼职消防管理人身份证")
    private String parttimeCardid;

    @ApiModelProperty(value = "监管等级")
    private Integer supervisionLevel;

    @ApiModelProperty(value = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;

    @ApiModelProperty(value = " 1 消防单位 2社会单位 3九小场所 4维保单位")
    private String typeName;

    @ApiModelProperty(value = "所属监控中心")
    private Long monitorCenterId;

    @ApiModelProperty(value = "所属监控中心")
    private String monitorCenterName;

    @ApiModelProperty(value = "联网状态 1 未联网 2联网")
    private Integer inlineState;

    @ApiModelProperty(value = "成立时间")
    private LocalDateTime establishTime;

    @ApiModelProperty(value = "建筑面积")
    private Double floorArea;

    @TableField(exist = false)
    private String areaName;

    @TableField(exist = false)
    private List<TBaseFirefighter> personList;
    @TableField(exist = false)
    private String phone;

}
