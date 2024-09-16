package com.smart.message.manage.strategy.complex;

import com.smart.message.manage.common.domain.FebsResponse;
import com.smart.message.manage.entity.YunBaseModel;
import com.smart.message.manage.strategy.SmsPhoneStrategy;
import com.smart.message.manage.strategy.service.PhoneStrategyImp;
import com.smart.message.manage.strategy.service.SmsStrategyImp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * USER: gll
 * DATE: 2020/4/27
 * TIME: 14:26
 * Describe:呼叫,短信复合接口
 *
 * @author l
 */
@Service
public class SmsPhoneStrategyImp implements SmsPhoneStrategy {

    @Resource
    private SmsStrategyImp smsStrategyImp;
    @Resource
    private PhoneStrategyImp phoneStrategyImp;

    @Override
    public FebsResponse yunSmsSend(YunBaseModel yunBaseModel) {

        FebsResponse febsResponse = new FebsResponse();
        try {
            if(yunBaseModel != null && yunBaseModel.getStrategyType() != null){
                if(yunBaseModel.getStrategyType() == 0){
                    febsResponse = smsStrategyImp.yunSmsSend(yunBaseModel);
                }else if(yunBaseModel.getStrategyType() == 1 || yunBaseModel.getStrategyType() == 2){
                    febsResponse = smsStrategyImp.yunSmsSend(yunBaseModel);
                    febsResponse = phoneStrategyImp.yunSmsSend(yunBaseModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return febsResponse;
    }

}
