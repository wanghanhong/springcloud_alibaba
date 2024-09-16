package com.smart.card.cardapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.card.cardapi.entity.location.LocationByPhone;
import com.smart.card.cardapi.entity.location.RouteLocation;
import com.smart.card.cardapi.entity.vo.FlowParamVo;
import com.smart.card.cardapi.service.CommonService;
import com.smart.card.cardapi.service.LocationService;
import com.smart.card.cardapi.util.CtAccountService;
import com.smart.card.cardapi.util.DesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author junglelocal
 */
@Service
@Slf4j
public class LocationServiceImpl implements LocationService{

    @Resource
    private CommonService commonService;
    @Resource
    private CtAccountService ctAccountService;
    private final static ObjectMapper mapper = new ObjectMapper();

    @Override
    public LocationByPhone getLocationByPhone(FlowParamVo vo) throws Exception {
        String aSecret = ctAccountService.queryAccount().getASecret();
        vo.setUrl("m2m_ec/app/location.do");
        vo.setMethod("getLocationByPhone");
        Object obj = commonService.getXmlCommon(vo);
        String str = String.valueOf(obj);
        String key1 = aSecret.substring(0,3);
        String key2 = aSecret.substring(3,6);
        String key3 = aSecret.substring(6,9);
        String des = DesUtils.strDec(str, key1, key2, key3);
        return mapper.readValue(des, new LocationByPhone().getClass());
    }

    @Override
    public RouteLocation queryRoutes(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("queryRoutes");
        vo.setParamAdd("&startDate=" + vo.getStartTime() + "&endDate=" + vo.getEndTime());
        Object obj = commonService.getXmlCommon(vo);
        return mapper.readValue(String.valueOf(obj), new RouteLocation().getClass());
    }

}
