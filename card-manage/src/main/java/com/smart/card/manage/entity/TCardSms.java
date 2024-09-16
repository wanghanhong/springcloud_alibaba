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
* 短信详单
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_card_sms")
public class TCardSms implements Serializable {

    private static final long serialVersionUID = 134546795400013L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * ICCID
     */
    private Long iccid;

    /**
    * 接入号
    */

    private Long msisdn;
    /**
    * 目标接入号
    */

    private Long accNbr;
    /**
    * 发送时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;
    /**
    * 短信类别 1-主叫  2-被叫
    */

    private Integer ticketTypeId;
    /**
    * 短信类型
    */

    private String ticketType;
    /**
    * 合计话费
    */

    private String chargeCntCh;
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
