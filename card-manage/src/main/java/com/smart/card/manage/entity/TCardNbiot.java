package com.smart.card.manage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import org.springframework.format.annotation.DateTimeFormat;

/**
* 
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_card_nbiot")
public class TCardNbiot implements Serializable {

    private static final long serialVersionUID = 134546795400007L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 接入号
    */

    private Long msisdn;
    /**
    * 发送时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;
    /**
    * 接收时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiveTime;
    /**
    * 呼叫类型
    */

    private String type;
    /**
    * 费用
    */

    private String charge;
    /**
    * 部门ID
    */

    private Long deptId;
    /**
    * 创建时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
    * 删除标记
    */

    private Integer deleteFlag;


    @TableField(exist = false)
    public String deptIds;
    @TableField(exist = false)
    private String startTime;
    @TableField(exist = false)
    private String endTime;
}
