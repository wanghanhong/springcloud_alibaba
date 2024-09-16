package com.smart.device.common.install.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 电力设备安装信息
 *
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_manager_cameras")
public class TManagerCameras implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 设备ID
     */
    @ApiModelProperty(value = "设备ID")
    private Long deviceId;
    @ApiModelProperty(value = "设备编号")
    private Long deviceCode;
    /**
     * IMEI
     */
    @ApiModelProperty(value = "IMEI")
    private Long imei;

    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型")
    private Integer deviceType;

    /**
     * 设备类型1 烟感
     */
    @ApiModelProperty(value = "设备类型")
    private String deviceTypeName;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    /**
     * 设备型号
     */
    @ApiModelProperty(value = "设备型号")
    private String deviceModel;
    @ApiModelProperty(value = "设备类型")
    private Integer parentType;
    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    private Long companyId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String companyName;

    /**
     * 建筑物编号
     */
    @ApiModelProperty(value = "建筑物编号")
    private Long buildingId;

    /**
     * 建筑物
     */
    @ApiModelProperty(value = "建筑物名称")
    private String buildingName;

    /**
     * 楼层
     */
    @ApiModelProperty(value = "楼层")
    private Integer buildingFloor;

    /**
     * 安装位置
     */
    @ApiModelProperty(value = "安装位置")
    private String location;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private String longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private String latitude;

    /**
     * 设备状态 0 新安装 1 在线 2 离线 3  报废
     */
    @ApiModelProperty(value = "设备状态")
    private Integer deviceState;

    /**
     * 设备状态 0 新安装 1 在线 2 离线 3  报废
     */
    @ApiModelProperty(value = "设备状态")
    private String deviceStateName;
    @ApiModelProperty(value = "最后上报时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime finalTime;
    @ApiModelProperty(value = "序列号")
    private String sn;
    @ApiModelProperty(value = "所属组织0第三方1自建")
    private String belongingToId;
    @ApiModelProperty(value = "所属组织")
    private String belongingToName;
    @ApiModelProperty(value = "mac地址")
    private String mac;
    @ApiModelProperty(value = "设备IP地址")
    private String deviceIp;
    @ApiModelProperty(value = "视频流地址")
    private String streamIp;
    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime updateTime;

    /**
     * 删除标识(0 正常 1删除)
     */
    @ApiModelProperty(value = "删除标识")
    private Integer deleteFlag;

    // 操作人
    private Long opUserId;
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
