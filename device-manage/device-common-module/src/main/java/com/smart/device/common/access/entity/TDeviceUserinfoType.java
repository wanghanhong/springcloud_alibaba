package com.smart.device.common.access.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 温湿度计设备基础信息
 * @author jungle
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_device_userinfo_type")
public class TDeviceUserinfoType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "设备编号")
    private Long partId;
    @ApiModelProperty(value = "设备类型")
    private Integer deviceType;

}
