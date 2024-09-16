package com.smart.device.common.install.entity.area;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 省市县镇村数据
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bs_village")
@ApiModel(value = "BsVillage对象", description = "省市县镇村数据")
public class BsVillage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "镇id")
    private Long streetCode;

    @ApiModelProperty(value = "村id--唯一")
    private Long villageCode;

    @ApiModelProperty(value = "村名称")
    private String villageName;

    @ApiModelProperty(value = "经度")
    private String lng;

    @ApiModelProperty(value = "纬度")
    private String lat;

    @ApiModelProperty(value = "层级")
    private Integer level;

    @ApiModelProperty(value = "排序")
    private Integer sort;


}
