package com.smart.card.common.dict.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* 设备类别字典
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_base_dict")
public class TBaseDict implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
    * 字典ID
    */
    private String dictId;
    /**
    * 字典名称
    */

    private String dictName;
    /**
     *字典类型id
     */

    private String dictTypeId;
    /**
     *上级id
     */
    private String parentId;
    private Integer uniqueId;
    /**
    * 排序
    */

    private Integer sortno;
    /**
     * 删除标识(0 正常 1删除)
     */

    private Integer deleteFlag;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Getter(AccessLevel.NONE)
    private LocalDateTime createTime;

    @TableField(exist = false)
    public String deptIds;

}
