package com.smart.brd.manage.base.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart.brd.manage.base.entity.vo.LiveStockVo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EscapeDto extends LiveStockDto{

    private Long id;

    /**
     * 逃逸日期
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate escapeDate;
    /**
     * 逃逸原因
     */
    private String escapeInfo;
    //  淘汰原因
    private Integer dictId;
    private String dictName;



}
