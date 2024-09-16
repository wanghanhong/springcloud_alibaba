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
public class ServiceResponse implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 请求状态
     */
    private String RspType;

    /**
     * 状态响应码
     */
    private String result;

    /**
     * 返回消息信息
     */
    private String resultMsg;

    /**
     * 流水号
     */
    private String group_transactionid;
}
