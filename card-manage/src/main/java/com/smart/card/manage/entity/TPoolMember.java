package com.smart.card.manage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.smart.card.common.dict.dict.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 流量池成员
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_pool_member")
public class TPoolMember implements Serializable {

    private static final long serialVersionUID = 134546795400021L;;

    @TableId(value = "pool_member_id", type = IdType.INPUT)
    private Long poolMemberId;
    /**
    * 流量池号码
    */

    private Long poolNbr;
    /**
    * 成员号码
    */

    private Long subsId;
    /**
    * 流量限额
    */

    private Integer limitValue;
    /**
    * 限额类型
    */

    private Integer limitType;
    /**
    * 限额名称
    */

    private String limitName;
    /**
    * 开卡地
    */

    private String areaName;
    /**
    * SIM卡状态
    */
    @Dict(value = "dict_type_227")
    private Integer status;
    /**
    * SIM卡状态
    */

    private String statusDic;
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
    /**
    * 激活时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activeTime;

    private String balanceAccount;

    private String billingNbr;

    private String ratableAmount;

    private String ratableTotal;


    @TableField(exist = false)
    public String deptIds;
}
