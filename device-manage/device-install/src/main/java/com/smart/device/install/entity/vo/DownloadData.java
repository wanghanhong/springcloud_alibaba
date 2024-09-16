package com.smart.device.install.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class DownloadData extends BaseRowModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = {"巡检报告单", "巡检批次"}, index = 0)
    private Long batch;

    @ExcelProperty(value = {"巡检报告单", "单位名称"}, index = 1)
    private String companyName;

    @ExcelProperty(value = {"巡检报告单", "巡检联系人电话"}, index = 2)
    private String fireFighterPhone;

    @ExcelProperty(value = {"巡检报告单", "巡检人"}, index = 3)
    private String firefighterName;

    @ExcelProperty(value = {"巡检报告单", "巡检地址"}, index = 4)
    private String location;

    @ExcelProperty(value = {"巡检报告单", "巡检周期"}, index = 5)
    private Integer period;

    private String deviceName;

    private String buildingName;
    // 巡检周期

    @ExcelProperty(value = "序号", index = 0)
    private Integer pointNum;

    @ExcelProperty(value = "序号", index = 0)
    private Integer pointRepair;

    @ExcelProperty(value = "序号", index = 0)
    private Integer pointReplace;

    @ExcelProperty(value = "序号", index = 0)
    private Integer pointLost;

    @ExcelProperty(value = "序号", index = 0)
    private String deviceQuestion;

    @ExcelProperty(value = "序号", index = 0)
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }


}
