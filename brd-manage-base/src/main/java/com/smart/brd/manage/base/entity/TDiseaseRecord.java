package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
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
 * 疾病记录表
 *
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_disease_record")
public class TDiseaseRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
     * 畜病id
     */

    private Long diseaseId;
    /**
     * 蓄病管理id
     */
    private Long diseaseTreatId;
    /**
     * 家畜id
     */
    private Long livestockId;
    /**
     * 兽药id
     */

    private Long drugId;
    /**
     * 家畜类别
     */
    private String suitableName;
    /**
     * 症状
     */
    @Dict
    private String symptom;
    @TableField(exist = false)
    private String symptomName;
    /**
     * 剂量
     */

    private String dose;

    /**
     * 单价
     */
    private BigDecimal unitPrice;
    /**
     * 治疗时间
     */

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime treatmentTime;
    @TableField(exist = false)
    private String treatmentTimeString;
    /**
     * 治疗人姓名
     */

    private String pName;
    /**
     * 新增时间
     */

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 部门id
     */

    private Long deptId;


    @TableField(exist = false)
    public String deptIds;
    /**
     * 兽药名称
     */
    private String drugName;
    /**
     * 治疗成本
     */
    private BigDecimal treatmentCosts;


    private String diseaseName;

    /**
     *生产商名称
     */
    private String manufacturer;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     *含量单位
     */

    private String contentUnit;
}
