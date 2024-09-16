package com.smart.card.cardapi.entity.prods;

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
public class FunProdInfo implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 所属属性
     */
    private List<AttrInfo> attrInfos;
}
