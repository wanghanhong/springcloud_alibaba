package com.smart.device.common.install.entity.area;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 地区设置
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bs_county")
@ApiModel(value = "BsCounty对象", description = "地区设置")
public class BsCounty implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区代码")
    private Long countyCode;

    @ApiModelProperty(value = "父级市代码")
    private Long cityCode;

    @ApiModelProperty(value = "市名称")
    private String countyName;

    @ApiModelProperty(value = "简称")
    private String shortName;

    @ApiModelProperty(value = "经度")
    private String lng;

    @ApiModelProperty(value = "纬度")
    private String lat;

    @ApiModelProperty(value = "层级")
    private Integer level;

    @ApiModelProperty(value = "排序")
    private Integer sort;


}
