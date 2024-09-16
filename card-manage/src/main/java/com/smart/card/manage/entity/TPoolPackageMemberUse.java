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
* 成员套餐使用量
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_pool_package_member_use")
public class TPoolPackageMemberUse implements Serializable {

    private static final long serialVersionUID = -134546795400023L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
     * 流量池号码
     */
    private Long poolNbr;
    /**
    * 成员号码
    */
    private Long subsId;
    /**
    * 加入时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activeTime;

    private Long billingNbr;
    /**
    * SIM卡状态
    */

    private Integer status;
    /**
    * SIM卡状态
    */
    private String statusDic;
    // 剩余额度
    private String balanceAccount;
    // 已使用量
    private String ratableAmount;
    //  成员分配额度
    private String ratableTotal;
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
     * 账期
     */
    private String date;

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
