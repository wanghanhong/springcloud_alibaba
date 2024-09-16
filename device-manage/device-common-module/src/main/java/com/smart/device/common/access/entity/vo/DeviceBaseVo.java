package com.smart.device.common.access.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DeviceBaseVo {

    public Long id;
    @ApiModelProperty(value = "设备ID")
    private Long deviceId;
    @ApiModelProperty(value = "设备名称")
    public String deviceName;

    @ApiModelProperty(value = "设备编号")
    public Long deviceCode;

    @ApiModelProperty(value = "设备类型")
    public Integer deviceType;

    @ApiModelProperty(value = "设备类型名称")
    public String deviceTypeName;
    @ApiModelProperty(value = "设备类型")
    public Integer parentType;
    @ApiModelProperty(value = "设备型号")
    public String deviceModel;

    @ApiModelProperty(value = "移动设备识别码")
    public Long imei;

    @ApiModelProperty(value = "移动用户识别码")
    public Long imsi;

    @ApiModelProperty(value = "厂商id")
    public Long productId;

    @ApiModelProperty(value = "厂商名称")
    public String productName;

    @ApiModelProperty(value = "协议")
    public String protocol;

    @ApiModelProperty(value = "出厂时间")
    public Date outFactoryTime;

    @ApiModelProperty(value = "报废时间")
    public Date scrapTime;

    @ApiModelProperty(value = "硬件版本号")
    public String hardVersion;

    @ApiModelProperty(value = "软件版本号")
    public String softVersion;

    /**
     * 设备状态          1在线正常 2离线异常
     * 设备状态 0 新安装 1 在线 2 离线 3  报废
     */
    @ApiModelProperty(value = "设备状态")
    private Integer deviceState;

    /**设备状态 1在线正常 2离线异常
     * 设备状态 0 新安装 1 在线 2 离线 3  报废
     */
    @ApiModelProperty(value = "设备状态")
    private String deviceStateName;

    @ApiModelProperty(value = "心跳间隔")
    private Integer heartInterval;
    @ApiModelProperty(value = "iccid 号")
    private String iccid;

    @ApiModelProperty(value = "最后上报时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime finalTime;

    //--电力------------------------------------------
    @ApiModelProperty(value = "报警温度设置的值")
    private Float alarmTemperature;
    @ApiModelProperty(value = "最大电流设置")
    private String alarmElectric;
    @ApiModelProperty(value = "漏电电流设置")
    private String alarmElectricLeakage;

    //--摄像头------------------------------------------
    @ApiModelProperty(value = "序列号")
    private String sn;
    private String mac;
    @ApiModelProperty(value = "设备IP地址")
    private String deviceIp;
    @ApiModelProperty(value = "视频流地址")
    private String streamIp;

}
