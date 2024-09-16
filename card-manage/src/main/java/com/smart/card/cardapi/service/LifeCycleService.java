package com.smart.card.cardapi.service;

import com.smart.card.cardapi.entity.service.CardStatus;
import com.smart.card.cardapi.entity.service.RequestActive;
import com.smart.card.cardapi.entity.service.ServiceResponse;
import com.smart.card.cardapi.entity.vo.FlowParamVo;

public interface LifeCycleService {

    /**
     * 活卡激活接口
     */
    RequestActive requestServActive(FlowParamVo vo) throws Exception;

    /**
     * 停机保号/复机/测试期去激活
     */
    ServiceResponse disabledNumber(FlowParamVo vo) throws Exception;

    /**
     * 卡主状态查询接口
     */
    CardStatus queryCardMainStatus(FlowParamVo vo) throws Exception;
}
