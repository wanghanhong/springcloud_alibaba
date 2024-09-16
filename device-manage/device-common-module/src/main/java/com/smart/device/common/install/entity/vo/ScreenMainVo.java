package com.smart.device.common.install.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScreenMainVo {

    @ApiModelProperty(value = " 1 消防单位 2社会单位 3九小场所 4维保单位")
    private Integer[] type;
    @ApiModelProperty(value = " 1 消防单位 2社会单位 3九小场所 4维保单位")
    private String[] typeName;
    @ApiModelProperty(value = "数量")
    private int[] countNum;


}
