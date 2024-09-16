package com.smart.card.cardapi.entity.query;

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
public class TelephonePlus implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;
    /**
     * 接入号码
     */
    private String access_number;

    /**
     * ICCID
     */
    @JsonProperty(value = "ICCID")
    private String iccid;

    /**
     * 4G IMSI
     */
    @JsonProperty(value = "4G_IMSI")
    private String imsi4g;
    /**
     * 3G IMSI
     */
    @JsonProperty(value = "3G_IMSI")
    private String imsi3g;
}
