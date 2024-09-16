package com.smart.message.manage.monitor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 * 语音表(SdDeviceVoice)实体类
 *
 * @author SanDuo
 * @since 2020-04-02 16:15:59
 */
@Data
public class SdDeviceVoice extends Model<SdDeviceVoice> {
    private static final long serialVersionUID = 612032254746488933L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;
    /**
    * 设备编号
    */
    private Long deviceId;
    /**
    * 0 机器人语音 1人工语音 2  短信
    */
    private Integer type;
    /**
    * 内容
    */
    private String content;
    /**
    * 语音人员
    */
    private String contactMan;
    /**
    * 发送时间
    */
    private Date sendTime;
    /**
    * 反馈时间
    */
    private Date feedbackTime;
    /**
    * 状态0失败1 成功
    */
    private Integer state;
    /**
    * 反馈结果
    */
    private String feedbackResult;
    /**
    * 添加时间
    */
    private Date addTime;
    /**
    * 备注
    */
    private String remarks;

    private String callId;
}