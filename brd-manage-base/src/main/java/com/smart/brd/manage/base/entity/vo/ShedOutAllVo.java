package com.smart.brd.manage.base.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author junglelocal
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class ShedOutAllVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 选择的牲畜ID
     */
    private List<Long> livestockIds;

    /**
     * 出栏数量
     */
    private Integer outNumber;

    /**
     * 毛重
     */
    private Double outWeight;

    /**
     * 出栏单价
     */
    private Double outSingle;

    /**
     * 销售金额
     */
    private Double outSales;

    /**
     * 出栏预定时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate outTime;

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
    private Long deptId;
    private String deptIds;
}
