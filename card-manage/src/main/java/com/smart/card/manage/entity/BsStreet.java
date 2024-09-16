package com.smart.card.manage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 街道设置
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bs_street")
public class BsStreet implements Serializable {

    private static final long serialVersionUID = -1714476694755134924L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 街道代码
    */

    private Long streetCode;
    /**
    * 父级区代码
    */

    private Long countyCode;
    /**
    * 街道名称
    */

    private String streetName;
    /**
    * 简称
    */

    private String shortName;
    /**
    * 经度
    */

    private String lng;
    /**
    * 纬度
    */

    private String lat;
    /**
    * 层级
    */

    private Integer level;
    /**
    * 排序
    */

    private Integer sort;


    @TableField(exist = false)
    public String deptIds;
}
