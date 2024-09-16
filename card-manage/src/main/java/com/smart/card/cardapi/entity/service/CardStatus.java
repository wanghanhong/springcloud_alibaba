package com.smart.card.cardapi.entity.service;

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
public class CardStatus implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 状态响应码
     */
    private String resultCode;

    /**
     * 返回消息信息
     */
    private String resultMsg;

    /**
     * 流水号
     */
    private String group_transactionid;

    /**
     * 查询号码
     */
    private String number;

    /**
     * 卡生效时间
     */
    private String servCreateDate;

    /**
     * 产品信息节点
     */
    private List<ProductInfo> productInfo;
}
