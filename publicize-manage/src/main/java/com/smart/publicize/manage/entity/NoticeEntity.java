package com.smart.publicize.manage.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * USER: gll
 * DATE: 2020/5/22
 * TIME: 16:50
 * Describe:公告实体类
 *
 * @author l
 */
@Data
@TableName(value = "t_publicize_notice")
@ApiModel(value = "notice对象", description = "公告对象notice")
public class NoticeEntity  implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id")
    @Min(1)
    @NotNull(message = "id为空")
    @ApiModelProperty(value = "公告id", name = "id")
    private Long id;
    @ApiModelProperty(value = "公告标题", name = "noticeName")
    @NotBlank(message = "公告标题不能为空")
    private String noticeName;
    @NotNull(message = "请选择公告类型")
    @ApiModelProperty(value = "公告类型0 弹窗 1 普通", name = "noticeType" )

    private Integer noticeType;
    @ApiModelProperty(value = "弹窗开始时间", name = "popStartTime" )

    private String popStartTime;
    @ApiModelProperty(value = "弹窗结束时间", name = "popEndTime" )

    private String popEndTime;
    @NotNull(message = "请选择发布类型")
    @ApiModelProperty(value = "公告发布类型 立即发布 1 定时发布0", name = "releaseType")
    private Integer releaseType;
    @ApiModelProperty(value = "发布时间", name = "releaseTime")
    private String releaseTime;
    @ApiModelProperty(value = "公告内容", name = "noticeContent")
    @NotBlank(message = "公告标题不能为空")
    private String noticeContent;
    @Range(min = 0, max = 1)
    @ApiModelProperty(value = "状态0 未发布 1 发布", name = "status")

    private Integer publicStatus;
    @Range(min = 0, max =1)
    @ApiModelProperty(value = "删除状态0 正常 1 删除", name = "status")

    private Integer status;
    @ApiModelProperty(value = "发布人id", name = "createId")
    @Min(1)
    private Long createId;
    @ApiModelProperty(value = "发布人姓名", name = "createName")
    private String createName;
    @Min(1)
    @ApiModelProperty(value = "操作人id", name = "updateId")
    private Long updateId;
    @ApiModelProperty(value = "操作人姓名", name = "updateName")
    private String updateName;
    @NotNull(message = "审核状态为空")
    @ApiModelProperty(value = "审核状态0待审核 1审核中 2已审核 3审核失败", name = "examineStatus")
    private Integer examineStatus;
    @ApiModelProperty(value = "审核时间", name = "examineTime")
    private Date examineTime;
    @ApiModelProperty(value = "创建时间", name = "createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty(value = "更新时间", name = "updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    @ApiModelProperty(value = "角色id", name = "roleId(1,2,3)")
    @TableField(exist = false)
    private String roleId;
}
