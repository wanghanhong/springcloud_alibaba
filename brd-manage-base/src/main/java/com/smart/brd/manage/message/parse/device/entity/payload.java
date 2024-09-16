package com.smart.brd.manage.message.parse.device.entity;

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
public class payload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 发送时间的时间戳
     */
    private Long uptime;

    /**
     * 体温温度范围30~42℃
     */
    private Double temperature;

    /**
     * 电压（取值范围 : 2.0-3.5）
     */
    private Double voltage;

    /**
     * 距离（取值范围 : 0-100）
     */
    private Integer distance;

    /**
     * 步数（取值范围 : 0-9999999）
     */
    private Long walk;

    /**
     * 标签号码
     */
    private String aaid;

}
