package com.smart.card.manage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
@TableName("t_pool_product")
public class TPoolProduct implements Serializable {

    private static final long serialVersionUID = 134546795400025L;

    @TableId(value = "pool_product_id", type = IdType.INPUT)
    private Long prodId;
    /**
     * 流量池号码
     */
    private Long poolNbr;
    /**
    * 流量池号码
    */
    private Long subsId;
    /**
    * 产品ID
    */

    private Long prodInstId;
    /**
    * 产品名称
    */

    private String prodName;
    /**
    * 产品编号
    */

    private String prodCode;
    /**
    * 产品编号
    */

    private String prodType;
    /**
    * 生效时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activeTime;
    /**
    * 产品描述
    */

    private String prodStatusName;
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

    private Long extProdInstId;

    private Long ownerCustId;

    private String modifyFlag;

    private String paymentType;

    private String status;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statusDate;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private String accProdInstId;

    private String stopType;

    private Integer useCustId;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginDate;

    private String restrictedZone;

    @TableField(exist = false)
    public String deptIds;
}
