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
* 操作历史订单
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_card_history")
public class TCardHistory implements Serializable {

    private static final long serialVersionUID = 134546795400005L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 订单编号
    */

    private Long orderNumber;
    /**
    * 订单状态代码
    */

    private Integer status;
    /**
    * 订单状态
    */

    private String statusDic;
    /**
    * 订单结果
    */

    private String result;
    /**
    * 订单来源
    */
    @Dict(value = "dict_type_218")
    private String orderType;
    /**
    * 订单类型
    */
    @Dict(value = "dict_type_216")
    private Integer type;

    /**
    * 结束时间
    */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    /**
     * 开始时间
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
    * 客户ID
    */

    private String custId;
    /**
    * 操作员ID
    */

    private Long creator;
    /**
    * 描述
    */

    private String workstatus;
    /**
    * 部门ID
    */

    private Long deptId;
    /**
    * 删除标记
    */

    private Integer deleteFlag;


    @TableField(exist = false)
    public String deptIds;

    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    public String startTime;
    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    public String endTime;

}
