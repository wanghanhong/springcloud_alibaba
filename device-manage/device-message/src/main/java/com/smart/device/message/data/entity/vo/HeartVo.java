package com.smart.device.message.data.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(description = "报警类查询对象")
public class HeartVo {

    @ApiModelProperty(value = "设备ID")
    private Long deviceId;
    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型")
    private Integer deviceType;

    /**
     * 移动设备识别码
     */
    @ApiModelProperty(value = "移动设备识别码")
    private Long imei;

    /**
     * 移动用户识别码
     */
    @ApiModelProperty(value = "移动用户识别码")
    private Long imsi;

    /**
     * 厂商名称
     */
    @ApiModelProperty(value = "厂商名称")
    private String productName;

    /**
     * 协议
     */
    @ApiModelProperty(value = "协议")
    private String protocol;

}
