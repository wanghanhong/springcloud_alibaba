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
/**
* 数据详单
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_card_data")
public class TCardData implements Serializable {

    private static final long serialVersionUID = 134546795400004L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 接入号
    */

    private Long msisdn;
    /**
    * 开始时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;
    /**
    * 时长
    */

    private String duration;
    /**
    * 用量
    */

    private String traffic;
    /**
    * 网络类型
    */

    private String netWorkType;
    /**
    * 通话地点
    */

    private String location;
    /**
    * 上网内容
    */

    private String netDes;
    /**
    * APN
    */

    private String apn;
    /**
    * 数据费用
    */

    private String amount;
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
