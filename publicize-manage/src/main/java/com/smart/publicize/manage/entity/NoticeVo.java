package com.smart.publicize.manage.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

/**
 * USER: gll
 * DATE: 2020/5/25
 * TIME: 22:46
 * Describe:
 */
@Data
@TableName(value = "t_publicize_notice")
@ApiModel(value = "notice对象", description = "用户对象notice")
public class NoticeVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id")
    @Min(1)
    @ApiModelProperty(value = "公告id", name = "id")
    private Long id;
    @ApiModelProperty(value = "公告标题", name = "noticeName")
    private String noticeName;
    @ApiModelProperty(value = "公告类型0 弹窗 1 普通", name = "noticeType")

    private String noticeType;
    @ApiModelProperty(value = "弹窗开始时间", name = "popStartTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date popStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "弹窗结束时间", name = "popEndTime")

    private Date popEndTime;
    @ApiModelProperty(value = "公告发布类型", name = "releaseType")
    private Integer releaseType;
    @ApiModelProperty(value = "发布时间", name = "releaseTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date releaseTime;
    @ApiModelProperty(value = "公告内容", name = "noticeContent")
    private String noticeContent;
    @ApiModelProperty(value = "创建人姓名", name = "createName")
    private String createName;
    @ApiModelProperty(value = "更新人姓名", name = "updateName")
    private String updateName;
    @ApiModelProperty(value = "审核状态0待审核 1审核中 2已审核 3审核失败", name = "examineStatus")
    private Integer examineStatus;
    @ApiModelProperty(value = "创建时间", name = "createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty(value = "更新时间", name = "updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
