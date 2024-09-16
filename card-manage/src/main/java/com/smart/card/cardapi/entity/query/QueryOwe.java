package com.smart.card.cardapi.entity.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class QueryOwe implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;
    /**
    * 14910000000
    */
    @JsonProperty(value = "Acc_Nbr")
    private String acc_nbr;
    /**
    * 用户级
    */
    @JsonProperty(value = "Query_Flag")
    private String query_flag;
    /**
    * 中国电信股份有限公司物联网分公司
    */
    @JsonProperty(value = "Acct_Name")
    private String acct_name;
    /**
     * 中国电信股份有限公司物联网分公司
     */
    @JsonProperty(value = "Acct_ID")
    private String acct_id;
    /**
    * 1650
    */
    @JsonProperty(value = "Acct_Item_Charge")
    private String acct_item_charge;
    /**
    * 物联网短信套餐费
    */
    @JsonProperty(value = "Acct_Item_Type_Name")
    private String acct_item_type_name;
    /**
    * 201612
    */
    @JsonProperty(value = "Billing_Cycle_ID")
    private String billing_cycle_id;

}
