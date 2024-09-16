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
public class ForwardPoolQuery implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 月可用量
     */
    private String balanceAvailable;

    /**
     * 月已使用量
     */
    private String balanceUsed;

    /**
     * 月剩余量
     */
    private String balanceAmount;

    /**
     * 成员已使用量
     */
    private String ratableAmount;
}
