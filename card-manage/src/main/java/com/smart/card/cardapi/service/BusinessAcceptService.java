package com.smart.card.cardapi.service;

import com.smart.card.cardapi.entity.service.OffNetResponse;
import com.smart.card.cardapi.entity.service.OrderFlow;
import com.smart.card.cardapi.entity.service.OrderPay;
import com.smart.card.cardapi.entity.vo.FlowParamVo;

public interface BusinessAcceptService {

    /**
     * 套餐订购
     */
    OrderFlow orderFlow(FlowParamVo vo) throws Exception;

    /**
     * 套餐退订
     */
    OrderFlow unsubScribe(FlowParamVo vo) throws Exception;

    /**
     * 达量断网阈值新增、修改及取消达量断网功能接口
     */
    OffNetResponse offNetAction(FlowParamVo vo) throws Exception;

    /**
     * 机卡重绑接口
     */
    OffNetResponse imeiReRecord(FlowParamVo vo) throws Exception;

    /**
     * 三码充值接口
     */
    OrderPay pay(FlowParamVo vo) throws Exception;
}
