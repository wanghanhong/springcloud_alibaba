package com.smart.card.manage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 主产品
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_product")
public class TProduct implements Serializable {

    private static final long serialVersionUID = 134546795400026L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 产品编号
    */

    private Long code;
    /**
    * 产品名称
    */

    private String name;
    /**
    * 产品类型
    */

    private Integer type;
    /**
    * 生效时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime effectiveDate;
    /**
    * 失效日期
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiredTime;
    /**
    * 阈值提醒比例
    */

    private Integer notifyRate;
    /**
    * 判断是否退订
    */

    private Integer unsubscribeFlag;
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
}
