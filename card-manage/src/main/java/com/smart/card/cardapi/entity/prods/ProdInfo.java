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
public class ProdInfo implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 产品状态名称
     */
    private String prodStatusName;

    /**
     * 产品状态名称
     */
    private Integer stopFlag;

    /**
     * 产品状态名称
     */
    private Integer isPoolMember;

    /**
     * 产品状态名称 1-后向
     */
    private Integer ownedPoolType;

    /**
     * 产品状态名称
     */
    private Long phoneNum;

    /**
     * 产品状态名称
     */
    private String custName;

    /**
     * 产品状态名称
     */
    private String prodMainStatusName;

    /**
     * 产品状态名称
     */
    private String productName;

    /**
     * 产品状态名称
     */
    private String commonRegionName;

    /**
     * 产品状态名称
     */
    private List<ProdOfferInfo> prodOfferInfos;

    /**
     * 产品状态名称
     */
    private List<FunProdInfo> funProdInfos;

    /**
     * 产品状态名称
     */
    private List<AttrInfo> attrInfos;

    /**
     * 产品状态名称
     */
    private Long ownedPoolNumber;
}
