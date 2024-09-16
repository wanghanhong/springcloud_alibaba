package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 设备类别字典
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_base_dict_type")
public class TBaseDictType implements Serializable {

    private static final long serialVersionUID = 1L;
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
     * 上级id
     */
    private String parentId;
    /**
    * 排序
    */

    private Integer sortno;
    /**
     * 自定义ID，用来计数
     */
    private Integer uniqueId;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     *是否可以删除
     */
    private Integer isCan;

    /**
     * 删除标识(0 正常 1删除)
     */
    private Integer deleteFlag;

    @TableField(exist = false)
    public String deptIds;

    @TableField(exist = false)
    private String dictShow;

    @TableField(exist = false)
    private List<TBaseDict> dictList;
}
