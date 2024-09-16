package com.smart.brd.manage.base.entity.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class LivestockInfo {
    /**
     * 耳标编号
     */
    private String deviceCode;
    /**
     * 家畜id
     */
    private Long livestockId;
    /**
     * 家畜类别
     */
    private String suitable;
    /**
     * 畜舍名称
     */
    private String shedName;
    /**
     * 畜栏名称
     */
    private String bedName;
    /**
     * 入栏日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDate entryDate;
    /**
     * 出生日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    /**
     * 供货商名称
     */
    private String supplierName;
    /**
     * 信用代码
     */
    private String creditCode;
}
