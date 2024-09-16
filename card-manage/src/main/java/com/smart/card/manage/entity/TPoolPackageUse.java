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
* 总套餐使用量
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_pool_package_use")
public class TPoolPackageUse implements Serializable {

    private static final long serialVersionUID = -134546795400024L;
    /**
    * ID
    */
    @TableId(value = "package_use_id", type = IdType.INPUT)
    private Long packageUseId;
    /**
     * 流量池号码
     */
    private Long poolNbr;
    /**
    * 当月可用量
    */

    private String balanceAmount;
    /**
    * 当月使用量
    */

    private String balanceUsed;
    /**
    * 当月剩余量
    */

    private String balanceAvailable;
    /**
    * 账期
    */

    private String date;
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
