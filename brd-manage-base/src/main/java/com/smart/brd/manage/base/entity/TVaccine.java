package com.smart.brd.manage.base.entity;

import java.math.BigDecimal;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.smart.brd.manage.base.common.dict.Dict;
import com.smart.brd.manage.base.entity.vo.VaccinesManufacturerVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 疫苗
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_vaccine")
public class TVaccine implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */

     @TableId(value = "vaccine_id", type = IdType.INPUT)
    private Long vaccineId;
    /**
    * 疫苗名称
    */
    @Excel(name = "疫苗名称", orderNum = "1")
    private String vaccineName;
    /**
    * 疫苗编码（一商一码）
    */

    private String vaccineCode;
    /**
    * 疫苗供应商名称
    */
    @Dict
    private String supplierName;
    /**
     *生产商
     */
    private String manufacturer;
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

    private String model;
    /**
    * 单价
    */
//    @Excel(name = "单价", orderNum = "6")
//    private BigDecimal unitPrice;
    /**
     * 适用类别
     */
    private String suitable;

    /**
     * 适用类别
     */
    private String suitableName;
    /**
     * 疫苗批次
     */

    private String vaccineBatch;
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
    public String deptIds;

    @TableField(exist = false)
    private List<Integer> suitableList;

    @TableField(exist = false)
    private String startTime;
    @TableField(exist = false)
    private String endTime;
//    @TableField(exist = false)
//    private String priceMin;
//    @TableField(exist = false)
//    private String priceMax;

    /**
     * 生产商集合
     */
    @TableField(exist = false)
    private List<VaccinesManufacturerVo> vaccinesManufacturerVoList;
    /**
     * 防疫对象
     */
    @Excel(name = "预防对象", orderNum = "2")
    @Dict
    private String preventionObj;
    @Excel(name = "生产商", orderNum = "3")
    @TableField(exist = false)
    private String shengchanshang;
    @Excel(name = "供货商", orderNum = "5")
    @TableField(exist = false)
    private String gonghuoshang;
    @Excel(name = "规格", orderNum = "4")
    private String content;
}
