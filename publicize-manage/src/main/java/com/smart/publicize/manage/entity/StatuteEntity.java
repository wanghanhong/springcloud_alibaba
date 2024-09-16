package com.smart.publicize.manage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * USER: gll
 * DATE: 2020/5/22
 * TIME: 16:54
 * Describe:
 */
@Data
@TableName(value = "t_publicize_statute")
@ApiModel(value = "Statute对象", description = "法规对象Statute")
public class StatuteEntity  implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id")
    @Min(1)
    @NotNull(message = "法规id为空")
    @ApiModelProperty(value = "法规id", name = "id")
    private Long id;
    @ApiModelProperty(value = "法规名称", name = "statuteName")
    @NotBlank(message = "法规名称不能为空")
    private String statuteName;
    @NotNull(message = "审核状态为空")
    @ApiModelProperty(value = "审核状态0待审核 1审核中 2已审核 3审核失败", name = "examineStatus")
    private Integer examineStatus;
    @NotNull(message = "法规类别不能为空")
    @ApiModelProperty(value = "法规类别 0 法律 2宪法", name = "statuteType")
    private Integer statuteType;

    @ApiModelProperty(value = "颁布机关编码 0 ", name = "issuingAuthority")
    private String issuingAuthority;

    @ApiModelProperty(value = "批准机关编码 0 ", name = "approvalAuthority")
    private String approvalAuthority;
    @ApiModelProperty(value = "颁布文号 0 ", name = "issueNo")
    private String issueNo;
    @ApiModelProperty(value = "颁布日期 ", name = "issueDatetime")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String issueDatetime;
//    @NotBlank(message = "法规内容不能为空")
    @ApiModelProperty(value = "法规内容 ", name = "statuteContent")
    private String statuteContent;
    @ApiModelProperty(value = "法规链接", name = "statueUrl")
    private String statueUrl;
    @ApiModelProperty(value = "创建人id", name = "createId")
    private Long createId;
    @ApiModelProperty(value = "创建人姓名", name = "createName")
    private String createName;
    @ApiModelProperty(value = "更新人id", name = "updateId")
    private Long updateId;
    @ApiModelProperty(value = "更新人姓名", name = "updateName")
    private String updateName;
    @ApiModelProperty(value = "状态 0 正常 1删除", name = "status")
    private Integer status;
    @ApiModelProperty(value = "审核时间", name = "examineTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date examineTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间", name = "statueUrl")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间", name = "statueUrl")
    private Date updateTime;
}
