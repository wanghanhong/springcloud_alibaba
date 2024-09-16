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
* 省市县镇村数据
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bs_village")
public class BsVillage implements Serializable {

    private static final long serialVersionUID = -1714476694755134924L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 镇id
    */

    private Long streetCode;
    /**
    * 村id--唯一
    */

    private Long villageCode;
    /**
    * 村名称
    */

    private String villageName;
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
