package com.smart.card.manage.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* 卡产品资料
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TCardProductDataDto implements Serializable {

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

    public String deptIds;

    private String simStatusName;
    private Integer simStatus;
    private Long groupId;

}
