package com.smart.card.cardapi.service;

import com.smart.card.cardapi.entity.prods.ProdInfo;
import com.smart.card.cardapi.entity.query.*;
import com.smart.card.cardapi.entity.vo.FlowParamVo;

import java.util.List;

public interface BusinessQueryService {

     /**
     * 余额查询接口
     * @param vo 参数
     */
     QueryBalance queryBalance(FlowParamVo vo) throws Exception;

    /**
     * 套餐使用量查询接口
     */
    List<QueryPakage> queryPakage(FlowParamVo vo) throws Exception;


    /**
     * 产品资料量查询接口
     */
    ProdInfo queryProducts(FlowParamVo vo) throws Exception;

    /**
     * 欠费查询接口
     */
    List<QueryOwe> queryOwe(FlowParamVo vo) throws Exception;

    /**
     * 短信详单查询接口
     */
    QuerySms querySmsDetail(FlowParamVo vo) throws Exception;

    /**
     * 增值详单查询接口
     */
    List<ValueAddedDetail> queryValueAddedDetail(FlowParamVo vo) throws Exception;

    /**
     * 订单查询接口
     */
    QueryOrder getOrdersByAccessNumber(FlowParamVo vo) throws Exception;

    /**
     * 接入号码查询接口
     */
    TelephoneEntity getTelephone(FlowParamVo vo) throws Exception;

    /**
     * 实名制信息查询接口
     */
    NameQueryResult realNameQueryIot(FlowParamVo vo) throws Exception;

    /**
     * 三码互查接口
     */
    TelephonePlusInfo getTelephonePlus(FlowParamVo vo) throws Exception;

    /**
     * 机卡绑定查询接口
     */
    BindDetect queryBindDetection(FlowParamVo vo) throws Exception;

    /**
     * 机卡查询接口
     */
    QueryImsi queryImsi(FlowParamVo vo) throws Exception;

    /**
     * 流量查询(时间段)接口
     */
    TrafficInfo queryTrafficByDate(FlowParamVo vo) throws Exception;

    /**
     * 流量查询(当月)接口
     */
    TrafficInfo queryTraffic(FlowParamVo vo) throws Exception;

    /**
     * SIM卡列表查询接口
     */
    SimInfo getSimList(FlowParamVo vo) throws Exception;
}
