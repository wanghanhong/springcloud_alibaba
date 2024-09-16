package com.smart.device.common.access.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 设备协议
 *
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TDeviceProtocol implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 协议名称
     */
    private String protocolName;
    private String protocolCode;
    /**
     * 协议类型
     */
    private String protocolType;

    /**
     * 删除标识(0 正常 1删除)
     */
    private Integer deleteFlag;

    /**
     * 备注
     */
    private String content;


}
