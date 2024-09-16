package com.smart.card.cardapi.entity.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author junglelocal
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SimOrderList {
    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 进入号
     */
    private String accessNumber;

    /**
     * 流水号
     */
    private String requestId;

    /**
     * 服务类别
     */
    private String serviceType;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    private String createTime;
}
