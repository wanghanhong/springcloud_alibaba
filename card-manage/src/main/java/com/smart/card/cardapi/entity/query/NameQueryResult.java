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
public class NameQueryResult implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 激活时间
     */
    private String activeTime;

    /**
     * 产品状态
     */
    private String prodStatusName;

    /**
     * 使用人身份证号码
     */
    private String certNumber;

    /**
     * 查询号码
     */
    private String number;
}
