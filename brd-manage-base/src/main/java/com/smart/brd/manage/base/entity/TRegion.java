package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 区域表
*
* @author
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_region")
public class TRegion implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
     @TableId(value = "region_code", type = IdType.INPUT)
    private String regionCode;

    private String parentRegionCode;

    private String regionName;

    private String mergerName;

    private String shortName;

    private String mergerShortName;

    private String regionLevel;

    private String cityCode;

    private String zipCode;

    private String pinyin;

    private String jianpin;

    private String firstChar;

    private String longitude;

    private String latitude;

    private String remarks;
    /**
    * 省
    */

    private String province;
    /**
    * 市
    */

    private String city;
    /**
    * 县
    */

    private String county;
    /**
    * 乡村
    */

    private String town;


    @TableField(exist = false)
    public String deptIds;
}
