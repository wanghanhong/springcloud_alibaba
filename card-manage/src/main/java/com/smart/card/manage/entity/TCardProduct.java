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
* 卡主产品
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_card_product")
public class TCardProduct implements Serializable {

    private static final long serialVersionUID = 134546795400010L;

    @TableId(value = "card_product_id", type = IdType.INPUT)
    private Long id;
    /**
     * 产品ID
     */
    private Long cardProductId;
    /**
    * 卡ID
    */

    private Long iccid;
    private Long msisdn;
    /**
    * 产品编码
    */

    private String code;
    /**
    * 主产品名称
    */

    private String name;
    /**
    * 提箱阈值
    */

    private String notifyRate;
    /**
    *运营商定义状态
    */

    private Integer status;
    /**
    * 生效时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime effectiveDate;

    private Long prodInstId;

    private Integer custCode;

    private String custName;
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

    private String deployInfo;

    private String deployInfoType;

    private String directedGroupsFlag;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiredDate;

    private Integer groupNum;

    private String modifyFlag;

    private String type;

    private String unsubscribeFlag;

    private String verified;


    @TableField(exist = false)
    public String deptIds;
}
