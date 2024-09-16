package com.smart.card.manage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 语音详单
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_card_voice")
public class TCardVoice implements Serializable {

    private static final long serialVersionUID = 134546795400017L;;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 接入号
    */

    private Long msisdn;
    /**
    * 目标接入号
    */

    private Long accNbr;
    /**
    * 语音类型
    */

    private String ticketType;
    /**
    * 开始时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;
    /**
    * 通话时长
    */

    private String duration;

    private String durationCh;
    /**
    * 费用
    */

    private String ticketCharge;
    /**
    * 通话地点
    */

    private String areaCode;
    /**
    * 产品名称
    */

    private String productName;
    /**
    * 部门ID
    */

    private Long deptId;
    /**
    * 创建时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
    * 删除标记
    */

    private Integer deleteFlag;


    @TableField(exist = false)
    public String deptIds;
    @TableField(exist = false)
    private String startTime;
    @TableField(exist = false)
    private String endTime;
}
