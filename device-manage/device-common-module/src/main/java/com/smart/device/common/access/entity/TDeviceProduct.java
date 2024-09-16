package com.smart.device.common.access.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 设备厂商
 *
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TDeviceProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    // 厂商名称
    private String productName;
    // 厂商编码
    private String productCode;
    // 厂商联系人
    private String contactPeople;
    // 厂商联系电话
    private String contactPhone;
    // 联系地址
    private String address;
    //    营业执照
    private String businessLicense;
    //  供应商类别
    private String productType;

    private Integer deleteFlag;
    @ApiModelProperty(value = "新增时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
