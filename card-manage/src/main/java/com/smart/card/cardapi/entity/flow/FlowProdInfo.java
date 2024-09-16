package com.smart.card.cardapi.entity.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlowProdInfo implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 成员号码
     */
    private String acc_nbr;

    /**
     * 生效时间
     */
    private String eff_date;

    /**
     * 流量限额
     */
    private String flow_quota;

    /**
     * 成员归属地
     */
    private String org_id;

    /**
     * 成员限额类型
     */
    private String quota_type;

    /**
     * 成员状态
     */
    private String state;
}
