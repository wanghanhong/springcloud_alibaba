package com.smart.brd.manage.base.entity;

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
* 转入转出记录
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shed_transfer")
public class TShedTransfer implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 家畜ID
    */
    private Long livestockId;

    /**
    * 转出栏ID
    */
    private Long sourceId;

    /**
     * 转入舍ID
     */
    private Long shedId;

    /**
    * 转入栏ID
    */
    private Long bedId;

    /**
    * 转移时间
    */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transferTime;

    /**
     * 转出原因
     */
    private Integer transferReason;

    /**
    * 部门ID
    */
    private Long deptId;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 删除标记
     */
    private Integer deleteFlag;

    @TableField(exist = false)
    public String deptIds;
}
