package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.smart.brd.manage.base.common.dict.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 告警规则
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_alarm_rules")
public class TAlarmRules implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "rule_id", type = IdType.INPUT)
    private Long ruleId;
    /**
    * 设备类型
    */
    @Dict
    private String equipmentType;
    /**
     * 家畜类别
     */
    private String suitable;
    /**
     *
     */
    private String suitableName;
    /**
     * 告警规则
     */
    private String alarmRules;
    /**
     * 最大阈值
     */
    private Double thresholdMax;
    /**
     * 最小阈值
     */
    private Double thresholdMin;
    /**
    * 触发条件
    */

    private String triggerConditions;
    /**
     * 是否启用
     */
    @Dict
    private String status;
    /**
    * 部门id
    */

    private Long deptId;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 删除标识(0 正常 1删除)
     */

    private Integer deleteFlag;

    @TableField(exist = false)
    public String deptIds;

    @TableField(exist = false)
    private List<Integer> suitableList;
    @Dict
    private String ruleType;
}
