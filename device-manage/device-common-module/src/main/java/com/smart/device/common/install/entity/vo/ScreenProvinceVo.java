package com.smart.device.common.install.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ScreenProvinceVo {

    @ApiModelProperty(value = "编码")
    private String regionCode;
    @ApiModelProperty(value = "名称")
    private String regionName;
    @ApiModelProperty(value = "设备类型")
    private Integer parentType;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "数量")
    private int countNum;

    @ApiModelProperty(value = "pk")
    private String cpk;
    @ApiModelProperty(value = "设备Ids")
    private String ids;


    private String name;
    private int count;


}
