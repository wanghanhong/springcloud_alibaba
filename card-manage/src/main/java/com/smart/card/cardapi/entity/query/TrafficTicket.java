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
public class TrafficTicket implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 流量
     */
    private String bytes_cnt;

    /**
     * 使用时长
     */
    private String duration_ch;

    /**
     * 承载网络
     */
    private String service_type;

    /**
     * 上线时间
     */
    private String start_time;

    /**
     * 话费
     */
    private String ticket_charge_ch;

    /**
     * 序号
     */
    private String ticket_number;

    /**
     * 通信地点
     */
    private String ticket_type;
}
