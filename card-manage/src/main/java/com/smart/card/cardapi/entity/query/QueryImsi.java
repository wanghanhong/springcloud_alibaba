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
public class QueryImsi implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 流水号
     */
    private String groupTransactionId;

    /**
     * 返回状态标识
     */
    private String resultCode;

    /**
     * 	返回标识说明
     */
    private String resultMsg;

    /**
     * 结果列表
     */
    private ImsiInfo description;
}
