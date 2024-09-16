package com.smart.message.manage.strategy;


import com.smart.message.manage.common.domain.FebsResponse;
import com.smart.message.manage.entity.YunBaseModel;

import javax.annotation.Resource;

/**
 * USER: gll
 * DATE: 2020/4/27
 * TIME: 17:01
 * Describe:设置策略
 *
 * @author l
 */
public class SmsPhoneContext {
    @Resource
    private SmsPhoneStrategy yunSmsStrategy;

    public SmsPhoneContext(SmsPhoneStrategy yunSmsStrategy) {
        this.yunSmsStrategy = yunSmsStrategy;
    }

    public FebsResponse sendStyle(YunBaseModel yunBaseModel) {
        return yunSmsStrategy.yunSmsSend(yunBaseModel);
    }


}
