package com.smart.device.common.install.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *  设备监控
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DeviceMonitorVo implements Serializable {

    @ApiModelProperty(value = "设备ID")
    private Long deviceId;
    @ApiModelProperty(value = "设备类型")
    private Integer parentType;

    @ApiModelProperty(value = "单位ID")
    private Long companyId;
    @ApiModelProperty(value = " 单位名称")
    private String companyName;
    @ApiModelProperty(value = "建筑物编号")
    private Long buildingId;
    @ApiModelProperty(value = "建筑物名称")
    private String buildingName;
    @ApiModelProperty(value = "建筑物数量")
    private int buildingNum;

    @ApiModelProperty(value = "烟感设备数量")
    private int smokeNum;
    @ApiModelProperty(value = "电气设备数量")
    private int electricNum;
    @ApiModelProperty(value = "水压设备数量")
    private int waterpressNum;

    @ApiModelProperty(value = "设备数量")
    private int fireAllNum;
    @ApiModelProperty(value = "未处理数量")
    private int dealNum;

    @ApiModelProperty(value = "处理")
    private int state;
    @ApiModelProperty(value = "处理状态")
    private String stateName;
    @ApiModelProperty(value = "处理结果")
    private String result;
    @ApiModelProperty(value = "处理时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "事件编号")
    private String eventCode;
    @ApiModelProperty(value = "事件内容")
    private String content;
    @ApiModelProperty(value = "建筑物层数")
    private Integer buildingFloor;
    @ApiModelProperty(value = "安装位置")
    private String location;

    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "县")
    private String county;
    @ApiModelProperty(value = "乡村")
    private String town;
    @ApiModelProperty(value = "小区")
    private String housing;

}
