package com.smart.brd.manage.base.entity.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart.brd.manage.base.common.dict.Dict;
import com.smart.common.core.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author jungle
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LiveStockVo extends PageDomain implements Serializable   {

    private static final long serialVersionUID = 1L;

    private Long livestockId;
    @Excel(name = "序号",orderNum = "1")

    @TableField(exist = false)
    private Long xuhao;
    /**
     * 品种
     */
    @Dict
    @Excel(name = "*品种", orderNum = "3"/*, replace = {"猪_1", "牛_2", "羊_3"}*/)
    private Integer type;

    /**
     * 家畜类别
     */
    @Dict
    @Excel(name = "*家畜类别", orderNum = "2"/*,replace = {"母猪_17","公猪_18","仔猪_19","育肥猪_20","后备母猪_21","后备公猪_22"}, width = 30*/)
    private String suitable;
    /**
     * 出生日期
     */
    @Excel(name = "*出生时间",format = "yyyy-MM-dd", orderNum = "8")
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
    @Excel(name = "*畜舍名称", orderNum = "4")
    private String shedName;
    @Excel(name = "*畜栏名称", orderNum = "5")
    private String bedName;
    /**
     * 入栏日期
     */
    @Excel(name = "入栏日期",format = "yyyy-MM-dd", orderNum = "7")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate entryDate;
    /**
     * 预出栏日期
     */
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
    @Excel(name = "*耳标编号", orderNum = "6")
    private String deviceCode;
    /**
     * 部门id
     */

    private Long deptId;

    @TableField(exist = false)
    public String deptIds;
    /**
     * 删除标识(0 正常 1删除)
     */

    private Integer deleteFlag;

    /**
     * 供应商名称
     */
    @Excel(name = "供货商名称", orderNum = "14")
    private String supplierName;

    /**
     * 供应商统一信用代码
     */
    @Excel(name = "供货商统一社会信用代码", orderNum = "15")
    private String creditCode;

    /**
     * 祖代公本
     */
    @Excel(name = "祖代公本编号", orderNum = "11")
    private String ancestMale;
    /**
     * 祖代母本
     */
    @Excel(name = "祖代母本编号", orderNum = "12")
    private String ancestFemale;
    /**
     * 父母代公本
     */
    @Excel(name = "父母代公本编号", orderNum = "9")
    private String parentMale;
    /**
     * 父母代母本
     */
    @Excel(name = "父母代母本编号", orderNum = "10")
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
    /**
     * 来源地
     */
    @Excel(name = "来源地",orderNum = "13")
    private String placeOrigin;
    /**
     * 状态
     */
    @Dict
    private String status;
    /**
     * 种类
     */
    @Dict
    private String varieties;

    private String idList;
    // 是否人工修改日期-判断和原有是否一样。
    private Integer transferType;

    /**
     * 家畜类别(用于多个类别查询时)
     */
    @Dict
    @Excel(name = "*家畜类别", orderNum = "2"/*,replace = {"母猪_17","公猪_18","仔猪_19","育肥猪_20","后备母猪_21","后备公猪_22"}, width = 30*/)
    private String suitables;

    /**
     * 耳标温度
     */

    private Float heat;

}
