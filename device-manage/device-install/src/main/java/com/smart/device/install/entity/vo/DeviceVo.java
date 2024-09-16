package com.smart.device.install.entity.vo;

import com.wuwenze.poi.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 设备安装信息
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DeviceVo implements Serializable {

    @ApiModelProperty(value = "设备ID")
    private Long deviceId;
    @ApiModelProperty(value = "设备类型")
    private String deviceTypeName;
    @ApiModelProperty(value = "设备名称")
    private String deviceName;
    @ApiModelProperty(value = "单位ID")
    private Long companyId;
    @ApiModelProperty(value = " 单位名称")
    private String companyName;
    @ApiModelProperty(value = "建筑物编号")
    private Long buildingId;
    @ApiModelProperty(value = "建筑物名称")
    private String buildingName;
    @ApiModelProperty(value = "楼层")
    private Integer buildingFloor;
    @ApiModelProperty(value = "安装位置")
    private String location;
    @ExcelField(value = "设备编号")
    private String deviceCode;
    @ApiModelProperty(value = "设备类型")
    private Integer deviceType;

    @ApiModelProperty(value = "设备状态")
    private Integer deviceState;
    @ApiModelProperty(value = "设备状态")
    private String deviceStateName;
    @ApiModelProperty(value = "设备描述")
    private String questionContent;


}
