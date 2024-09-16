package com.smart.card.cardapi.controller;

import com.smart.card.cardapi.entity.flow.*;
import com.smart.card.cardapi.entity.vo.FlowParamVo;
import com.smart.card.cardapi.service.FlowPoolService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

@Controller
@Slf4j
public class FlowPoolController {

    @Resource
    private FlowPoolService flowPoolService;

    /**
     * 流量池成员列表查询接口
     * 根据流量池号获取后向流量池成员列表相关属性
     *
     *     接口名称：流量池成员列表查询接口
     *     接口描述：根据流量池号获取后向流量池成员列表相关属性
     *     请求方式：HTTP-GET或HTTP-POST
     *     请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     *     响应格式：XML格式
     *     调动方向：第三方客户->连接管理子系统
     *
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	getPoolMemberList	表示流量池成员列表查询接口
     * user_id	账号	String	Y	te****st	用户名,即自管理门户账号
     * poolNbr	后向流量池号码	String	Y	50LLC00000	流量池号码，例如：50LLC00000
     * currentPage	查询页码	String	Y	1	例如1，表示第一页，每页最多50条记录
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     * 响应参数
     * 参数标识	参数名称	参数类型	说明
     * SvcCont	应答节点	String	应答消息节点
     * pageIndex	页码信息	String	例如：1，表示第一页
     * OutProdInfos	流量池成员列表信息	String	流量池成员列表信息描述
     * acc_nbr	成员号码	String	例如：106491000000
     * eff_date	生效时间	String	例如：2016/03/08 15:03:28，表示流量池成员生效时间
     * flow_quota	流量限额	String	例如：0，表示限额为0
     * org_id	成员归属地	String	例如：8320100
     * quota_type	成员限额类型	String	限额类型（1：流量，2：时长，3：条数）
     * state	成员状态	String	例如：在用
     * resultCode	结果代码&nbsp;	String	例如：0，表示查询成功
     * resultMsg	结果信息	String	例如：成功
     * totalPage	总页数(每页50条)	String	例如：360，表示总共360页
     * GROUP_TRANSACTIONID	流水号	String	请求流水
     *
     */

    @PostMapping("/api/card/v1/getPoolMemberList")
    @ResponseBody
    public Result getPoolMemberList(@RequestBody FlowParamVo vo) {
        try {
            List<FlowProdInfo> obj = flowPoolService.getPoolMemberList(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }

    /** 2
     *  流量池单个成员查询接口
     * 根据后向流量池号和成员号码获取后向流量池成员相关属性
     *
     *     接口名称：流量池单个成员查询接口
     *     接口描述：根据后向流量池号和成员号码获取后向流量池成员相关属性
     *     请求方式：HTTP-GET或HTTP-POST
     *     请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     *     响应格式：XML格式
     *     调动方向：第三方客户->连接管理子系统
     *
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	getPoolMember	表示流量池单个成员查询接口
     * user_id	账号	String	Y	te****st	用户名,即自管理门户账号
     * poolNbr	后向流量池号码	String	Y	50LLC00000	流量池号码，例如：50LLC00000
     * member_accNbr	流量池成员号码	String	Y	1064****000	例如：1064900000000
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     * 响应参数
     * 参数标识	参数名称	参数类型	说明
     * SvcCont	应答节点	String	应答消息节点
     * OutProdInfos	流量池成员列表信息	String	流量池成员列表信息描述
     * acc_nbr	成员号码	String	例如：106491000000
     * eff_date	生效时间	String	例如：2016/03/08，表示流量池成员生效时间
     * flow_quota	流量限额	String	例如:-1,表示无限制
     * org_id	成员归属地	String	例如：8320100
     * quota_type	成员限额类型	String	限额类型（1：流量，2：时长，3：条数）
     * state	成员状态	String	例如：在用
     * resultCode	结果代码&nbsp;	String	例如：0，表示查询成功
     * resultMsg	结果信息	String	例如：处理成功!
     * GROUP_TRANSACTIONID	流水号	String	请求流水
     *
     * http://api.ct10649.com:9001/m2m_ec/query.do?method=getPoolMember&user_id=te****st&passWord=03****AE&sign=03****D1&poolNbr=50LLC00000&member_accNbr=1064****0000
     *
     * <?xml   version="1.0" encoding="utf-8"?>
     * <SvcCont>
     *   <OutProdInfos>
     *       <acc_nbr>1064900000000</acc_nbr>
     *       <eff_date>2016-03-08</eff_date>
     *       <flow_quota>-1</flow_quota>
     *     <org_id>8320100</org_id>
     *       <quota_type>1</quota_type>
     *     <state>在用</state>
     *   </OutProdInfos>
     *   <resultCode>0</resultCode>
     *   <resultMsg>处理成功!</resultMsg>
     *     <GROUP_TRANSACTIONID>1000000252201606162134633100</GROUP_TRANSACTIONID>
     * </SvcCont>
     *  2
     */
    @PostMapping("/api/card/v1/getPoolMember")
    @ResponseBody
    public Result getPoolMember(@RequestBody FlowParamVo vo) {
        try {
            FlowProdInfo obj = flowPoolService.getPoolMember(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }

    /**
     * 流量池成员新增接口
     * 为后向流量池增加指定的成员号码
     *
     *     接口名称：流量池成员新增接口
     *     接口描述：为后向流量池增加指定的成员号码
     *     请求方式：HTTP-GET或HTTP-POST
     *     请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     *     响应格式：XML格式
     *     调动方向：第三方客户->连接管理子系统
     *
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	poolMemAdd	表示流量池成员新增接口
     * user_id	账号	String	Y	te****st	用户名,即自管理门户账号
     * poolNbr	后向流量池号码	String	Y	50LLC00000	流量池号码，例如：50LLC00000
     * member_accNbr	流量池成员号码	String	Y	1064****000	表示新增该成员
     * flow_quota	流量池流量限额	String	Y	1	例如：1，只能设置为-1,0或正整数,当设置为-1时，表示无限制
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     * 响应参数
     * 参数标识	参数名称	参数类型	说明
     * SvcCont	应答节点	String	应答消息节点
     * Response	应答响应	String	应答响应节点
     * RspType	请求结果标识	String	例如:0表示请求成功
     * RspCode	请求结果代码	String	例如:0000表示处理成功
     * RspDesc	请求结果说明	String	例如：成功接收消息
     * GROUP_TRANSACTIONID	流水号	String	请求流水
     *
     * http://api.ct10649.com:9001/m2m_ec/query.do?method=poolMemAdd&user_id=te****st&passWord=03****AE&sign=03****D1&poolNbr=50LLC00000&member_accNbr=1064900000000&flow_quota=1
     *
     */
    @PostMapping("/api/card/v1/poolMemAdd")
    @ResponseBody
    public Result poolMemAdd(@RequestBody FlowParamVo vo) {
        try {
            Object obj = flowPoolService.poolMemAdd(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }
    /** 流量池充值接口
     给后向流量池充流量，充值单位为GB

     接口名称：流量池充值接口
     接口描述：给后向流量池充流量，充值单位为GB
     请求方式：HTTP-GET或HTTP-POST
     请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     响应格式：XML格式
     调动方向：第三方客户->连接管理子系统

     请求参数
     参数标识	参数名称	参数类型	是否必须	示例值	说明
     method	接口标识	String	Y	flowPoolPay	表示流量池充值接口
     user_id	账号	String	Y	te****st	用户名,即自管理门户账号
     poolNbr	后向流量池号码	String	Y	50LLC00000	流量池号码，例如：50LLC00000
     charge	充值的流量值	String	Y	1	charge值只能传正整数，充值单位为GB
     passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     响应参数
     参数标识	参数名称	参数类型	说明
     SvcCont	应答节点	String	应答消息节点
     IRESULT	应答状态	String	结果状态0，表示成功
     SMSG	充值信息	String	充值信息，例如：833201606201235237812
     GROUP_TRANSACTIONID	流水号	String	请求流水
     */
    @PostMapping("/api/card/v1/flowPoolPay")
    @ResponseBody
    public Result flowPoolPay(@RequestBody FlowParamVo vo) {
        try {
            FlowRes obj = flowPoolService.flowPoolPay(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }
    /**
     *     接口名称：流量池成员删除接口
     *     接口描述：为后向流量池删除指定的成员号码
     *     请求方式：HTTP-GET或HTTP-POST
     *     请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     *     响应格式：XML格式
     *     调动方向：第三方客户->连接管理子系统
     *
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	poolMemDelete	表示流量池成员删除接口
     * user_id	账号	String	Y	te****st	用户名,即自管理门户账号
     * poolNbr	后向流量池号码	String	Y	50LLC00000	流量池号码，例如：50LLC00000
     * member_accNbr	流量池成员号码	String	Y	1064****000	表示删除该成员
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     * 响应参数
     * 参数标识	参数名称	参数类型	说明
     * SvcCont	应答节点	String	应答消息节点
     * Response	应答响应	String	应答响应节点
     * RspType	请求结果标识	String	例如:0表示请求成功
     * RspCode	请求结果代码	String	例如:0000表示处理成功
     * RspDesc	请求结果说明	String	例如：成功接收消息
     * GROUP_TRANSACTIONID	流水号	String	请求流水
     * <SvcCont>
     *   <Response>
     *     <RspType>0</RspType>
     *     <RspCode>0000</RspCode>
     *     <RspDesc>成功接收消息</RspDesc>
     *   </Response>
     *     <GROUP_TRANSACTIONID>1000000252201606163256057537</GROUP_TRANSACTIONID>
     * </SvcCont>
     */
    @PostMapping("/api/card/v1/poolMemDelete")
    @ResponseBody
    public Result poolMemDelete(@RequestBody FlowParamVo vo) {
        try {
            FlowResponse obj = flowPoolService.poolMemDelete(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }

    /**
     * 接口名称：流量池列表查询接口
     * 接口描述：查询用户后向流量池列表相关属性，返回用户名下的后向流量池列表
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 响应格式：XML格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	getPoolList	表示流量池列表查询接口
     * user_id	账号	String	Y	te****st	用户名,即自管理门户账号
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     */
    @PostMapping("/api/card/v1/getPoolList")
    @ResponseBody
    public Result getPoolList(@RequestBody FlowParamVo vo) {
        try {
            List<Pool> obj = flowPoolService.getPoolList(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }

    /**
     * 接口名称：流量池成员额度调整接口
     * 接口描述：修改后向流量池指定成员号码的流量限额
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 响应格式：XML格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	modifyPoolMember	表示流量池成员额度调整接口
     * user_id	账号	String	Y	te****st	用户名,即自管理门户账号
     * poolNbr	后向流量池号码	String	Y	50LLC00000	流量池号码，例如：50LLC00000
     * member_accNbr	流量池成员号码	String	Y	1064****000	待调整限额的成员号码
     * flow_quota	流量池流量限额	String	Y	1	例如：1，只能设置为-1,0或正整数,当设置为-1时，表示无限制
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     */
    @PostMapping("/api/card/v1/modifyPoolMember")
    @ResponseBody
    public Result modifyPoolMember(@RequestBody FlowParamVo vo) {
        try {
            FlowResponse obj = flowPoolService.modifyPoolMember(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }

    /**
     * 接口名称：流量池总使用量查询接口
     * 接口描述：查询指定后向流量池的流量总使用情况
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 响应格式：XML格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     *参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	poolQry	表示流量池总使用量查询接口
     * user_id	账号	String	Y	te****st	用户名,即自管理门户账号
     * poolNbr	后向流量池号码	String	Y	50LLC00000	流量池号码，例如：50LLC00000
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     */
    @PostMapping("/api/card/v1/poolQry")
    @ResponseBody
    public Result poolQry(@RequestBody FlowParamVo vo) {
        try {
            PoolQuery obj = flowPoolService.poolQry(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }

    /**
     * 接口名称：流量池单个成员使用量查询接口
     * 接口描述：查询后向流量池单个成员号码流量使用情况
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 响应格式：XML格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	poolMemQry	表示流量池单个成员使用量查询接口
     * user_id	账号	String	Y	te****st	用户名,即自管理门户账号
     * poolNbr	后向流量池号码	String	Y	50LLC00000	流量池号码，例如：50LLC00000
     * member_accNbr	流量池成员号码	String	Y	1064****0000	例如：1064900000000，表示查询该成员使用量信息
     * monthSelect	查询月份	String	Y	1	1表示当月，0表示上月
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     */
    @PostMapping("/api/card/v1/poolMemQry")
    @ResponseBody
    public Result poolMemQry(@RequestBody FlowParamVo vo) {
        try {
            PoolQuery obj = flowPoolService.poolMemQry(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }


    /**
     * 接口名称：前向流量池内各成员查询接口
     * 接口描述：可通过任一成员号码，查询某前向流量池下所有成员的列表
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do?
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	forwardFlowPoolMember (固定值)	forwardFlowPoolMember前向流量池成员查询
     * user_id	账号	String	Y	test	用户名,即自管理门户账号
     * access_number或iccid	接入号码或iccid	String	Y	14910000000	物联网接入号(149或10649号段)
     * pageIndex	当前页数	String	Y	1	每页包含50条记录，页码从1开始。
     * passWord	接口密码	String	Y	03A2180AB9 BF770CE0E32EE39A8AF0	user_id对应的密码经过加密之后的结果，调用文件des.js中的加密方法： passWord =strEnc(密码,'key1','key2','key3'); 注：key1,key2,key2为电信提供的9位长接口密钥值平均分为三段所形成，key1为密钥前三位，key2为密钥中间三位，key3为密钥最后三位，例如密钥值为bc123ABC，则key1=abc，key2=123，key3=ABC
     * sign	接口密钥	String	Y		sign参数为接入号码、用户名、密码、method，pageIndex经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果。
     */
    @PostMapping("/api/card/v1/forwardFlowPoolMember")
    @ResponseBody
    public Result forwardFlowPoolMember(@RequestBody FlowParamVo vo) {
        try {
            ForwardResMem obj = flowPoolService.forwardFlowPoolMember(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }
    /**
     * 接口名称：前向流量池成员量本查询接口
     * 接口描述：根据物联网接入号和账期查询前向池的月可用量、月已使用量、月剩余量和该成员的已使用量
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	forwardPoolQueryIot (固定值)	forwardFlowPoolMember前向流量池成员查询
     * user_id	账号	String	Y	test	用户名,即自管理门户账号
     * access_number或iccid	接入号码或iccid	String	Y	14910000000	流量池号码
     * billingCycleID	账期	String	Y	201801	账期
     * passWord	接口密码	String	Y	32C40A3FC633213EF9EF670D337F2000	user_id对应的密码经过加密之后的结果，调用文件des.js中的加密方法： passWord=strEnc(密码,'key1','key2','key3'); 注：key1,key2,key2为电信提供的9位长接口密钥值平均分为三段所形成，key1为密钥前三位，key2为密钥中间三位，key3为密钥最后三位，例如密钥值为bc123ABC，则key1=abc，key2=123，key3=ABC
     * sign	接口密钥	String	Y	03A3180AB9BF770CD0E95C2DE32EE39A2AF07904802C7D7BED1F13FDFDA12D58CAD0B8	sign参数为用户名、密码、method、access _accNbr、billingCycleID经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果。
     */
    @PostMapping("/api/card/v1/forwardPoolQueryIot")
    @ResponseBody
    public Result forwardPoolQueryIot(@RequestBody FlowParamVo vo) {
        try {
            ForwardResPool obj = flowPoolService.forwardPoolQueryIot(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }
    /**
     * 接口名称：前向流量池列表查询接口
     * 接口描述：查询用户前向流量池列表信息
     * 接口请求方式：HTTP-GET或HTTP-POST
     * 接口响应格式：JSON格式
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	getForwardGroupNumbers	getForwardGroupNumbers表示前向池列表查询接口
     * user_id	账号	String	Y	test	用户名,即自管理门户账号
     * passWord	接口密码	String	Y	32C40A3FC633213EF9EF670D337F2000	user_id对应的密码经过加密之后的结果，调用文件des.js中的加密方法
     * sign	签名	String	Y	03A3180AB9BF770CD0E95C2DE32EE39A2AF07904802C7D7BED1F13FDFDA1 2D58CAD0B8EB944019C81
     */
    @PostMapping("/api/card/v1/getForwardGroupNumbers")
    @ResponseBody
    public Result getForwardGroupNumbers(@RequestBody FlowParamVo vo) {
        try {
            ForwardResList obj = flowPoolService.getForwardGroupNumbers(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }

}
