package com.smart.device.common.message.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 报警策略
 *
 * @author
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_strategy_alarm")
@ApiModel(value = "策略对象")
public class TStrategyAlarm {

    private Long id;
    /**
     * 设备类型
     */
    private Integer deviceType;
    @ApiModelProperty(value = "设备类型名称")
    private String deviceTypeName;
    @ApiModelProperty(value = "设备类型")
    private Integer parentType;
    /**
     * 设定策略类型
     */
    private Integer strategyType;
    @ApiModelProperty(value = "原始报警code")
    private String eventCode;
    @ApiModelProperty(value = "事件分类")
    public int parentEventCode;
    @ApiModelProperty(value = "备注")
    private String content;
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 新增时间
     */
    private LocalDateTime createTime;

    /**
     * 状态更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标识(0 正常 1删除)
     */
    private Integer deleteFlag;

    @TableField(exist = false)
    public String eventCodeName;

}
