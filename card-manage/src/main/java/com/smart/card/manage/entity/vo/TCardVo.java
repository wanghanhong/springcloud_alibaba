package com.smart.card.manage.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
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
public class TCardVo implements Serializable {

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
     * 卡状态(可激活-1  测试激活-2 测试去激活-3 测试期激活-4 在用-5 停机-6)
     */

    private Integer simStatus;
    /**
     * 卡状态名称
     */

    private String simStatusName;
    /**
     * 客户名称
     */

    private String custName;
    /**
     * 客户编码
     */

    private Long custCode;
    /**
     * 断网状态 （1-未断网  2-已断网）
     */

    private Integer offline;
    /**
     * 断网类型
     */

    private String offlineType;
    /**
     * 激活时间
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activateDate;
    /**
     * 4GIMSI
     */

    private String imsi4g;
    /**
     * 3GIMSI
     */

    private String imsi3g;
    /**
     * 是否属于流量池
     */

    private String isPoolMember;
    /**
     * 流量池类型
     */
    private String idNumber;
    private String idName;

    private String ownedPoolType;
    private Long ownedPoolNumber;
    /**
     * 用户发展时间
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime joinDate;
    /**
     * 主产品名称
     */

    private String productName;
    /**
     * 主产品ID
     */

    private Long productId;
    /**
     * 群组ID
     */

    private Long groupId;
    /**
     * 群组名称
     */

    private String groupName;
    private String tags;
    /**
     * 最后一次变更时间
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime changeTime;
    /**
     * 部门
     */

    private Long deptId;

    /**
     * 归属地
     */
    private String registrationLocation;

    /**
     * 认证状态
     */
    private String verified;

    /**
     * 运营商定义状态
     */
    private String operatorDefinitionStatus;

    /**
     * 新增时间
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 删除标识(0 正常 1删除)
     */

    private Integer deleteFlag;

    /**
     * 余额
     */
    private String balance;

    /**
     * 用户级余额
     */
    private String userBalance;

    /**
     * 账户级余额
     */
    private String accountBalance;

    /**
     *
     */
    private String arrearsAmount;

    /**
     * 数据用量
     */
    private String dataUse;

    /**
     * 短信用量
     */
    private String smsUse;

    private String deptIds;
}
