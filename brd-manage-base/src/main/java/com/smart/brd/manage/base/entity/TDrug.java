package com.smart.brd.manage.base.entity;

import java.math.BigDecimal;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;
import com.smart.brd.manage.base.common.dict.Dict;
import com.smart.brd.manage.base.entity.vo.DrugManufacturerVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 兽药
*
* @author
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_drug")
public class TDrug implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */

    @TableId(value = "drug_id", type = IdType.INPUT)
    private Long drugId;
    /**
    * 兽药名称
    */
    @Excel(name = "兽药名称",orderNum = "1")
    private String drugName;
    /**
     * 兽药功效
     */
    @Excel(name = "兽药功效",orderNum = "2")
    private String effect;
    /**
    * 兽药编码（一商一码）
    */

    private String drugCode;
    /**
     *生产商名称
     */
    @Excel(name = "生产商",orderNum = "3")
    private String manufacturer;
    /**
    * 供应商ID
    */

    private Long supplierId;
    /**
    * 供应商名称
    */
    @Dict
    @Excel(name = "供货商",orderNum = "5")
    private String supplierName;
    /**
    * 注册商标
    */

    private String trademark;
    /**
    * 种类
    */
    @Dict
    private Integer type;
    /**
    * 规格或型号
    */
    @Excel(name = "产品规格",orderNum = "4")
    private String model;
    /**
     * 总价
     */
    /*注释价格相关*/
    //private BigDecimal  price;
    /**
    * 单价
    */

    //private BigDecimal unitPrice;

    //@TableField(exist = false)
    //private BigDecimal unitPriceMin;
    //@TableField(exist = false)
    //private BigDecimal unitPriceMax;
    /**
     *含量单位
     */

    private String contentUnit;
    /**
     *含量数量
     */

    private Double contentNum;
    /**
     * 兽药批号
     */
    private String drugBatch;
    /**
     * 适用家畜
     */
    private String suitable;
    /**
     * 适用家畜
     */
    private String suitableName;
    /**
    * 部门id
    */

    private Long deptId;
    /**
    * 新增时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    public String startTime;

    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    public String endTime;
    /**
    * 删除标识(0 正常 1删除)
    */

    private Integer deleteFlag;


    @TableField(exist = false)
    public String deptIds;


    @TableField(exist = false)
    private List<Integer> suitableList;

    /**
     * 剂量
     */
    @TableField(exist = false)
    private String dose;
    /**
     * 生产商集合
     */
    @TableField(exist = false)
    List<DrugManufacturerVo> drugManufacturerVoList;
    /**
     *疾病名称集合
     */
    private String diseasesName;
    /**
     * 疾病id集合
     */
    private String diseases;
    @TableField(exist = false)
    private String disease;
    /**
     * 疾病id集合
     */
    @TableField(exist = false)
    private List<String> diseaseId;
    /**
     * 通过疾病查询相关药物
     */
    @TableField(exist = false)
    private String byDisease;
    /**
     * 总价区间
     */
    /*注释价格相关*/
    //@TableField(exist = false)
    //private BigDecimal priceMin;
    /**
     * 总价区间
     */
    /*注释价格相关*/
    //@TableField(exist = false)
   //private BigDecimal priceMax;
}
