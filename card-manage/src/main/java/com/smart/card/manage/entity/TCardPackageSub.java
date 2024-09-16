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
* 卡附属套餐
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_card_package_sub")
public class TCardPackageSub implements Serializable {

    private static final long serialVersionUID = 134546795400009L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 卡ID
    */

    private Long iccid;
    private Long msisdn;
    /**
    * 套餐编号
    */

    private Long code;
    /**
    * 套餐名称
    */

    private String name;
    /**
    * 套餐编号
    */

    private String offerInstId;
    /**
    * 状态
    */

    private String status;
    /**
    * 生效日期
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime effectiveDate;
    /**
    * 失效日期
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiredDate;
    /**
    * 总量
    */

    private Double total;
    /**
    * 使用量
    */

    private Double used;
    /**
    * 使用率
    */

    private Double useRate;
    /**
    * 剩余量
    */

    private Integer remain;
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
