package com.smart.card.cardapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.card.cardapi.entity.service.OffNetResponse;
import com.smart.card.cardapi.entity.service.OrderFlow;
import com.smart.card.cardapi.entity.service.OrderPay;
import com.smart.card.cardapi.entity.service.ResponseVo;
import com.smart.card.cardapi.entity.vo.FlowParamVo;
import com.smart.card.cardapi.service.BusinessAcceptService;
import com.smart.card.cardapi.service.CommonService;
import com.smart.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author junglelocal
 */
@Service
@Slf4j
public class BusinessAcceptServiceImpl implements BusinessAcceptService{

    @Resource
    private CommonService commonService;
    private final static ObjectMapper mapper = new ObjectMapper();

    @Override
    public OrderFlow orderFlow(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/app/serviceAccept.do");
        vo.setMethod("orderFlow");
        vo.setParamAdd("&flowValue=" + vo.getFlowValue());
        Object obj = commonService.getXmlCommon(vo);
        OrderFlow info;
        if (String.valueOf(obj).contains("{")) {
            ObjectMapper mapper = new ObjectMapper();
            info = mapper.readValue(String.valueOf(obj), new OrderFlow().getClass());
         }else {
            info = new OrderFlow();
            info.setMessg(String.valueOf(obj));
        }
        return info;
    }

    @Override
    public OrderFlow unsubScribe(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/app/serviceAccept.do");
        vo.setMethod("unsubScribe");
        vo.setParamAdd("&flowValue=" + vo.getFlowValue());
        Object obj = commonService.getXmlCommon(vo);
        OrderFlow info;
        if (String.valueOf(obj).contains("{")) {
            ObjectMapper mapper = new ObjectMapper();
            info = mapper.readValue(String.valueOf(obj), new OrderFlow().getClass());
        }else {
            info = new OrderFlow();
            info.setMessg(String.valueOf(obj));
        }
        return info;
    }

    @Override
    public OffNetResponse offNetAction(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("offNetAction");
        vo.setParamAdd("&action=" + vo.getAction() + "&quota=" + vo.getQuota() + "&type=" + vo.getType());
        Object obj = commonService.getXmlCommon(vo);
        OffNetResponse info;
        if (String.valueOf(obj).contains("{")) {
            ObjectMapper mapper = new ObjectMapper();
            info = mapper.readValue(String.valueOf(obj), new OffNetResponse().getClass());
        }else {
            info = new OffNetResponse();
            info.setRspDesc(String.valueOf(obj));
        }
        return info;
    }

    @Override
    public OffNetResponse imeiReRecord(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/app/serviceAccept.do");
        vo.setMethod("IMEIReRecord");
        vo.setParamAdd("&action=" + vo.getAction());
        if (StringUtils.isNotBlank(vo.getBindType())) {
            vo.setParamAdd(vo.getParamAdd() + "&bind_type=" + vo.getBindType());
        }
        if (StringUtils.isNotBlank(vo.getImei())) {
            vo.setParamAdd(vo.getParamAdd() + "&imei=" + vo.getImei());
        }
        Object obj = commonService.getXmlCommon(vo);
        OffNetResponse response;
        if (String.valueOf(obj).contains("{")) {
            ResponseVo res = mapper.readValue(String.valueOf(obj), new ResponseVo().getClass());
            response = mapper.readValue(res.getResponse(), new OffNetResponse().getClass());
            response.setGroup_transactionid(res.getGROUP_TRANSACTIONID());
        }else {
            response = new OffNetResponse();
            response.setRspDesc(String.valueOf(obj));
        }
        return response;
    }

    @Override
    public OrderPay pay(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/app/pay.do");
        vo.setMethod("pay2");
        vo.setParamAdd("&order_number=" + vo.getOrderNumber() + "&sub_bank_id=" + vo.getBankSub() +
                "&funds_type=" + vo.getFundType() + "&pay_money=" + vo.getPayMoney() +
                "&callURL=" + vo.getCallUrl() + "&callbackURL=" + vo.getCallBackUrl());
        Object obj = commonService.getXmlCommon(vo);
        OrderPay info;
        if (String.valueOf(obj).contains("{")) {
            ObjectMapper mapper = new ObjectMapper();
            info = mapper.readValue(String.valueOf(obj), new OrderPay().getClass());
        }else {
            info = new OrderPay();
            info.setMessage(String.valueOf(obj));
        }
        return info;
    }
}
