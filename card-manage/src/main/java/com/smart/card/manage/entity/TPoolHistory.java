package com.smart.card.manage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart.card.common.dict.dict.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author jungle
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_pool_history")
public class TPoolHistory implements Serializable {

    private static final long serialVersionUID = 134546795400020L;;
    @TableId(value = "history_id", type = IdType.INPUT)
    private Long histotyId;

    /**
     * 流量池ID
     */
    private Long id;
    /**
     *  0前向 1后向
     */
    private Integer type;
    /**
     * 客户ID
     */

    private Long ecId;
    /**
     * 客户名称
     */

    private String ecName;
    /**
     * 流量池容量
     */

    private BigDecimal allNumber;
    /**
     * 当月可用量
     */

    private String avalNum;
    /**
     * SIM卡状态
     */
    @Dict(value = "dict_type_227")
    private Long mainStatus;
    /**
     * SIM卡状态文字
     */

    private String mainStatusDesc;
    /**
     * 当月已用量
     */

    private String usedNum;
    /**
     * 当月剩余量
     */

    private String hasNum;
    /**
     * 账期
     */

    private String date;
    /**
     * 激活时间
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activeTime;
    /**
     * 失效时间
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime exporeTime;
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

    private String bindStatus;

    private String eccustAccount;

    private Integer eccustId;

    private String eproduct;

    private String groupToEccust;

    private String modeCD;

    private String offerInst;

    private Long offerinstID;

    private String prodCode;

    private Long prodInstId;

    private String prodName;

    private Integer prodNumber;

    private Integer regionId;

    private String status;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statusDate;

    private String statusDis;


    @TableField(exist = false)
    public String deptIds;
}
