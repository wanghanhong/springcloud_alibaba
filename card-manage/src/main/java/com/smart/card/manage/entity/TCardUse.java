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
* 套餐使用量
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_card_use")
public class TCardUse implements Serializable {

    private static final long serialVersionUID = 134546795400015L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 卡ID
    */

    private Long iccid;
    /**
    * 接入号
    */

    private Long msisdn;
    /**
    * 用量分类
    */

    private String resourceName;
    /**
    * 套餐名称
    */

    private String packageName;
    /**
    * 套餐总量
    */

    private String total;
    /**
    * 使用量
    */

    private String usage;
    /**
    * 使用率
    */

    private String rest;
    /**
    * 剩余量
    */

    private String useRate;
    /**
    * 计费开始
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    /**
    * 计费结束
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;


    public String date;
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
    public String billDate;
    @TableField(exist = false)
    public String startDate;
    @TableField(exist = false)
    public String endDate;

}
