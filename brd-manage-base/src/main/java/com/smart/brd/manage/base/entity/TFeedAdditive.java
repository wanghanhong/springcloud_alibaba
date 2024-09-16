package com.smart.brd.manage.base.entity;

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
* 饲料添加剂表
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_feed_additive")
public class TFeedAdditive implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 供应商ID
    */

    private Long supplierId;
    /**
    * 供应商名称
    */

    private String supplierName;
    /**
    * 编码（一码一商）
    */

    private String productCode;
    /**
    * 产品名称
    */

    private String productName;
    /**
    * 注册商标
    */

    private String trademark;
    /**
    * 种类
    */

    private String type;
    /**
    * 规格或型号
    */

    private String model;
    /**
    * 单价
    */

    private BigDecimal unitPrice;
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
    * 部门id
    */

    private Long deptId;


    @TableField(exist = false)
    public String deptIds;
}
