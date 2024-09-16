package com.smart.device.access.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.wuwenze.poi.annotation.Excel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@Excel
@ApiModel(value = "批量导入信息")
public class ImportBaseDeviceVO extends BaseRowModel {

    @ExcelProperty(index = 0)
    private Long imei;
    @ExcelProperty(index = 1)
    private String deviceName;

}
