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
* 卡账单表
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_card_bill")
public class TCardBill implements Serializable {

    private static final long serialVersionUID = 134546795400003L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 接入号
    */
    private Long iccid;
    private Long msisdn;

    private Long accountId;
    private BigDecimal amount;
    private Integer cycle;
    private Integer type;
    private String accountName;

    /**
    * 账期
    */
    private String billingCycleID;
    /**
    * 业务类型(1-业务级  2-账户级)
    */

    private String queryFlag;
    /**
    * 账目类型
    */

    private Long acctItemType;
    private String acctItemTypeName;
    /**
    * 账户名称
    */

    private String acctName;
    /**
    * 欠费金额
    */

    private String acctCharge;
    /**
    * 部门id
    */

    private Long deptId;
    /**
    * 新增时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
    * 删除标识(0 正常 1删除)
    */

    private Integer deleteFlag;


    @TableField(exist = false)
    public String deptIds;
}
