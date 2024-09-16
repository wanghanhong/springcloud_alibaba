package com.smart.brd.manage.base.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author dukzzz
 * @date 2021/3/17 17:37:下午
 * @desc 疫苗生产商
 */
@Data
public class VaccinesManufacturerVo implements Serializable {

    /**
     *生产商
     */
    private String manufacturer;
    /**
     * 含量
     */
    private String content;
    /**
     * 疫苗供应商集合
     */
    private List<VaccinesSupplierVo> vaccinesSupplierVoList;
}
