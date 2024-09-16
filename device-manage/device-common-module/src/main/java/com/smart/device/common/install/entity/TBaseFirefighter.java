package com.smart.device.common.install.entity;

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
 * 维保单位
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_base_firefighter")
@ApiModel(value = "TBaseFirefighter对象", description = "维保单位")
public class TBaseFirefighter implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "1 报装 2 安装 3 巡检 4  维修  ")
    private Integer type;

    @ApiModelProperty(value = "联系人")
    private String name;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "身份证")
    private String cardId;

    @ApiModelProperty(value = "单位ID")
    private Long companyId;

    @ApiModelProperty(value = "单位名称")
    private String companyName;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "培训时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime trainTime;

    @ApiModelProperty(value = "是否消防培训")
    private Integer isTrain;

    @ApiModelProperty(value = "证书内容")
    private String certificateContent;

    @ApiModelProperty(value = "证书编号")
    private String certificateNo;

    @ApiModelProperty(value = "证书时间")
    private String certificateTime;

    @ApiModelProperty(value = "是否为疏散引导员")
    private Integer isPointsman;

    @ApiModelProperty(value = "新增时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "状态更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;

    public TBaseFirefighter() {
    }

    public TBaseFirefighter(String phone) {
        this.phone = phone;
    }
    public TBaseFirefighter(Integer type, String name, String cardId, String phone){
        this.type = type;
        this.name = name;
        this.cardId = cardId;
        this.phone = phone;
    }
    public TBaseFirefighter(Long id,Integer type, String name, String cardId, String phone){
        this.id = id;
        this.type = type;
        this.name = name;
        this.cardId = cardId;
        this.phone = phone;
    }
}
