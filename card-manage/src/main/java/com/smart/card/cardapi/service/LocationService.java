package com.smart.card.cardapi.service;

import com.smart.card.cardapi.entity.location.LocationByPhone;
import com.smart.card.cardapi.entity.location.RouteLocation;
import com.smart.card.cardapi.entity.vo.FlowParamVo;

public interface LocationService {

    /**
     * 基站粗定位接口
     */
    LocationByPhone getLocationByPhone(FlowParamVo vo) throws Exception;

    /**
     * 基站粗定位接口
     */
    RouteLocation queryRoutes(FlowParamVo vo) throws Exception;
}
