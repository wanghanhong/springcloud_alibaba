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
* 卡产品资料
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_card_product_data")
public class TCardProductData implements Serializable {

    private static final long serialVersionUID = 134546795400011L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * ICCID
    */

    private Long iccid;
    /**
    * 产品实例ID
    */

    private Long prodInstId;
    /**
    * 产品编号
    */

    private String prodCode;
    /**
    * 接入号
    */

    private Long msisdn;
    /**
    * SIM卡状态
    */

    private Integer cmpStatus;
    /**
    * 运营商定义状态
    */

    private String crmDic;
    /**
    * 运营商状态标识
    */

    private String crmMainStatus;
    /**
    * 状态最后变更时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastStatusChangedTime;
    /**
    * 阈值
    */

    private Integer notify;
    /**
    * 达量断网ID
    */

    private Long offnetId;
    /**
    * 达量断网产品编号
    */

    private String offnetProdCode;
    /**
    * 达量断网阈值
    */

    private String offnetThreshold;
    /**
    * 达量断网类型
    */

    private Integer offnetType;
    /**
    * 所属销售品信息
    */

    private String offerInfos;
    /**
    * 已开通功能类信息
    */

    private String funcProdInfos;
    /**
    * 标签
    */

    private String tags;
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
    * 删除标记（0-未删除  1-已删除）
    */

    private Integer deleteFlag;


    @TableField(exist = false)
    public String deptIds;
}
