package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.smart.brd.manage.base.common.dict.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 家畜
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_livestock_log")
public class TLivestockLog implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */

    @TableId(value = "livestock_id", type = IdType.INPUT)
    private Long livestockId;
    /**
     * 品种
     */
    @Dict
    private Integer type;
    /**
     * 家畜类别
     */
    @Dict
    private String suitable;
    /**
     * 出生日期
     */

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
    private String shedName;
    /**
     * 栏ID
     */
    private Long bedId;
    private String bedName;
    /**
     * 入栏日期
     */

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
     * 体温
     */

    private String temperature;
    /**
     * 步数
     */

    private String steps;
    /**
     * 毛重
     */

    private String grossWeight;
    /**
     * 设备ID
     */
    private Long deviceId;
    /**
     * 耳标编号
     */
    private String deviceCode;

    /**
     * 防疫日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate preventionDate;

    /**
     * 治疗日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime treatDate;

    /**
     * 死亡日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate deadDate;

    /**
     * 逃逸日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate escapeDate;

    /**
     * 发情日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate estrusDate;

    /**
     * 交配日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate matingDate;

    /**
     * 怀孕日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate pregnancyDate;

    /**
     * 生产日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate manufactureDate;

    /**
     * 崽畜数量
     */
    private Integer pupsNum;

    /**
     * 断奶日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate weaningDate;

    /**
     * 祖代公本
     */
    private String ancestMale;

    /**
     * 祖代母本
     */
    private String ancestFemale;

    /**
     * 父母代公本
     */
    private String parentMale;

    /**
     * 父母代母本
     */
    private String parentFemale;

    /**
     * 商品代
     */
    private String commodityGeneration;

    /**
     * 新增时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 状态标识 0正常 1异常
     */
    private Integer situation;

    /**
     * 删除标识(0 正常 1删除)
     */

    private Integer deleteFlag;
    /**
     * 部门id
     */

    private Long deptId;

    /**
     * 供应商信用代码
     */
    private String creditCode;
    private String supplierName;

    @TableField(exist = false)
    public String deptIds;
    /**
     * 来源地
     */
    private String placeOrigin;
    /**
     * 状态
     */
    private String status;
    /**
     * 种类
     */
    private String varieties;
}
