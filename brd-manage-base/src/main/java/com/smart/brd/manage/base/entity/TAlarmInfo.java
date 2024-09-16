package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.smart.brd.manage.base.common.dict.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 告警信息
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_alarm_info")
public class TAlarmInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 告警类别
    */
    @Dict
    private String alarmCategory;
    /**
     * 舍ID
     */
    private Long shedId;
    /**
     *种类
     */
    @Dict
    private Integer type;
    /**
     * 舍名称
     */
    private String shedName;
    /**
     * 栏id
     */
    private Long bedId;
    /**
     * 栏名称
     */
    private String bedName;
    /**
    * 告警时间
    */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime alarmTime;
    /**
    * 处理时间
    */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime processingTime;
    /**
    * 告警设备类型
    */
    @Dict
    private String equipmentType;
    /**
    * 设备编号
    */
    private String equipmentNumber;
    /**
    * 告警内容
    */
    private String alarmContent;
    /**
    * 处理状态 （0：未处理  1：已处理）
    */
    @Dict
    private Integer eventStatus;
    /**
    * 处理人
    */
    private String handler;
    /**
    * 联系方式
    */
    private String contactInformation;
    /**
    * 处理内容
    */
    @Dict
    private String treatmentResults;
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

    /**
     * 家畜ID
     */
    private Long livestockId;

    @TableField(exist = false)
    public String deptIds;

    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    public String startTime;

    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    public String endTime;
}
