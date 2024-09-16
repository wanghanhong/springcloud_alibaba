package com.smart.device.common.access.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeviceRedisVo {

    private Long id;
    @ApiModelProperty(value = "移动设备识别码")
    private Long imei;
    @ApiModelProperty(value = "设备状态")
    private Integer deviceState;



}
