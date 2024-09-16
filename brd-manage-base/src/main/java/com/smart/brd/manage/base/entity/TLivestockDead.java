package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.smart.brd.manage.base.common.dict.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 家畜死亡
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_livestock_dead")
public class TLivestockDead implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 家畜ID
    */

    private Long livestockId;
    private String deviceCode;
    @Dict
    private Integer dictId;
    /**
     * 畜病
     */
    private String dictName;
    /**
    * 死亡日期
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate deadDate;
    /**
    * 死亡原因
    */

    private String deadInfo;
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

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @TableField(exist = false)
    public String startTime;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @TableField(exist = false)
    public String endTime;
}
