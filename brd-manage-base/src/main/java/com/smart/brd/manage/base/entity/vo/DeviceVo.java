package com.smart.brd.manage.base.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart.brd.manage.base.common.dict.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DeviceVo implements Serializable {

    private static final long serialVersionUID = -135546577531L;

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
     *播放地址
     */
    @TableField(exist = false)
    private String urlVlc;
}
