package com.smart.device.common.access.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 电力设备基础信息
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_device_cameras")
@ApiModel(value = "TDeviceCameras对象", description = "电力设备基础信息")
public class TDeviceCameras implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "设备编号")
    private Long deviceCode;

    @ApiModelProperty(value = "设备类型")
    private Integer deviceType;

    @ApiModelProperty(value = "设备类型名称")
    private String deviceTypeName;
    @ApiModelProperty(value = "设备类型")
    private Integer parentType;
    @ApiModelProperty(value = "设备型号")
    private String deviceModel;

    @ApiModelProperty(value = "移动设备识别码")
    private Long imei;

    @ApiModelProperty(value = "移动用户识别码")
    private Long imsi;

    @ApiModelProperty(value = "厂商id")
    private Long productId;

    @ApiModelProperty(value = "厂商名称")
    private String productName;

    @ApiModelProperty(value = "协议")
    private String protocol;

    @ApiModelProperty(value = "出厂时间")
    private Date outFactoryTime;

    @ApiModelProperty(value = "报废时间")
    private Date scrapTime;

    @ApiModelProperty(value = "硬件版本号")
    private String hardVersion;

    @ApiModelProperty(value = "软件版本号")
    private String softVersion;

    @ApiModelProperty(value = "mac地址")
    private String mac;
    @ApiModelProperty(value = "设备IP地址")
    private String deviceIp;

    @ApiModelProperty(value = "新增时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;

    @ApiModelProperty(value = "第三方平台设备ID")
    private String thirdDeviceId;

    @ApiModelProperty(value = "设备状态")
    private Integer deviceState;
    @ApiModelProperty(value = "设备状态")
    private String deviceStateName;
    @ApiModelProperty(value = "序列号")
    private String sn;

    @ApiModelProperty(value = "操作单位")
    private Long opCompanyId;
    @ApiModelProperty(value = "操作人")
    private Long opUserId;


}
