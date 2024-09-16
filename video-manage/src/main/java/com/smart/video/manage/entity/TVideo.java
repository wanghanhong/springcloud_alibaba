package com.smart.video.manage.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 设备管理表
 *
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_video")
public class TVideo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 记录ID
     */
    @TableId(value = "device_id", type = IdType.INPUT)
    private Long deviceId;

    /**
     * 设备类型1 耳标 2 温度传感器
     */
    @Excel(name = "设备类型",replace={"耳标_0","温度传感器_1"}, orderNum = "0" )
    private Integer deviceType;

    /**
     * 设备编号
     */
    @Excel(name = "设备编号",orderNum = "1")
    private String deviceCode;
    private String deviceModel;
    private String playUrl;

    /**
     * 设备名称
     */
    @Excel(name = "设备名称",orderNum = "2")
    private String deviceName;

    /**
     * 生产厂商
     */
    @Excel(name = "生产厂商",orderNum = "3")
    private String supplierId;

    /**
     * 生产日期
     */
    @Excel(name = "生产日期",format = "yy-MM-dd",orderNum = "4")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate productDate;

    /**
     * 报废日期
     */
    @Excel(name = "报废日期",format = "yy-MM-dd",orderNum = "5")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate scrapDate;

    /**
     * 部门id
     */
    private Long deptId;

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

}
