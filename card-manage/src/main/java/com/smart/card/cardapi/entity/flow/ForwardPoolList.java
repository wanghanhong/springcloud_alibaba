package com.smart.card.cardapi.entity.flow;

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
public class ForwardPoolList implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 产品接入号码
     */
    private String phoneNum;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品状态名称
     */
    private String prodStatusName;

    /**
     * 所属地区
     */
    private String commonRegionName;

    /**
     * 激活时间
     */
    private String startDt;

    /**
     * 套餐名称
     */
    private String prodOfferName;
}
