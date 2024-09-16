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
* 卡信息
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_card_identity")
public class TCardIdentity implements Serializable {

    private static final long serialVersionUID = 134546795400006L;

    /**
    * iccid卡号
    */

     @TableId(value = "iccid", type = IdType.INPUT)
    private Long iccid;
    /**
    * 接入号码
    */

    private Long msisdn;
    /**
    * SIM卡状态
    */

    private String status;
    /**
    * 删除实名制状态
    */

    private String infoClear;
    /**
    * 部门
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
