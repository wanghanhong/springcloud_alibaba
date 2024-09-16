package com.smart.brd.manage.base.entity;

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
* 家畜采购商
*
* @author
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_livestock_purchase")
public class TLivestockPurchase implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 采购商编号
    */

    private Long purchaseCode;
    /**
    * 采购商名称
    */

    private String purchaseName;
    /**
    * 部门ID
    */

    private Long deptId;
    /**
    * 统一身份认证编号
    */

    private String creditCode;
    /**
    * 家畜种类
    */

    private Integer purchaseType;
    /**
    * 地址
    */

    private String purchaseAddress;
    /**
    * 联系电话
    */

    private String purchasePhone;
    /**
    * 联系人
    */

    private String purchaseContact;
    /**
    * 电子邮箱
    */

    private String purchaseEmail;
    /**
    * 建立时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


    @TableField(exist = false)
    public String deptIds;
}
