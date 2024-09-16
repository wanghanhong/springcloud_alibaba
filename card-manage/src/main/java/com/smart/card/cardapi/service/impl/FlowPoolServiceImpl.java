package com.smart.card.cardapi.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.card.cardapi.entity.flow.*;
import com.smart.card.cardapi.entity.query.SimList;
import com.smart.card.cardapi.entity.vo.FlowParamVo;
import com.smart.card.cardapi.service.CommonService;
import com.smart.card.cardapi.service.FlowPoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FlowPoolServiceImpl implements FlowPoolService{

    @Resource
    private CommonService commonService;
    private final static ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<FlowProdInfo> getPoolMemberList(FlowParamVo vo) throws Exception {
        List<FlowProdInfo> res = new ArrayList<>();
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("getPoolMemberList");
        vo.setParamAdd("&poolNbr="+vo.getPoolNbr()+"&currentPage="+vo.getCurrentPage());
        Object obj = commonService.getXmlCommon(vo);
        if(String.valueOf(obj).contains("[")){
            res = mapper.readValue(String.valueOf(obj),new TypeReference<List<FlowProdInfo>>(){});
        }else{
            FlowProdInfo entity;
            if (String.valueOf(obj).contains("{")) {
                ObjectMapper mapper = new ObjectMapper();
                entity = mapper.readValue(String.valueOf(obj), new FlowProdInfo().getClass());
            }else {
                entity = new FlowProdInfo();
                entity.setState(String.valueOf(obj));
            }
            res.add(entity);
        }
        return res;
    }
    /** 2
     * 流量池单个成员查询接口
     *     member_accNbr	流量池成员号码	String	Y	1064****000	例如：1064900000000
     *     poolNbr	后向流量池号码	String	Y	50LLC00000	流量池号码，例如：50LLC00000
      */
    @Override
    public FlowProdInfo getPoolMember(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("getPoolMember");
        vo.setParamAdd("&poolNbr="+vo.getPoolNbr()+"&member_accNbr="+vo.getMember_accNbr());
        Object obj = commonService.getXmlCommon(vo);
        FlowProdInfo info;
        if (String.valueOf(obj).contains("{")) {
            info = mapper.readValue(String.valueOf(obj), new FlowProdInfo().getClass());
        }else {
            info = new FlowProdInfo();
            info.setState(String.valueOf(obj));
        }
        return info;
    }
    /** 3
     *    流量池成员新增接口
     *    poolNbr	后向流量池号码	 流量池号码，例如：50LLC00000
     * member_accNbr	流量池成员号码	 1064****000	表示新增该成员
     * flow_quota	流量池流量限额	 例如：1，只能设置为-1,0或正整数,当设置为-1时，表示无限制
     *
     */
    @Override
    public FlowResponse poolMemAdd(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("poolMemAdd");
        vo.setParamAdd("&poolNbr="+vo.getPoolNbr()+"&member_accNbr="+vo.getMember_accNbr()
        +"&flow_quota="+vo.getFlow_quota());
        Object obj = commonService.getXmlCommon(vo);
        FlowResponse info;
        if (String.valueOf(obj).contains("{")) {
            info = mapper.readValue(String.valueOf(obj), new FlowResponse().getClass());
        }else {
            info = new FlowResponse();
            info.setRspDesc(String.valueOf(obj));
        }
        return info;
    }
    /** 4
     *    流量池充值接口
     */
    @Override
    public FlowRes flowPoolPay(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("flowPoolPay");
        vo.setParamAdd("&poolNbr="+vo.getPoolNbr()+"&member_accNbr="+vo.getMember_accNbr()
                +"&charge="+vo.getCharge());
        Object obj = commonService.getXmlCommon(vo);
        FlowRes info;
        if (String.valueOf(obj).contains("{")) {
            info = mapper.readValue(String.valueOf(obj), new FlowRes().getClass());
        }else {
            info = new FlowRes();
            info.setSmsg(String.valueOf(obj));
        }
        return info;
    }
    /** 5
     *    流量池成员删除接口
     */
    @Override
    public FlowResponse poolMemDelete(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("poolMemDelete");
        vo.setParamAdd("&poolNbr="+vo.getPoolNbr()+"&member_accNbr="+vo.getMember_accNbr());
        Object obj = commonService.getXmlCommon(vo);
        FlowResponse info;
        if (String.valueOf(obj).contains("{")) {
            info = mapper.readValue(String.valueOf(obj), new FlowResponse().getClass());
          }else {
            info = new FlowResponse();
            info.setRspDesc(String.valueOf(obj));
        }
        return info;
    }
    /** 6
     *    流量池成员额度调整接口
     */
    @Override
    public FlowResponse modifyPoolMember(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("modifyPoolMember");
        vo.setParamAdd("&poolNbr="+vo.getPoolNbr()+"&member_accNbr="+vo.getMember_accNbr()
                +"&flow_quota="+vo.getFlow_quota());
        Object obj = commonService.getXmlCommon(vo);
        FlowResponse info;
        if (String.valueOf(obj).contains("{")) {
            info = mapper.readValue(String.valueOf(obj), new FlowResponse().getClass());
        }else {
            info = new FlowResponse();
            info.setRspDesc(String.valueOf(obj));
        }
        return info;
    }
    /**  7
     *    流量池列表查询接口
     */
    @Override
    public List<Pool> getPoolList(FlowParamVo vo) throws Exception {
        List<Pool> res = new ArrayList<>();
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("getPoolList");
        vo.setParamAdd("");
        Object obj = commonService.getXmlCommon(vo);
        if(String.valueOf(obj).contains("[")){
            res = mapper.readValue(String.valueOf(obj),new TypeReference<List<Pool>>(){});
        }else{
            Pool info;
            if (String.valueOf(obj).contains("{")) {
                info = mapper.readValue(String.valueOf(obj), new Pool().getClass());
            }else {
                info = new Pool();
                info.setState(String.valueOf(obj));
            }
            res.add(info);
        }
        return res;
    }
    /**   8
     *    流量池总使用量查询接口
     */
    @Override
    public PoolQuery poolQry(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("poolQry");
        vo.setParamAdd("&poolNbr="+vo.getPoolNbr());
        Object obj = commonService.getXmlCommon(vo);
        PoolQuery info;
        if (String.valueOf(obj).contains("{")) {
            info = mapper.readValue(String.valueOf(obj), new PoolQuery().getClass());
        }else {
            info = new PoolQuery();
            info.setIresult(String.valueOf(obj));
        }
        return info;
    }
    /**   9
     *    流量池单个成员使用量查询接口
     *    poolNbr	后向流量池号码	 50LLC00000	流量池号码，例如：50LLC00000
     *     member_accNbr	流量池成员号码	 1064****0000	例如：1064900000000，表示查询该成员使用量信息
     *     monthSelect	查询月份	String	 1表示当月，0表示上月
     */
    @Override
    public PoolQuery poolMemQry(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("poolMemQry");
        vo.setParamAdd("&poolNbr="+vo.getPoolNbr()+"&member_accNbr="+vo.getMember_accNbr()
                +"&monthSelect="+vo.getMonthSelect());
        Object obj = commonService.getXmlCommon(vo);
        PoolQuery info;
        if (String.valueOf(obj).contains("{")) {
            info = mapper.readValue(String.valueOf(obj), new PoolQuery().getClass());
        }else {
            info = new PoolQuery();
            info.setIresult(String.valueOf(obj));
        }
        return info;
    }


    /*****************************************************/
    /**   1
     *    前向流量池内各成员查询接口
     *    pageIndex	当前页数	String	Y	1	每页包含50条记录，页码从1开始。
     */
    @Override
    public ForwardResMem forwardFlowPoolMember(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("forwardFlowPoolMember");
        vo.setParamAdd("&pageIndex="+vo.getPageIndex());
        Object obj = commonService.getXmlCommon(vo);
        ForwardResMem info;
        if (String.valueOf(obj).contains("{")) {
            info = mapper.readValue(String.valueOf(obj), new ForwardResMem().getClass());
        }else {
            info = new ForwardResMem();
            info.setResultMsg(String.valueOf(obj));
        }
        return info;
    }

    /**   2
     *    前向流量池成员量本查询接口
     */
    @Override
    public ForwardResPool forwardPoolQueryIot(FlowParamVo vo) throws Exception {
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("forwardPoolQueryIot");
        vo.setParamAdd("&billingCycleID="+vo.getBillingCycleID());
        Object obj = commonService.getXmlCommon(vo);
        ForwardResPool info;
        if (String.valueOf(obj).contains("{")) {
            info = mapper.readValue(String.valueOf(obj), new ForwardResPool().getClass());
        }else {
            info = new ForwardResPool();
            info.setResultMsg(String.valueOf(obj));
        }
        return info;
    }
    /**   3
     *    前向流量池列表查询接口
     */
    @Override
    public ForwardResList getForwardGroupNumbers(FlowParamVo vo) throws Exception {
        List<ForwardPool> res = new ArrayList<>();
        vo.setUrl("m2m_ec/query.do");
        vo.setMethod("getForwardGroupNumbers");
        vo.setParamAdd("");
        Object obj = commonService.getXmlCommon(vo);
        ForwardResList info;
        if (String.valueOf(obj).contains("{")) {
            info = mapper.readValue(String.valueOf(obj), new ForwardResList().getClass());
        }else {
            info = new ForwardResList();
            info.setResultMsg(String.valueOf(obj));
        }
        return info;
    }


}
