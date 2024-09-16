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
public class BindDetection implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 接入号码
     */
    private String msisdn;

    /**
     * 卡号
     */
    private String iccid;

    /**
     * IMSI号码
     */
    private String imsi;

    /**
     * IMEI对应终端类型（最近一次上报）
     */
    private String lastIMEI;

    /**
     * 	IMEI对应终端类型 （HSS签约）
     */
    private String signedDeviceType;

    /**
     *IMEI（HSS签约）
     */
    private String imei;

    /**
     * 	IMEI对应终端类型（最近一次上报）
     */
    private String deviceType;

    /**
     * 机卡绑定功能 开通、未开通
     */
    private String bingdingType;

    /**
     * 机卡匹配结果 是，匹配成功 、否，匹配失败，即IMSI/IMEI不在匹配设定范围
     */
    private String bingdingStatus;
}
