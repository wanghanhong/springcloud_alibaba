package com.smart.card.cardapi.entity.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author junglelocal
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SimList implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 接入号码
     */
    private Long accNumber;

    /**
     * iccid
     */
    private String iccid;

    /**
     * 激活时间
     */
    private String activationTime;

    /**
     * 激活时间
     */
    private String createTime;

    /**
     * sim卡状态
     */
    private List<String> simStatus;

    /**
     * 客户ID
     */
    private String custId;
}
