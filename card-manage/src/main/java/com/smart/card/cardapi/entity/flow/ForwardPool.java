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
public class ForwardPool implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 组别名
     */
    private String groupNum;

    /**
     * 客户ID
     */
    private String custId;

    /**
     * 客户名称
     */
    private String custName;
}
