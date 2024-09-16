package com.smart.message.manage.entity;

import lombok.Data;

/**
 * USER: gll
 * DATE: 2020/4/28
 * TIME: 11:27
 * Describe: 云消息实体类
 *
 * @author l
 */
@Data
public class YunBaseModel {

    /**
     * MQ消息
     */
    private String phone;

    private String smsParam;
    private String phoneParam;

    private Integer strategyType;
    /**
     * 模板参数
     */
    private String msgId;

    private String deviceCode;
}
