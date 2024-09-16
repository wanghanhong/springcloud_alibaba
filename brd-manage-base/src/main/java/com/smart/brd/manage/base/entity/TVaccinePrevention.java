package com.smart.brd.manage.base.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.smart.brd.manage.base.common.dict.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 疫苗防疫
*
* @author
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_vaccine_prevention")
public class TVaccinePrevention implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 一疫苗防疫id
     */
    @TableId(value = "prevention_id", type = IdType.INPUT)
    private Long preventionId;
    /**
    * 畜病id
    */

    private String diseaseId;
    /**
     * 种类
     */
    @Dict
    private Integer type;
    /**
    * 畜病名称
    */

    private String diseaseName;
    /**
     * 家畜类别
     */
    private String suitable;/**
     * 家畜类别
     */
    private String suitableName;

    /**
    * 治疗日期
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate treatTime;
    /**
     * 防疫年份
     */
    private String year;
    /**
     * 防疫批次
     */

    private Integer batch;
    /**
     * 防疫对象
     */
    @Dict
    private String preventionObj;
    /**
     * 疫苗id
     */
    private Long vaccineId;
    /**
     * 疫苗名称
     */
    @TableField(exist = false)
    private String vaccineName;
    /**
     *生产商
     */
    @TableField(exist = false)
    private String manufacturer;
    /**
    * 疫苗供应商id
    */
    @TableField(exist = false)
    private Long supplierId;
    /**
    * 疫苗供应商名称
    */
    @Dict
    @TableField(exist = false)
    private String supplierName;
    /**
    * 使用剂量
    */

    private String dosage;
    /**
    * 单价
    */
//    @TableField(exist = false)
//    private BigDecimal unitPrice;
    /**
    * 花费
    */

//    private BigDecimal cost;
    /**
    * 部门id
    */
    private Long deptId;
    /**
    * 新增时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
    * 删除标识(0 正常 1删除)
    */

    private Integer deleteFlag;

    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    public String startTime;

    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    public String endTime;

    @TableField(exist = false)
    public String deptIds;
    /**
     * 某批动物的id集合
     */
    @TableField(exist = false)
    private List<String> recordList;

    @TableField(exist = false)
    private List<Integer> suitableList;
//    @TableField(exist = false)
//    private String priceMin;
//    @TableField(exist = false)
//    private String priceMax;
}
