package com.smart.card.cardapi.entity.query;

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
public class ImsiInfo implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 接入号码
     */
    private String msisdn;

    /**
     * 	iccid
     */
    private String iccid;

    /**
     * imsi
     */
    private String imsi;

    /**
     * 查询结果来源
     */
    private String result;

    /**
     * 查询结果描述
     */
    private String source;
}
