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
public class OrderFlow implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 响应消息
     */
    private String checkout;

    /**
     * 提示信息
     */
    private String messg;

    /**
     * 	订购信息
     */
    private String info;

    /**
     * 流水号
     */
    private String GROUP_TRANSACTIONID;
}
