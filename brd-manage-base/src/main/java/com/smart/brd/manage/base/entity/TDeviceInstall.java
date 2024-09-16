package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.smart.brd.manage.base.common.dict.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 设备管理表
*
* @author
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_device_install")
public class TDeviceInstall implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    // 品种
    @Dict
    private Integer type;
    //  类别
    @Dict
    private Integer suitable;
    /**
    * 设备编号
    */
    private Long deviceId;
    @Dict
    private Integer deviceType;
    /**
    * 设备编号
    */

    private String deviceCode;
    /**
    * 设备名称
    */

    private String deviceName;
    /**
    * 部门id
    */

    private Long deptId;
    /**
    * 养殖场ID
    */

    private Long fieldId;
    /**
    * 舍ID
    */

    private Long shedId;
    /**
    * 舍
    */

    private String shedName;
    /**
    * 栏ID
    */

    private Long bedId;
    /**
    * 栏
    */

    private String bedName;
    /**
    * 安装位置
    */

    private String location;
    /**
    * 供应商社会信用代码
    */

    private String creditCode;
    /**
    * 供应商名称
    */

    private String supplierName;


    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate installTime;
    /**
    * 设备状态
    */
    @Dict
    private Integer deviceStatus;
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
     * 通道
     */
    private String channel;
    /**
     *播放地址
     */
    @TableField(exist = false)
    private String urlVlc;
    
    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    public String startTime;

    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private String endTime;
}
