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
public class OrderPay implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 状态
     */
    private String status;

    /**
     * 信息
     */
    private String message;

    /**
     * 返回URL
     */
    private String url;
}
