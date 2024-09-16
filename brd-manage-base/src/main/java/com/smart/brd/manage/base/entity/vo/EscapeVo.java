package com.smart.brd.manage.base.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EscapeVo extends LiveStockVo{

    private Long id;

    /**
     * 逃逸日期
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private String escapeDate;
    /**
     * 逃逸原因
     */
    private String escapeInfo;
    //  淘汰原因
    private Integer dictId;
    private String dictName;



}
