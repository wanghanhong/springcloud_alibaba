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
public class FlowRes implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 应答状态
     */
    private String iresult;

    /**
     * 充值信息
     */
    private String smsg;

    /**
     * 流水号
     */
    private String group_transactionid;
}
