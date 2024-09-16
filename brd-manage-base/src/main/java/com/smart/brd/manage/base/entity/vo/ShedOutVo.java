package com.smart.brd.manage.base.entity.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart.brd.manage.base.common.dict.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author jungle
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShedOutVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    private Long id;

    /**
     * 舍ID
     */
    private Long shedId;

    /**
     * 舍名
     */
    private String shedName;

    /**
     * 栏ID
     */
    private Long bedId;

    /**
     * 栏名
     */
    private String bedName;

    /**
     * 出栏家畜ID
     */
    private Long livestockId;

    /**
     * 牲畜类型
     */
    @Dict
    private String suitable;

    /**
     * 品种
     */
    @Dict
    private String type;

    /**
     * 设备ID
     */
    private String deviceCode;

    /**
     * 入栏日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate entryDate;

    /**
     * 出栏日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate outDate;

    /**
     * 出生日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    /**
     * 部门ID
     */
    private Long deptId;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 在栏时长
     */
    private String entryTime;

    /**
     * 父母代公本
     */
    private String parentMale;

    /**
     * 父母代母本
     */
    private String parentFemale;

    /**
     * 开始时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private String startTime;

    /**
     * 结束时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private String endTime;
    /**
     * 出栏开始时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private String startOutTime;

    /**
     * 出栏结束时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private String endOutTime;

    @TableField(exist = false)
    public String deptIds;

    /**
     * 出栏统计表ID
     */
    private Long outId;

    /**
    * 单价
    * */
    private Double outSingle;

    /**
     * 猪的种类
     * */
    private String varietie;
}
