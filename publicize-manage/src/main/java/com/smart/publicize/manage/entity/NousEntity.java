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
 * TIME: 16:52
 * Describe:
 * @author l
 */
@Data
@TableName(value = "t_publicize_nous")
@ApiModel(value = "nous对象", description = "消防常识对象nous")
public class NousEntity  implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id")
    @Min(1)
    @NotNull(message = "id为空")
    @ApiModelProperty(value = "消防常识id", name = "id")
    private Long id;
    @NotBlank(message ="消防常识名称不能为空")
    @ApiModelProperty(value = "消防常识名称", name = "nousName")
    private String nousName;
//    @NotBlank(message ="消防常识内容不能为空")
    @ApiModelProperty(value = "消防常识内容", name = "nousContent")
    private String nousContent;

    @ApiModelProperty(value = "创建人id", name = "status")
    private Integer status;

    @ApiModelProperty(value = "创建人id", name = "createId")
    private Long createId;
    @ApiModelProperty(value = "创建人姓名", name = "createName")
    private String createName;
    @ApiModelProperty(value = "修改人id", name = "updateId")
    private Long updateId;
    @ApiModelProperty(value = "修改人姓名", name = "updateName")
    private String updateName;
    @ApiModelProperty(value = "文件路径", name = "fileUrl")
    private String fileUrl;
    @ApiModelProperty(value = "审核时间", name = "examineTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date examineTime;
    @ApiModelProperty(value = "创建时间", name = "createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty(value = "更新时间", name = "updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @NotNull(message = "审核状态不能为空")
    @ApiModelProperty(value = "审核状态0待审核 1审核中 2已审核 3审核失败", name = "examineStatus")
    private Integer examineStatus;
}
