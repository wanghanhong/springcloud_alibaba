package com.smart.card.manage.entity;

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
* 卡功能产品
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_card_product_sub")
public class TCardProductSub implements Serializable {

    private static final long serialVersionUID = 134546795400012L;

    @TableId(value = "card_product_sub_id", type = IdType.INPUT)
    private Long id;
    private Long iccid;
    private Long msisdn;
    /**
     * 功能产品ID
     */
    private Long cardProductSubId;
    /**
    * 主ID
    */

    private Long cardProductId;
    /**
    * 功能产品名称
    */

    private String name;
    /**
    * 配置信息
    */

    private String deployInfo;
    /**
    * 生效日期
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime effectiveDate;
    /**
    * 是否能修改 0-能 1-不能
    */

    private Integer modifyFlag;
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

    private String code;

    private String custCode;

    private String custName;

    private String deployInfoType;

    private String directedGroupsFlag;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiredDate;

    private Integer groupNum;

    private String notifyRate;

    private Long prodInstId;

    private Integer status;

    private String type;

    private String unsubscribeFlag;

    private String verified;


    @TableField(exist = false)
    public String deptIds;
}
