package com.smart.card.cardapi.entity.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryPakage implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;
    /**
    * 20201030154017
    */
    @JsonProperty(value = "START_TIME")
    private String start_time;
    /**
    * 20210430000000
    */
    @JsonProperty(value = "END_TIME")
    private String end_time;
    /**
    * 物联网-NB-限免流量20M6个月（201706）
    */
    @JsonProperty(value = "OFFER_NAME")
    private String offer_name;
    /**
    *
    */
    @JsonProperty(value = "FLOW_ID_GROUP_NAME")
    private String flow_id_group_name;
    /**
    * 49
    */
    @JsonProperty(value = "CUMULATION_ALREADY")
    private String cumulation_already;
    /**
    * 20480
    */
    @JsonProperty(value = "CUMULATION_TOTAL")
    private String cumulation_total;
    /**
    * KB
    */
    @JsonProperty(value = "UNIT_NAME")
    private String unit_name;
    /**
    * 
    */
    @JsonProperty(value = "OFFER_ID")
    private String offer_id;
    /**
    * 20431
    */
    @JsonProperty(value = "CUMULATION_LEFT")
    private String cumulation_left;
    /**
    * 物联网-NB-限免流量20M6个月（201706）
    */
    @JsonProperty(value = "ACCU_NAME")
    private String accu_name;
    /**
    * 0
    */
    @JsonProperty(value = "OFFER_TYPE")
    private String offer_type;
    /**
    *
    */
    @JsonProperty(value = "FLOW_ID_GROUP_NBR")
    private String flow_id_group_nbr;

}
