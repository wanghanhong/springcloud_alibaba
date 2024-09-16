package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.smart.brd.manage.base.common.dict.Dict;
import com.smart.brd.manage.base.entity.vo.DrugDoseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

/**
 * 畜病治疗表
 *
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_disease_treat")
public class TDiseaseTreat implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
     * 个体编号（一畜一标）
     */

    private Long livestockId;
    /**
     * 畜病id
     */

    private Long diseaseId;
    /**
     * 畜病名称
     */
    private String diseaseName;
    /**
     * 症状
     */
    @Dict
    private String symptom;
    /**
     * 治疗日期
     */

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDateTime treatDate;
    @TableField(exist = false)
    private String treatDateString;
    /**
     * 品种
     */

    private String type;
    /**
     * 兽药ID
     */

    private Long drugId;
    /**
     * 兽药
     */

    private String drugName;
    /**
     * 剂量
     */
    private String dose;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 部门id
     */

    private Long deptId;

    /**
     * 新增时间
     */

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 删除标识(0 正常 1删除)
     */

    private Integer deleteFlag;


    @TableField(exist = false)
    public String deptIds;

    /**
     * 治疗状态：0： 未康复 1：已康复 2： 死亡
     */
    @Dict
    private String treatmentState;
    /**
     * 舍id
     */
    @TableField(exist = false)
    private String shedId;
    /**
     * 舍
     */
    @TableField(exist = false)
    private String shedName;
    /**
     * 栏id
     */
    @TableField(exist = false)
    private String bedId;
    /**
     * 栏
     */
    @TableField(exist = false)
    private String bedName;
    /**
     * 家畜类别
     */
    private String suitableName;
    /**
     * 家畜类别
     */
    @TableField(exist = false)
    private String suitable;
    /**
     * 耳标
     */
    private String deviceCode;
    @TableField(exist = false)
    private LocalDate deathDate;

    /**
     * 入栏日期
     */
    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDate entryDate;
    /**
     * 出生日期
     */
    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    /**
     * 开始时间
     */
    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private String startTime;
    /**
     * 结束时间
     */
    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private String endTime;
    /**
     * 兽药集合
     */
    @TableField(exist = false)
    private List <DrugDoseVo> tDrugList;
    /**
     * 治疗成本区间
     */
    /*注释价格相关*/
    /*@TableField(exist = false)
    private Integer minTreatmentCostsSum;
    *//**
     * 治疗成本区间
     *//*
    @TableField(exist = false)
    private Integer maxTreatmentCostsSum;
    *//**
     * 治疗成本和
     *//*
    @TableField(exist = false)
    private BigDecimal treatmentCostsSum;*/
    /**
     * 治疗记录
     */
    @TableField(exist = false)
    List <TDiseaseRecord> tDiseaseRecordList;
    /**
     * dictId
     */
    @TableField(exist = false)
    private Integer dictId;
    /**
     * dictName
     */
    @TableField(exist = false)
    private String dictName;
}
