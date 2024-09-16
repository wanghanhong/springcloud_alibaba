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
@TableName("t_pool_package")
public class TPoolPackage implements Serializable {

    private static final long serialVersionUID = 134546795400022L;

    @TableId(value = "pool_package_id", type = IdType.INPUT)
    private Long poolPackageId;
    /**
     * 流量池号码
     */
    private Long poolNbr;
    private Long subsId;
    /**
    * 产品ID
    */

    private Integer packageId;
    /**
    * 产品名称
    */

    private Integer packageName;
    /**
    * 套餐编号
    */

    private String packageCode;
    /**
    * 套餐编号
    */

    private String packageuctNbr;
    /**
    * 生效时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activeTime;
    /**
     * 失效时间
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireTime;
    /**
    * 套餐描述
    */

    private String packageStatusName;

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
