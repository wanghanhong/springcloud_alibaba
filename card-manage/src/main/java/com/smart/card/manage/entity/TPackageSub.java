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
* 附属套餐
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_package_sub")
public class TPackageSub implements Serializable {

    private static final long serialVersionUID = 134546795400019L;;

    @TableId(value = "package_sub_id", type = IdType.INPUT)
    private Long packageSubId;
    /**
    * 主ID
    */

    private Long packageId;
    /**
    * 套餐编号
    */

    private Integer packageNo;
    /**
    * 套餐名称
    */

    private String packageName;
    /**
    * 
类型
    */

    private Integer type;
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
