package com.smart.brd.manage.base.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author dukzzz
 * @date 2021/3/17 17:38:下午
 * @desc 疫苗供应商
 */
@Data
public class VaccinesSupplierVo implements Serializable {
    /**
     * 疫苗供应商名称
     */

    private String supplierName;
    /**
     * 单价
     */

    private BigDecimal unitPrice;
}
