package com.smart.card.cardapi.entity.service;

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
public class ResponseVo implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 请求结果说明
     */
    private String Response;

    private String GROUP_TRANSACTIONID;
}
