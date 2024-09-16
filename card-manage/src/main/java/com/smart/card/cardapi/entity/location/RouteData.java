package com.smart.card.cardapi.entity.location;

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
public class RouteData implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 省份
     */
    private String province;

    /**
     * 进入时间
     */
    private String enterTime;
}
