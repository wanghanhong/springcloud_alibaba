package com.smart.device.access.entity.vo;

import lombok.Data;

@Data
public class CtWingVo {

    /**
     * 产品ID
     */
    private Integer productId;
    /**
     * 产品ID
     */
    private String masterKey;

    public CtWingVo() {
    }

    public CtWingVo(Integer productId, String masterKey) {
        this.productId = productId;
        this.masterKey = masterKey;
    }
}
