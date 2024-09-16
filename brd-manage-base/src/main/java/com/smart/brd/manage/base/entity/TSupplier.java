package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
* 供应商
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_supplier")
public class TSupplier implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */

     @TableId(value = "supplier_id", type = IdType.INPUT)
    private Long supplierId;
    /**
    * 供应商名称
    */

    private String supplierName;
    /**
    * 统一社会信用代码
    */

    private String creditCode;

    /**
    * 产品名称
    */
    private String productName;

    /**
    * 注册商标
    */
    private String trademark;

    /**
    * 种类(1:设备供应商 2：饲料供应商  3：添加剂供应商 4：疫苗供应商  5：兽药供应商)
    */
    @Dict
    private Integer type;
    /**
    * 地址
    */

    private String address;
    /**
    * 联系电话
    */

    private String phone;
    /**
    * 联系人
    */

    private String contacts;
    /**
    * 电子邮箱
    */

    private String email;
    /**
    * 部门id
    */

    private Long deptid;
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
}
