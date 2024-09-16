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
public class ForwardResMem implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 请求结果标识
     */
    private String resultCode;

    /**
     * 请求结果内容
     */
    private String resultMsg;

    /**
     * 流水号
     */
    private String groupTransactionId;

    /**
     * 流水号2
     */
    private String group_transactionid;

    /**
     * 流量池成员
     */
    private ForwardPoolMember description;
}
