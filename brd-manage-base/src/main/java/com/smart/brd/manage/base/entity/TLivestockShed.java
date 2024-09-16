package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
* 舍表
*
* @author
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_livestock_shed")
public class TLivestockShed implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 舍ID
    */

     @TableId(value = "shed_id", type = IdType.INPUT)
    private Long shedId;
    /**
    * 养殖场
    */

    private Long fieldId;
    private Integer type;
    /**
    * 舍名称
    */

    private String shedName;
    /**
    * 新增时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 部门id
     */

    private Long deptId;

    @TableField(exist = false)
    public String deptIds;
    private Integer deleteFlag;
}
