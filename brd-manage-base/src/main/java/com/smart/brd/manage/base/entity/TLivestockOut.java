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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 出栏总计
 * @author junglelocal
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_livestock_out")
public class TLivestockOut implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 出栏数目
     */
    private Long outNumber;

    /**
     * 出栏日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate outDate;

    /**
     * 出栏单价
     */
    private Double outSingle;

    /**
     * 总毛重
     */
    private Double outWeight;

    /**
     * 总销售额
     */
    private Double outSales;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 删除标记 0-正常 1-删除
     */
    private Integer deleteFlag;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @TableField(exist = false)
    public String deptIds;
}
