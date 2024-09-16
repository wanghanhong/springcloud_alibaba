package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 设备基础信息
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_device_cameras")
public class TDeviceCameras implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 设备名称
    */

    private String deviceName;
    /**
    * 设备编号
    */

    private String deviceCode;
    /**
    * 设备类型
    */

    private Integer deviceType;
    /**
    * 设备类型名称
    */

    private String deviceTypeName;
    /**
    * 设备类型
    */

    private Integer parentType;
    /**
    * 设备型号
    */

    private String deviceModel;
    /**
    * 移动设备识别码
    */

    private Long imei;
    /**
    * 移动用户识别码
    */

    private Long imsi;
    /**
    * 厂商id
    */

    private Long productId;
    /**
    * 厂商名称
    */

    private String productName;
    /**
    * 协议
    */

    private String protocol;
    /**
    * 出厂时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate outFactoryTime;
    /**
    * 报废时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate scrapTime;
    /**
    * 硬件版本号
    */

    private String hardVersion;
    /**
    * 软件版本号
    */

    private String softVersion;
    /**
    * 新增时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
    * 删除标识(0 正常 1删除)
    */

    private Integer deleteFlag;
    /**
    * 第三方平台设备ID
    */

    private String thirdDeviceId;
    /**
    * 设备状态 1在线正常 2离线异常
    */

    private Integer deviceState;
    /**
    * 设备状态  1 正常 2 异常
    */

    private String deviceStateName;
    /**
    * 最后上报时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finalTime;
    /**
    * 设备IP地址
    */

    private String deviceIp;
    /**
    * mac 地址
    */

    private String mac;
    /**
    * 序列号
    */

    private String sn;
    /**
    * 视频流地址
    */

    private String streamIp;
    /**
    * 部门id
    */

    private Long deptId;
    /**
    * 操作单位
    */

    private Long opCompanyId;
    /**
    * 操作人
    */

    private Integer opUserId;


    @TableField(exist = false)
    public String deptIds;
}
