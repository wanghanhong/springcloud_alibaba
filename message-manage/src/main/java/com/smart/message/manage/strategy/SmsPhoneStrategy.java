package com.smart.message.manage.strategy;


import com.smart.message.manage.common.domain.FebsResponse;
import com.smart.message.manage.entity.YunBaseModel;


/**
 * USER: gll
 * DATE: 2020/4/27
 * TIME: 13:49
 *
 * @author l
 */
public interface SmsPhoneStrategy {
    /**
     * 消息发送策略
     *
     * @param yunBaseModel
     * @return
     */
    FebsResponse yunSmsSend(YunBaseModel yunBaseModel) ;

}
