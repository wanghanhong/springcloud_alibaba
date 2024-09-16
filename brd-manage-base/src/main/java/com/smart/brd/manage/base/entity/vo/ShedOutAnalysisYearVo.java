package com.smart.brd.manage.base.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShedOutAnalysisYearVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 出栏年份
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy")
    private String outDate;

    /**
     * 每年出栏的数量
     */
    public Long countPig;
}
