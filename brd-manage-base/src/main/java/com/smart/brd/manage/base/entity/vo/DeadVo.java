package com.smart.brd.manage.base.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DeadVo extends LiveStockVo{

    private Long id;
    private Integer dictId;
    /**
     * 畜病
     */
    private String dictName;
    /**
     * 死亡日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate deadDate;


}
