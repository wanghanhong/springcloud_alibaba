package com.smart.device.access.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Excel
@ApiModel(value = "批量导入信息")
public class ImportDeviceVO {
    @ExcelField(value = "设备名称")
    private String deviceName;
    private Integer deviceType;
    @ExcelField(value = "设备型号")
    private String deviceModel;
    @ExcelField(value = "移动设备识别码")
    private Long imei;
    @ExcelField(value = "移动用户识别码")
    private Long imsi;
    @ApiModelProperty(value = "厂商id")
    private Long productId;
    @ExcelField(value = "厂商名称")
    private String productName;
    @ExcelField(value = "协议")
    private String protocol;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "出厂时间")
    @ExcelField(value = "出厂时间")
    private String outFactoryTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "报废时间")
    @ExcelField(value = "报废时间")
    private String scrapTime;
    @ExcelField(value = "SN号")
    private String sn;
    @ExcelField(value = "硬件版本号")
    private String hardVersion;
    @ExcelField(value = "软件版本号")
    private String softVersion;
    @ExcelField(value = "第三方平台设备ID")
    private String thirdDeviceId;


    @ExcelField(value = "设备编号")
    private Long deviceCode;
    // 操作单位
    private Long opCompanyId;
    // 操作人
    private Long opUserId;
    private Integer parentType;
    //设备类别名称
    private String deviceTypeName;

}
