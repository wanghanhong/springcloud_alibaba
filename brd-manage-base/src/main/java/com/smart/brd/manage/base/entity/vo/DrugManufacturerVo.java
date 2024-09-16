package com.smart.brd.manage.base.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author dukzzz
 * @date 2021/3/17 17:37:下午
 * @desc 药品生产商
 */
@Data
public class DrugManufacturerVo implements Serializable {
    /**
     * 生产商
     */
    private String manufacturer;
    /**
     *含量数量
     */

    private Double contentNum;
    /**
     *含量单位
     */

    private String contentUnit;
    /**
     * 药品供应商集合
     */
    private List<DrugSupplierVo> drugSupplierVos;
}
