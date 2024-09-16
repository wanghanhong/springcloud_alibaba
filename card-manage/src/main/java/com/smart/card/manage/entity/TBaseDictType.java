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
* 字典类别
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_base_dict_type")
public class TBaseDictType implements Serializable {

    private static final long serialVersionUID = -1714476694755134924L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 字典ID
    */

    private String dictTypeId;
    /**
    * 字典名称
    */

    private String dictTypeName;
    /**
    * 上级ID
    */

    private String parentId;
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
    * 是否可以删除
    */

    private Integer isCan;
    /**
    * 删除标识(0 正常 1删除)
    */

    private Integer deleteFlag;


    @TableField(exist = false)
    public String deptIds;
}
