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
* 群组
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_base_group")
public class TBaseGroup implements Serializable {

    private static final long serialVersionUID = 134546795400001L;

    @TableId(value = "dept_id", type = IdType.INPUT)
    private Long deptId;
    /**
    * 名称
    */

    private String deptName;
    /**
    * 默认群组
    */

    private Integer status;
    /**
    * 描述
    */

    private String remark;
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
