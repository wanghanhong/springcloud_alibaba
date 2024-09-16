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
public class PoolQuery implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 应答状态
     */
    private String iresult;

    /**
     * 流量池已用流量
     */
    private String pool_already;

    /**
     * 流量池剩余流量
     */
    private String pool_left;

    /**
     * 流量池总流量
     */
    private String pool_total;

    /**
     * 流水号
     */
    private String group_transactionid;

    /**
     * 流量池信息
     */
    private PoolInfo PoolInfo;
}
