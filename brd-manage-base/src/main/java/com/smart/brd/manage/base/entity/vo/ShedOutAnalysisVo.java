package com.smart.brd.manage.base.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShedOutAnalysisVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 出栏日期
     */
    private String outDate;

    /**
     * 每个月出栏的数量
     */
    public Integer countPig;
}
