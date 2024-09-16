package com.smart.card.cardapi.entity.prods;

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
public class ProdOfferInfo implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 销售品规格名称
     */
    private String prodOfferName;

    /**
     * 套餐ID
     */
    private String prodOfferNbr;

    /**
     * 销售品生效时间
     */
    private String startDt;

    /**
     * 销售品失效时间
     */
    private String endDt;

    /**
     * 状态名称
     */
    private String statusName;
}
