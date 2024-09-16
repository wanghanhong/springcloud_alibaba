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
* 字典详细
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_base_dict")
public class TBaseDict implements Serializable {

    private static final long serialVersionUID = -1714476694755134924L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 字典id
    */

    private String dictId;
    /**
    * 字典类型id
    */

    private String dictTypeId;
    /**
    * 字典名称
    */

    private String dictName;
    /**
    * 自定义ID，用来计数
    */

    private Integer uniqueId;
    /**
    * 排序
    */

    private Integer sortno;
    /**
    * 创建时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
    * 删除标识(0 正常 1删除)
    */

    private Integer deleteFlag;
    /**
    * 上级ID
    */

    private String parentId;


    @TableField(exist = false)
    public String deptIds;
}
