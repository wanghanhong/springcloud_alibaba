package com.smart.device.common.message.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 烟感设备心跳日志
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_heart_waterpress")
@ApiModel(value = "THeartWaterpress对象", description = "烟感设备心跳日志")
public class THeartWaterpress implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    @ApiModelProperty(value = "设备ID")
    private Long deviceId;
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

    @ApiModelProperty(value = "心跳时间")
    private LocalDateTime heartTime;
    @ApiModelProperty(value = "事件code")
    private String eventCode;
    @ApiModelProperty(value = "信号强度")
    private Integer signalStrength;

    @ApiModelProperty(value = "设备电压")
    private Float deviceVolt;

    @ApiModelProperty(value = "设备剩余电量(百分比)")
    private Float electricQuantity;

    @ApiModelProperty(value = "设备摄氏温度(摄氏度)")
    private Float temperature;

    @ApiModelProperty(value = "采集间隔时间单位0-秒，1-分，2-时")
    private Integer colletUint;

    @ApiModelProperty(value = "采集间隔时间值")
    private Integer colletValue;

    @ApiModelProperty(value = "上传间隔时间单位0-秒，1-分，2-时")
    private Integer transUint;

    @ApiModelProperty(value = "上传间隔时间值")
    private Integer transValue;

    @ApiModelProperty(value = "设备水压低压报警门限")
    private Float lowValue;

    @ApiModelProperty(value = "设备水压高压报警门限")
    private Float highValue;

    @ApiModelProperty(value = "水压当前值")
    private Float presentValue;

    @ApiModelProperty(value = "水压采集时间")
    private LocalDateTime wptime1;

    @ApiModelProperty(value = "水压数据")
    private Float wpdata1;

    @ApiModelProperty(value = "水压采集时间")
    private LocalDateTime wptime2;

    @ApiModelProperty(value = "水压数据")
    private Float wpdata2;

    @ApiModelProperty(value = "水压采集时间")
    private LocalDateTime wptime3;

    @ApiModelProperty(value = "水压数据")
    private Float wpdata3;

    @ApiModelProperty(value = "水压采集时间")
    private LocalDateTime wptime4;

    @ApiModelProperty(value = "水压数据")
    private Float wpdata4;
    @ApiModelProperty(value = "事件类型")
    private Integer type;
    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "新增时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "状态更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;

    @ApiModelProperty(value = "点位编码")
    private String address;

    @TableField(exist = false)
    private THeartWaterpressSon wpSon;

}
