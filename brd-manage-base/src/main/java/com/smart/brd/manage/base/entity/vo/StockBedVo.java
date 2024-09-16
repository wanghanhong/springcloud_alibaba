package com.smart.brd.manage.base.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author junglelocal
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StockBedVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 栏ID
     */
    private Long bedId;

    /**
     * 舍id
     */
    private Long shedId;

    /**
     * 栏名称
     */
    private String bedName;
}
