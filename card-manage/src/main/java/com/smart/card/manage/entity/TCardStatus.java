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
* 卡状态变更记录
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_card_status")
public class TCardStatus implements Serializable {

    private static final long serialVersionUID = 134546795400014L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * iccid卡号
    */

    private Long iccid;
    /**
    * 接入号
    */

    private Long msisdn;
    /**
    * 变更状态(可激活-1  测试激活-2 测试去激活-3 测试期激活-4 在用-5 停机-6)
    */

    private Integer cardStatus;
    /**
    * 卡状态名称
    */

    private String cardStatusName;
    /**
    * 修改状态时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime changeTime;
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
