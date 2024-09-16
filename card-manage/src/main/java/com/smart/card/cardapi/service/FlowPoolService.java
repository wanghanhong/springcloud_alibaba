package com.smart.card.cardapi.service;

import com.smart.card.cardapi.entity.flow.*;
import com.smart.card.cardapi.entity.vo.FlowParamVo;

import java.util.List;

public interface FlowPoolService {

    //  流量池成员列表查询接口
    List<FlowProdInfo> getPoolMemberList(FlowParamVo vo) throws Exception;
    //   流量池单个成员查询接口
    FlowProdInfo getPoolMember(FlowParamVo vo) throws Exception;
    //   流量池成员新增接口
    FlowResponse poolMemAdd(FlowParamVo vo) throws Exception;
    //  流量池充值接口
    FlowRes flowPoolPay(FlowParamVo vo) throws Exception;
    //  流量池成员删除接口
    FlowResponse poolMemDelete(FlowParamVo vo) throws Exception;
    //  流量池成员额度调整接口
    FlowResponse modifyPoolMember(FlowParamVo vo) throws Exception;
    //  流量池列表查询接口
    List<Pool> getPoolList(FlowParamVo vo) throws Exception;
    //  流量池总使用量查询接口
    PoolQuery poolQry(FlowParamVo vo) throws Exception;
    //  流量池单个成员使用量查询接口
    PoolQuery poolMemQry(FlowParamVo vo) throws Exception;

    /*****************************************************/
    //  前向流量池内各成员查询接口
    ForwardResMem forwardFlowPoolMember(FlowParamVo vo) throws Exception;
    //  前向流量池成员量本查询接口
    ForwardResPool forwardPoolQueryIot (FlowParamVo vo) throws Exception;
    //  前向流量池列表查询接口
    ForwardResList getForwardGroupNumbers(FlowParamVo vo) throws Exception;

}
