package com.smart.card.cardapi.entity.service;

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
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 卡主状态编码
     */
    private String productMainStatusCd;

    /**
     * 卡主状态描述
     */
    private String productMainStatusName;
}
