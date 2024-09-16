package com.smart.device.common.access.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* 设备类别字典
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TDeviceDict implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
    * 上行下行设备类型状态，1 上行 2下行
    */
    private Integer status;

    /**
    * 设备类别
    */
    private Integer deviceType;
    private Integer parentType;

    /**
    * 设备类别名称
    */
    private String deviceTypeName;

    /**
    * 设备型号
    */
    private String deviceModel;

    /**
    * 删除标识(0 正常 1删除)
    */
    private Integer deleteFlag;

    /**
    * 备注
    */
    private String content;

    //ctwing平台产品id
    private Integer productId;

    //ctwing平台masterKey
    private String  masterKey;

    @ApiModelProperty(value = "心跳间隔")
    private Integer  heartInterval;

}
