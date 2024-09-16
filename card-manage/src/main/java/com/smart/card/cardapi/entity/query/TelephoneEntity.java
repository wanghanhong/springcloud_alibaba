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
public class TelephoneEntity implements Serializable {

    /**
     * 结果代码
     */
    private String result;

    /**
     * 查询结果
     */
    private String smsg;
}
