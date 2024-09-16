package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* 耳标心跳记录
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_heart_device")
public class THeartDevice implements Serializable {

    private static final long serialVersionUID = -1714476694755134924L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 设备ID
    */

    private Long deviceId;
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
    * 服务器key码
    */

    private String serverKey;
    /**
    * 设备电压
    */

    private Float bat;
    /**
    * 标签电压
    */

    private Float batteryPower;
    /**
    * 心跳时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventTime;
    /**
    * 信号强度
    */

    private Integer lterssi;
    /**
    * 开盒次数
    */

    private Integer damage;
    /**
    * 体温(摄氏度)
    */

    private Float temperature;
    /**
    * 环境温度(摄氏度)
    */

    private Float envTemperature;
    /**
    * 标签信号 0-正常 1-异常
    */

    private Integer rssi;
    /**
    * 上网类型
    */

    private String netmode;
    /**
    * MAC地址
    */

    private String mac;
    /**
    * 新增时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
    * 删除标识(0 正常 1删除)
    */

    private Integer deleteFlag;


    @TableField(exist = false)
    public String deptIds;

    /**
     * 厂家
     */
    private String manufacturers;

    /**
     * 距离（取值范围 : 0-100）
     */
    private Integer distance;

    /**
     * 步数（取值范围 : 0-9999999）
     */
    private Long walk;
}
