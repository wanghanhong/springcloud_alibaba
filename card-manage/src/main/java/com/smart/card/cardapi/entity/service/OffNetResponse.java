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
public class OffNetResponse implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 请求结果标识
     */
    private String RspType;

    /**
     * 请求结果代码
     */
    private String RspCode;

    /**
     * 请求结果说明
     */
    private String RspDesc;

    /**
     * 流水号
     */
    private String group_transactionid;
}
