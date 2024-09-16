package com.smart.card.manage.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 套餐使用量
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_card_use_count")
public class TCardUseCount implements Serializable {

    private static final long serialVersionUID = 134546795400016L;
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
    * SIM卡状态
    */

    private Integer simStatus;
    /**
    * 账期
    */

    private String billDate;
    /**
    * 流量使用量
    */

    private BigDecimal dataUsage;
    /**
    * 语音用量
    */

    private Integer voiceUsage;
    /**
    * 短信用量
    */

    private Integer smsUsage;
    /**
    * NB消息用量
    */

    private Integer nbUsage;
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
    * 删除标记(0-未删除  1-已删除)
    */

    private Integer deleteFlag;


    @TableField(exist = false)
    public String deptIds;
}
