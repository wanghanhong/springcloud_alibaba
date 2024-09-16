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
public class PoolInfo implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 流量池成员号码
     */
    private String acc_nbr;

    /**
     * 成员已使用流量
     */
    private String pool_member_already;

    /**
     * 成员总流量
     */
    private String pool_member_total;
}
