package com.smart.card.cardapi.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.card.cardapi.entity.prods.AttrInfo;
import com.smart.card.cardapi.entity.prods.FunProdInfo;
import com.smart.card.cardapi.entity.prods.ProdInfo;
import com.smart.card.cardapi.entity.prods.ProdOfferInfo;
import com.smart.card.cardapi.entity.query.*;
import com.smart.card.cardapi.entity.vo.FlowParamVo;
import com.smart.card.cardapi.service.BusinessQueryService;
import com.smart.card.cardapi.service.CommonService;
import com.smart.common.json.JSON;
import com.smart.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author junglelocal
 */
@Service
@Slf4j
public class BusinessQueryServiceImpl implements BusinessQueryService {

    @Resource
    private CommonService commonService;
    private final static ObjectMapper mapper = new ObjectMapper();

    @Override
    public QueryBalance queryBalance(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("queryBalance");
        Object obj = commonService.getXmlCommon(vo);
        QueryBalance entity = new QueryBalance();
        if (String.valueOf(obj).contains("{")) {
            entity = mapper.readValue(JSON.marshal(obj), QueryBalance.class);
        }else {
            entity.setIresult(JSON.marshal(obj));
        }
        return entity;
    }

    @Override
    public List<QueryPakage> queryPakage(FlowParamVo vo) throws Exception {
        List<QueryPakage> res = new ArrayList<>();
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("queryPakage");
        vo.setParamAdd("&monthDate="+vo.getMonthDate());
        Object obj = commonService.getXmlCommon(vo);
        if(String.valueOf(obj).contains("[")) {
            res = mapper.readValue(String.valueOf(obj), new TypeReference<List<QueryPakage>>(){});
        }else {
            QueryPakage entity = new QueryPakage();
            if (String.valueOf(obj).contains("{")) {
                entity = mapper.readValue(String.valueOf(obj), QueryPakage.class);
            }else {
                entity.setAccu_name(String.valueOf(obj));
            }
            res.add(entity);
        }
        return res;
    }

    @Override
    public ProdInfo queryProducts(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("prodInstQuery");
        Object obj = commonService.getXmlCommon(vo);
        JSONObject prod;
        List<FunProdInfo> funProd;
        List<ProdOfferInfo> prodOffer;
        List<AttrInfo> attrInfo;
        ProdInfo entity = new ProdInfo();
        if (String.valueOf(obj).contains("{")) {
            try {
                prod = (JSONObject) obj;
            }catch (Exception e) {
                String message = String.valueOf(obj);
                entity.setProductName(message);
                return entity;
            }
            funProd = getFun(prod);
            prodOffer = getOffer(prod);
            attrInfo = getAttr(prod);
            entity = mapper.readValue(String.valueOf(obj), ProdInfo.class);
            entity.setAttrInfos(attrInfo);
            entity.setProdOfferInfos(prodOffer);
            entity.setFunProdInfos(funProd);
        }else {
            entity.setProductName(String.valueOf(obj));
        }
        return entity;
    }

    public List<FunProdInfo> getFun(JSONObject prod) throws JsonProcessingException {
        List<FunProdInfo> info = new ArrayList<>();
        JSONArray infoArray = prod.getJSONArray("funProdInfos");
        for (int i = 0; i < infoArray.length(); i++) {
            info.add(getFunAttr(infoArray.get(i)));
        }
        prod.remove("funProdInfos");
        return info;
    }

    public FunProdInfo getFunAttr(Object fun) throws JsonProcessingException {
        FunProdInfo funProdInfo;
        JSONObject funProd = (JSONObject) fun;
        List<AttrInfo> attr = new ArrayList<>();
        AttrInfo attrInfo;
        try {
            funProdInfo = mapper.readValue(String.valueOf(fun), FunProdInfo.class);
        }catch (Exception e) {
            funProdInfo = new FunProdInfo();
            funProdInfo.setProductName(funProd.getString("productName"));
            if (funProd.has("attrInfos")) {
                attrInfo = mapper.readValue(String.valueOf(funProd.get("attrInfos")),AttrInfo.class);
                attr.add(attrInfo);
                funProdInfo.setAttrInfos(attr);
            }
        }
        return funProdInfo;
    }

    public List<ProdOfferInfo> getOffer(JSONObject prod) throws JsonProcessingException {
        List<ProdOfferInfo> info = new ArrayList<>();
        ProdOfferInfo funInfo;
        try {
            info = mapper.readValue(String.valueOf(prod.get("prodOfferInfos")),new TypeReference<List<ProdOfferInfo>>(){});
        }catch (Exception e) {
            funInfo = mapper.readValue(String.valueOf(prod.get("prodOfferInfos")),ProdOfferInfo.class);
            info.add(funInfo);
        }
        prod.remove("prodOfferInfos");
        return info;
    }

    public List<AttrInfo> getAttr(JSONObject prod) throws JsonProcessingException {
        List<AttrInfo> info = new ArrayList<>();
        AttrInfo funInfo;
        try {
            info = mapper.readValue(String.valueOf(prod.get("attrInfos")),new TypeReference<List<AttrInfo>>(){});
        }catch (Exception e) {
            funInfo = mapper.readValue(String.valueOf(prod.get("attrInfos")),AttrInfo.class);
            info.add(funInfo);
        }
        prod.remove("attrInfos");
        return info;
    }

    @Override
    public List<QueryOwe> queryOwe(FlowParamVo vo) throws Exception {
        List<QueryOwe> res = new ArrayList<>();
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("queryOwe");
        vo.setParamAdd("&Query_Flag=" + vo.getQueryFlag());
        Object obj = commonService.getXmlCommon(vo);
        if(String.valueOf(obj).contains("[")) {
            res = mapper.readValue(String.valueOf(obj),new TypeReference<List<QueryOwe>>(){});
        }else {
            QueryOwe entity = new QueryOwe();
            if (String.valueOf(obj).contains("{")) {
                entity = mapper.readValue(String.valueOf(obj), QueryOwe.class);
             }else {
                entity.setAcc_nbr(String.valueOf(obj));
            }
            res.add(entity);
        }
        return res;
    }

    @Override
    public QuerySms querySmsDetail(FlowParamVo vo) throws Exception {
        List<QuerySmsDetail> res = new ArrayList<>();
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("querySmsDetail");
        vo.setParamAdd("&start_time=" + vo.getStartTime() + "&end_time=" + vo.getEndTime());
        Object obj = commonService.getXmlCommon(vo);
        QuerySms sms;
        if (String.valueOf(obj).contains("{")) {
            JsonNode node = mapper.readTree(JSON.marshal(obj));
            Object list = node.get("SM_TICKET_QRlist");
            if(String.valueOf(obj).contains("[")) {
                res = mapper.readValue(String.valueOf(obj),new TypeReference<List<QuerySmsDetail>>(){});
            }else {
                QuerySmsDetail entity;
                entity = mapper.readValue(String.valueOf(obj), QuerySmsDetail.class);
                res.add(entity);
            }
            sms = mapper.readValue(String.valueOf(obj), QuerySms.class);
            sms.setDetails(res);
        }else {
            sms = new QuerySms();
            sms.setIresult(String.valueOf(obj));
        }
        return sms;
    }

    @Override
    public List<ValueAddedDetail> queryValueAddedDetail(FlowParamVo vo) throws Exception {
        List<ValueAddedDetail> res = new ArrayList<>();
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("valueAddedDetailQuery");
        vo.setParamAdd("&start_date=" + vo.getStartTime() + "&end_date=" + vo.getEndTime());
        Object obj = commonService.getXmlCommon(vo);
        if(String.valueOf(obj).contains("[{")) {
            res = mapper.readValue(String.valueOf(obj),new TypeReference<List<ValueAddedDetail>>(){});
        }else {
            ValueAddedDetail entity = new ValueAddedDetail();
            if (String.valueOf(obj).contains("{")) {
                entity = mapper.readValue(String.valueOf(obj), ValueAddedDetail.class);
            }else {
                entity.setResult_data(String.valueOf(obj));
            }
            res.add(entity);
        }
        return res;
    }

    @Override
    public QueryOrder getOrdersByAccessNumber(FlowParamVo vo) throws Exception {
        List<SimOrderList> res = new ArrayList<>();
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("getOrdersByAccessNumber");
        if (StringUtils.isNotBlank(vo.getRequestId())) {
            vo.setMethod("getOrdersByRequestId");
            vo.setParamAdd("&requestId=" + vo.getRequestId() + "&pageIndex=" + vo.getPageIndex());
        }else {
            vo.setParamAdd("&pageIndex=" + vo.getPageIndex());
        }
        Object obj = commonService.getXmlCommon(vo);
        JsonNode node = mapper.readTree(JSON.marshal(obj));
        Object des = node.get("description");
        JsonNode desNode = mapper.readTree(String.valueOf(des));

        QueryOrder order = mapper.readValue(JSON.marshal(obj), QueryOrder.class);
        order.setPageIndex(desNode.get("pageIndex").toString());
        if (desNode.has("simOrderList")) {
            if(String.valueOf(desNode.get("simOrderList")).contains("[")) {
                res = mapper.readValue(String.valueOf(desNode.get("simOrderList")),new TypeReference<List<SimOrderList>>(){});
            }else {
                SimOrderList entity = mapper.readValue(String.valueOf(desNode.get("simOrderList")), SimOrderList.class);
                res.add(entity);
            }
            order.setSimOrderList(res);
        }
        return order;
    }

    @Override
    public TelephoneEntity getTelephone(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("getTelephone");
        if (StringUtils.isNotBlank(vo.getImsi())) {
            vo.setParamAdd("&imsi=" + vo.getImsi());
        }
        Object obj = commonService.getXmlCommon(vo);
        if (String.valueOf(obj).contains("{")) {
            return mapper.readValue(String.valueOf(obj), TelephoneEntity.class);
        }
        TelephoneEntity result = new TelephoneEntity();
        result.setResult(String.valueOf(obj));
        return result;
    }

    @Override
    public NameQueryResult realNameQueryIot(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("realNameQueryIot");
        Object obj = commonService.getXmlCommon(vo);
        if (String.valueOf(obj).contains("{")) {
            return mapper.readValue(String.valueOf(obj), NameQueryResult.class);
        }
        NameQueryResult result = new NameQueryResult();
        result.setNumber(String.valueOf(obj));
        return result;
    }

    @Override
    public TelephonePlusInfo getTelephonePlus(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("getTelephonePlus");
        if (StringUtils.isNotBlank(vo.getImsi())) {
            vo.setParamAdd("&imsi=" + vo.getImsi());
        }
        TelephonePlusInfo res;
        Object obj = commonService.getXmlCommon(vo);
        if (JSON.marshal(obj).contains("{")) {
            res = mapper.readValue(String.valueOf(obj), TelephonePlusInfo.class);

            JsonNode plusNode = mapper.readTree(String.valueOf(obj));
            String info = plusNode.get("info").toString();
            JsonNode infoNode = mapper.readTree(info);

            TelephonePlus pInfo = mapper.readValue(String.valueOf(plusNode.get("info")), TelephonePlus.class);
            pInfo.setImsi3g(String.valueOf(infoNode.get("3G_IMSI")));
            pInfo.setImsi4g(String.valueOf(infoNode.get("4G_IMSI")));
            res.setInfo(pInfo);
        }else {
            res = new TelephonePlusInfo();
            res.setResultMsg(JSON.marshal(obj));
        }
        return res;
    }

    @Override
    public BindDetect queryBindDetection(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("queryBindDetection");
        Object obj = commonService.getXmlCommon(vo);
        if (String.valueOf(obj).contains("{")) {
            return mapper.readValue(String.valueOf(obj), BindDetect.class);
        }
        BindDetect result = new BindDetect();
        result.setResultMsg(String.valueOf(obj));
        return result;
    }

    @Override
    public QueryImsi queryImsi(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("queryIMSI");
        Object obj = commonService.getXmlCommon(vo);
        if (String.valueOf(obj).contains("{")) {
            return mapper.readValue(String.valueOf(obj), QueryImsi.class);
        }
        QueryImsi result = new QueryImsi();
        result.setResultMsg(JSON.marshal(obj));
        return result;
    }

    @Override
    public TrafficInfo queryTrafficByDate(FlowParamVo vo) throws Exception {
        List<TrafficTicket> res = new ArrayList<>();
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("queryTrafficByDate");
        vo.setParamAdd("&startDate=" + vo.getStartTime() + "&endDate=" + vo.getEndTime());
        if (StringUtils.isNotBlank(vo.getNeedDtl())) {
            vo.setParamAdd(vo.getParamAdd() + "&needDtl=" + vo.getNeedDtl());
        }
        TrafficInfo info;
        Object obj = commonService.getXmlCommon(vo);
        if (String.valueOf(obj).contains("{")) {
            JsonNode trafficNode = mapper.readTree(String.valueOf(obj));
            info = mapper.readValue(String.valueOf(obj), TrafficInfo.class);
            if (trafficNode.has("NEW_DATA_TICKET_QRlist")) {
                if(trafficNode.get("NEW_DATA_TICKET_QRlist").toString().contains("[")) {
                    res = mapper.readValue(String.valueOf(trafficNode.get("NEW_DATA_TICKET_QRlist") ),new TypeReference<List<TrafficTicket>>(){});
                }else {
                    TrafficTicket entity = mapper.readValue(String.valueOf(trafficNode.get("NEW_DATA_TICKET_QRlist")), TrafficTicket.class);
                    res.add(entity);
                }
                info.setTicket_list(res);
            }
        }else {
            info = new TrafficInfo();
            info.setResultMsg(String.valueOf(obj));
        }
        return info;
    }

    @Override
    public TrafficInfo queryTraffic(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("&ldquo;queryTraffic");
        if (StringUtils.isNotBlank(vo.getNeedDtl())) {
            vo.setParamAdd(vo.getParamAdd() + "&needDtl=" + vo.getNeedDtl());
        }
        Object obj = commonService.getXmlCommon(vo);
        if (String.valueOf(obj).contains("{")) {
            return mapper.readValue(String.valueOf(obj), TrafficInfo.class);
        }
        TrafficInfo result = new TrafficInfo();
        result.setResultMsg(String.valueOf(obj));
        return result;
    }

    @Override
    public SimInfo getSimList(FlowParamVo vo) throws Exception {
        List<SimList> res = new ArrayList<>();
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("getSIMList");
        vo.setParamAdd("&pageIndex=" + vo.getPageIndex());
        if (StringUtils.isNotBlank(vo.getCustId())) {
            vo.setParamAdd(vo.getParamAdd() + "&requestId=" + vo.getCustId());
        }
        if (StringUtils.isNotBlank(vo.getStartTime())) {
            vo.setParamAdd(vo.getParamAdd() + "&activationTimeBegin=" + vo.getCustId());
        }
        if (StringUtils.isNotBlank(vo.getEndTime())) {
            vo.setParamAdd(vo.getParamAdd() + "&activationTimeEnd=" + vo.getCustId());
        }
        if (StringUtils.isNotBlank(vo.getSimStatus())) {
            vo.setParamAdd(vo.getParamAdd() + "&simStatus=" + vo.getCustId());
        }
        if (StringUtils.isNotBlank(vo.getGroupId())) {
            vo.setParamAdd(vo.getParamAdd() + "&groupId=" + vo.getCustId());
        }
        Object obj = commonService.getXmlCommon(vo);

        JsonNode webNode = mapper.readTree(JSON.marshal(obj));
        JsonNode desNode = mapper.readTree(String.valueOf(webNode.get("description")));

        SimInfo order = mapper.readValue(JSON.marshal(obj), SimInfo.class);
        if (desNode != null) {
            order.setPageIndex(desNode.get("pageIndex").toString());
            if (desNode.has("simList")) {
                if(desNode.get("simList").toString().contains("[")) {
                    res = mapper.readValue(String.valueOf(desNode.get("simList") ),new TypeReference<List<SimList>>(){});
                }else {
                    SimList entity = mapper.readValue(String.valueOf(desNode.get("simList") ), SimList.class);
                    res.add(entity);
                }
                order.setSimList(res);
            }
        }
        return order;
    }

}
