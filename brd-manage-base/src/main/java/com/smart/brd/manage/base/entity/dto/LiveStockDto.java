package com.smart.brd.manage.base.entity.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart.brd.manage.base.common.dict.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LiveStockDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long livestockId;
    /**
     * 品种
     */
    @Dict
    @Excel(name = "品种 1-猪 2-牛 3-羊", orderNum = "1", replace = {"猪_1", "牛_2", "羊_3"})
    private Integer type;

    /**
     * 家畜类别
     */
    @Dict
    @Excel(name = "家畜类别", orderNum = "2", width = 30)
    private String suitable;
    /**
     * 出生日期
     */
    @Excel(name = "出生日期", orderNum = "5")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    /**
     * 养殖场ID
     */

    private Long fieldId;
    /**
     * 舍ID
     */

    private Long shedId;
    /**
     * 栏ID
     */

    private Long bedId;
    @Excel(name = "舍名", orderNum = "3")
    private String shedName;
    @Excel(name = "栏名", orderNum = "4")
    private String bedName;
    /**
     * 入栏日期
     */
    @Excel(name = "入栏日期", orderNum = "6")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate entryDate;
    /**
     * 预出栏日期
     */
    @Excel(name = "预出栏日期", orderNum = "7")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate preListingDate;
    /**
     * 转群日期
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate groupTransferDate;
    /**
     * 设备ID
     */
    private Long deviceId;
    @Excel(name = "设备编码", orderNum = "8")
    private String deviceCode;
    /**
     * 部门id
     */

    private Long deptId;

    @TableField(exist = false)
    public String deptIds;

    /**
     * 供应商名称
     */
    @Excel(name = "供应商名称", orderNum = "9")
    private String supplierName;

    /**
     * 供应商统一信用代码
     */
    @Excel(name = "供应商统一信用代码", orderNum = "10")
    private String creditCode;

    /**
     * 祖代公本
     */
    @Excel(name = "祖代公本", orderNum = "11")
    private String ancestMale;
    /**
     * 祖代母本
     */
    @Excel(name = "祖代母本", orderNum = "12")
    private String ancestFemale;
    /**
     * 父母代公本
     */
    @Excel(name = "父母代公本", orderNum = "13")
    private String parentMale;
    /**
     * 父母代母本
     */
    @Excel(name = "父母代母本", orderNum = "14")
    private String parentFemale;

    /**
     * 状态标识 0正常 1异常
     */
    private String situation;

    /**
     * 查找开始时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private String startTime;

    /**
     * 查找结束时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private String endTime;

    //  淘汰年龄
    private Integer escapeMonth;
    private Integer escapeDay;
    private String escapeAge;

}
