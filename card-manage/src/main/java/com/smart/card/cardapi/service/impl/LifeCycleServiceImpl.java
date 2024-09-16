package com.smart.card.cardapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.card.cardapi.entity.flow.FlowResponse;
import com.smart.card.cardapi.entity.service.CardStatus;
import com.smart.card.cardapi.entity.service.RequestActive;
import com.smart.card.cardapi.entity.service.ServiceResponse;
import com.smart.card.cardapi.entity.vo.FlowParamVo;
import com.smart.card.cardapi.service.CommonService;
import com.smart.card.cardapi.service.LifeCycleService;
import com.smart.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class LifeCycleServiceImpl implements LifeCycleService{

    @Resource
    private CommonService commonService;
    private final static ObjectMapper mapper = new ObjectMapper();

    @Override
    public RequestActive requestServActive(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("requestServActive");
        Object obj = commonService.getXmlCommon(vo);
        RequestActive info;
        if (String.valueOf(obj).contains("{")) {
            info = mapper.readValue(String.valueOf(obj), new RequestActive().getClass());
        }else {
            info = new RequestActive();
            info.setSmsg(String.valueOf(obj));
        }
        return info;
    }

    @Override
    public ServiceResponse disabledNumber(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("requestServActive");
        vo.setParamAdd("&acctCd=&orderTypeId=" + vo.getOrderTypeId());
        Object obj = commonService.getXmlCommon(vo);
        ServiceResponse info;
        if (String.valueOf(obj).contains("{")) {
            info = mapper.readValue(String.valueOf(obj), new ServiceResponse().getClass());
        }else {
            info = new ServiceResponse();
            info.setResultMsg(String.valueOf(obj));
        }
        return info;
    }

    @Override
    public CardStatus queryCardMainStatus(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("queryCardMainStatus");
        String t = "true";
        if (StringUtils.isNotBlank(vo.getQueryDropCard()) && t.equals(vo.getQueryDropCard())) {
            vo.setParamAdd("&queryDropCard=" + t);
        }
        Object obj = commonService.getXmlCommon(vo);
        CardStatus info;
        if (String.valueOf(obj).contains("{")) {
            info = mapper.readValue(String.valueOf(obj), new CardStatus().getClass());
        }else {
            info = new CardStatus();
            info.setResultMsg(String.valueOf(obj));
        }
        return info;
    }

}
