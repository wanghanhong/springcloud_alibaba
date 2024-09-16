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
public class Pool implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 创建时间
     */
    private String create_date;

    /**
     * 量池号码
     */
    private String acc_nbr;

    /**
     * 量池状态
     */
    private String state;

    /**
     * 量池容量
     */
    private String pool_info;

    /**
     * 量池单位
     */
    private String pool_infoUnit;

    /**
     * 失效时间
     */
    private String exp_date;

    /**
     * 生效时间
     */
    private String eff_date;
}
