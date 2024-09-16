package com.smart.card.cardapi.controller;

import com.smart.card.cardapi.entity.prods.ProdInfo;
import com.smart.card.cardapi.entity.query.*;
import com.smart.card.cardapi.entity.vo.FlowParamVo;
import com.smart.card.cardapi.service.BusinessQueryService;
import com.smart.card.common.task.xxlJobHandler;
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
public class BusinessQueryController {

    @Resource
    private BusinessQueryService cmpapiService;
    @Resource
    private xxlJobHandler jobHandler;

    /**
     *  根据接入号查询SIM卡的余额情况，可区分用户级余额和账户级余额
     *
     *     接口名称：余额查询接口
     *     接口描述：根据接入号查询SIM卡的余额情况，可区分用户级余额和账户级余额
     *     请求方式：HTTP-GET或HTTP-POST
     *     请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     *     响应格式：XML格式
     *     调动方向：第三方客户->连接管理子系统
     *
     *
     * @param vo 参数
     * @return 参数标识 参数名称    参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	queryBalance	表示余额查询接口
     * user_id	账号	String	Y	te****st	用户名,即自管理门户账号
     * access_number或iccid	接入号码或者iccid	String	Y	149****0000	物联网接入号(149或10649号段)或iccid
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     *
     * 参数标识	参数名称	参数类型	说明
     * root	根节点	String	根节点内容
     * web:BALANCE_QRRsp	余额查询	String	区分业务为余额查询
     * ACCOUNTBALANCE	帐户级余额	String	帐户余额
     * BALANCE	当前用户余额	String	当前用户余额
     * USERBALANCE	用户级余额	String	用户级余额
     * IRESULT	处理结果代码	String	在处理正确情况下为NO_ERROR=0，否则是错误代码
     * number	查询号码	String	当前查询的号码
     * GROUP_TRANSACTIONID	流水号	String	请求流水
     *
     * 根据接入号码:
     * http://api.ct10649.com:9001/m2m_ec/query.do?method=queryBalance&access_number=149****0000&user_id=te****st&passWord=32****00&sign=03****53
     * 根据iccid:
     * http://api.ct10649.com:9001/m2m_ec/query.do?method=queryBalance&iccid=8986****4660&user_id=te****st&passWord=32****00&sign=C5****FD
     *
     *  {"iccid":"8986111827003024737"}
     */

    @PostMapping("/api/card/v1/queryBalance")
    @ResponseBody
    public Result queryBalance(@RequestBody FlowParamVo vo) {
        try {
            QueryBalance obj = cmpapiService.queryBalance(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**
     * 套餐使用量查询接口
     * 根据接入号或iccid查询SIM卡的套餐使用量情况
     *
     *     接口名称：套餐使用量查询接口
     *     接口描述：根据接入号或iccid查询SIM卡的套餐使用量情况
     *     请求方式：HTTP-GET或HTTP-POST
     *     请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     *     响应格式：XML格式
     *     调动方向：第三方客户->连接管理子系统
     *     调用频次： 500次/分钟
     *
     * 请求参数
     * 参数表示	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	queryPakage(固定值)	queryPakage表示套餐使用量查询接口
     * user_id	账号	String	Y	test	用户名,即连接管理子系统账号
     * access_number或iccid	接入号码或者iccid	String	Y	149****000	物联网接入号(149或10649号段)
     * monthDate	查询日期	String	Y	20160501	例如20160501，表示查询2016年5月份的套餐；如果不填，则为查询实时套餐
     * passWord	密码	String	Y	03****AF	user_id对应的密码经过加密之后的结果
     * sign	签名	String	Y	03A****C81	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果。
     * 响应参数
     * 参数标识	参数名称	参数类型	说明
     * root	根节点	String	根节点
     * web:CurrAcuRsp	套餐查询	String	区分业务为套餐使用量
     * CumulRspList	套餐使用记录	String	当前用户套餐使用记录
     * ACCU_NAME	套餐名称	String	例如：数据{国内}流量
     * CUMULATION_ALREADY	已使用量	String	已使用累积量
     * CUMULATION_LEFT	剩余量	String	剩余累积量
     * CUMULATION_TOTAL	总量	String	累积量总量
     * START_TIME	开始时间	String	套餐生效时间，即套餐开始计费时间
     * END_TIME	结束时间	String	结束时间，即套餐失效时间
     * OFFER_ID	订购套餐ID	String	订购套餐ID，例如2038
     * OFFER_NAME	套餐名称	String	例如：物联网（数据）月流量包非定向24元500MB（201604）
     * UNIT_NAME	单位	String	单位:KB
     * OFFER_TYPE	是否定向	String	1：定向0：非定向
     * IRESULT	处理结果代码	String	在处理正确情况下为0，否则是错误代码
     * SMSG	处理结果信息	String	在处理异常情况下为错误信息，包括错误内容提示、可能的错误原因、解决操作
     * GROUP_TRANSACTIONID	流水号	String	请求流水号
     * number	查询号码	String	当前查询的号码
     *
     * 根据接入号:
     * http://api.ct10649.com:9001/m2m_ec/query.do?method=queryPakage&user_id=test&access_number=149****000&passWord=03A****70&sign=03A****E9BE&monthDate=20160501
     * 注：monthDate表示查询账期，例如20160501，表示查询2016年5月份的账单
     * 如果monthDate这个节点不传，则查询的是当月套餐使用情况
     * 根据iccid:
     * http://api.ct10649.com:9001/m2m_ec/query.do?method=queryPakage
     * &user_id=test&iccid=89****000&passWord=03****770C&sign=03A****E9BE&monthDate=20160501
     * 注：monthDate表示查询账期，例如20160501，表示查询2016年5月份的账单
     * 如果monthDate这个节点不传，则查询的是当月套餐使用情况
     */
    // {"iccid":"8986112022101172217","monthDate":"20201201"}
    @PostMapping("/api/card/v1/queryPakage")
    @ResponseBody
    public Result queryPakage(@RequestBody FlowParamVo vo) {
        try {
            List<QueryPakage> entity = cmpapiService.queryPakage(vo);
            return Result.SUCCESS(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**
     * 欠费查询接口
     *
     *根据接入号查询SIM卡的欠费情况，可区分用户级欠费和账户级欠费
     * 接口名称：欠费查询接口
     * 接口描述：根据接入号查询SIM卡的欠费情况，可区分用户级欠费和账户级欠费
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 响应格式：XML格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     * 参数表示	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	queryOwe(固定值)	queryOwe表示欠费查询接口
     * user_id	账号	String	Y	test	用户名,即连接管理子系统账号
     * access_number	接入号码	String	Y	149****000	物联网接入号(149或10649号段)
     * Query_Flag	查询类型	String	Y	1	0:账户级欠费查询1:用户级欠费查询2：用户级和账户级欠费查询默认为1，即用户级欠费查询
     * passWord	密码	String	Y	03****AF0	user_id对应的密码经过加密之后的结果
     * sign	签名	String	Y	03A****C81	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果。
     */
    @PostMapping("/api/card/v1/queryOwe")
    @ResponseBody
    public Result queryOwe(@RequestBody FlowParamVo vo) {
        try {
            List<QueryOwe> entity = cmpapiService.queryOwe(vo);
            return Result.SUCCESS(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**
     *短信详单查询接口
     * 根据接入号查询SIM卡的短信业务使用详单，包含对端号码、发送时间等
     * 接口名称：短信详单查询接口
     * 接口描述：根据接入号查询SIM卡的短信业务使用详单，包含对端号码、发送时间等
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 响应格式：XML格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     * 参数表示	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	querySmsDetail(固定值)	querySmsDetail表示短信详单查询接口
     * user_id	账号	String	Y	test	用户名,即连接管理子系统账号
     * access_number	接入号码	String	Y	149****000	物联网接入号(149或10649号段)
     * start_time	开始时间	String	Y	查询起始时间	格式 yyyymmdd，例如：20160901
     * end_time	结束时间	String	Y	查询截止时间	格式 yyyymmdd，例如：20160909（起止时间需在一个自然月内，不可跨月跨年）
     * passWord	密码	String	Y	03****AF	user_id对应的密码经过加密之后的结果
     * sign	签名	String	Y	03A****C81	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     */
    @PostMapping("/api/card/v1/querySmsDetail")
    @ResponseBody
    public Result querySmsDetail(@RequestBody FlowParamVo vo) {
        try {
            QuerySms entity = cmpapiService.querySmsDetail(vo);
            return Result.SUCCESS(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**
     *增值详单查询接口
     * 根据接入号查询SIM卡的增值业务使用详单，包含呼叫时间、转售商代码等
     * 接口名称：增值详单查询接口
     * 接口描述：根据接入号查询SIM卡的增值详单查询接口，包含对端号码、发送时间等
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 响应格式：XML格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     * 参数表示	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	querySmsDetail(固定值)	querySmsDetail表示短信详单查询接口
     * user_id	账号	String	Y	test	用户名,即连接管理子系统账号
     * access_number	接入号码	String	Y	149****000	物联网接入号(149或10649号段)
     * start_time	开始时间	String	Y	查询起始时间	格式 yyyymmdd，例如：20160901
     * end_time	结束时间	String	Y	查询截止时间	格式 yyyymmdd，例如：20160909（起止时间需在一个自然月内，不可跨月跨年）
     * passWord	密码	String	Y	03****AF	user_id对应的密码经过加密之后的结果
     * sign	签名	String	Y	03A****C81	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     */
    @PostMapping("/api/card/v1/queryValueAddedDetail")
    @ResponseBody
    public Result queryValueAddedDetail(@RequestBody FlowParamVo vo) {
        try {
            List<ValueAddedDetail> entity = cmpapiService.queryValueAddedDetail(vo);
            return Result.SUCCESS(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**
     *订单查询接口
     *根据订单编号查询订单
     *接口名称：订单查询接口（流水号）
     *接口描述：根据订单编号查询订单
     *请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 请求参数
     *参数标识	参数名称	参数类型	是否必须	示例值	说明
     *method	接口标识	String	Y	getOrdersByRequestId	getOrdersByRequestId表示卡状态查询接口
     *user_id	账号	String	Y	test	用户名,即自管理门户账号
     *requestId	订单流水号	String	Y	1000000000000123865446	订单流水号
     * 或是access_number  接入号码	String	Y	14910000000	物联网接入号(149或10649号段)
     *pageIndex	页码	String	Y	1	分页索引。起始值为1 每页固定10条记录。 当分页索引值超过了符合条件的总页数时，返回空记录
     *passWord	密码	String	Y	03****AF0	user_id对应的密码经过加密之后的结果，调用文件des.js中的加密方法： passWord =strEnc(密码,key1,key2,key3); 注：key1,key2,key3为电信提供的9位长接口密钥值平均分为三段所形成，key1为密钥前三位，key2为密钥中间三位，key3为密钥最后三位，例如密钥值为abc123ABC，则key1=abc，key2=123，key3=ABC
     */
    @PostMapping("/api/card/v1/queryOrders")
    @ResponseBody
    public Result queryOrders(@RequestBody FlowParamVo vo) {
        try {
            QueryOrder entity = cmpapiService.getOrdersByAccessNumber(vo);
            return Result.SUCCESS(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**
     *接入号码查询接口
     *根据iccid或imsi查询物联网SIM卡号码（10649***、149***、14103***）
     *接口名称：接入号码查询接口
     * 接口描述：根据iccid或imsi查询物联网SIM卡号码（10649***、149***、14103***）
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 响应格式：JSON格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     *参数表示	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	getTelephone(固定值)	getTelephone表示接入号码查询接口
     * user_id	账号	String	Y	test	用户名,即连接管理子系统账号
     * iccid或imsi	iccid或者imsi	String	Y	898****0000	物联网卡所属iccid或imsi
     * passWord	密码	String	Y	32****00	user_id对应的密码经过加密之后的结果
     * sign	签名	String	Y	03A****C81	sign参数为接入号码、身份证号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，
     * 再通过DES加密算法加密之后所得结果。
     */
    @PostMapping("/api/card/v1/getTelephone")
    @ResponseBody
    public Result getTelephone(@RequestBody FlowParamVo vo) {
        try {
            TelephoneEntity entity = cmpapiService.getTelephone(vo);
            return Result.SUCCESS(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**
     *实名制信息查询接口
     *根据接入号查询SIM卡的实名制信息
     *接口名称：实名制信息查询接口
     * 接口描述：根据接入号查询SIM卡的实名制信息
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 响应格式：XML格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     *参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	realNameQueryIot	表示实名制信息查询接口
     * user_id	账号	String	Y	te****st	用户名,即自管理门户账号
     * access_number	接入号码	String	Y	149****0000	物联网接入号(149或10649号段)
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     */
    @PostMapping("/api/card/v1/realNameQueryIot")
    @ResponseBody
    public Result realNameQueryIot(@RequestBody FlowParamVo vo) {
        try {
            NameQueryResult entity = cmpapiService.realNameQueryIot(vo);
            return Result.SUCCESS(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**
     *三码互查接口
     *根据接入号/IMSI/ICCID中的任一，查询另外两个字段信息
     *接口名称：三码互查接口
     * 接口描述：根据接入号/IMSI/ICCID中的任一，查询另外两个字段信息
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 响应格式：JSON格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     *参数表示	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	getTelephonePlus	固定显示
     * user_id	账号	String	Y	test	调用API的客户名
     * access_number或iccid或imsi	接入号码或者iccid或imsi	String	Y	898****000	物联网卡所属接入号码或iccid或imsi
     * passWord	密码	String	Y	32****000	user_id对应的密码经过加密之后的结果
     * sign	签名	String	Y	03A****C81	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果。
     */
    @PostMapping("/api/card/v1/getTelephonePlus")
    @ResponseBody
    public Result getTelephonePlus(@RequestBody FlowParamVo vo) {
        try {
            TelephonePlusInfo entity = cmpapiService.getTelephonePlus(vo);
            return Result.SUCCESS(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**
     *接口名称：机卡绑定查询接口
     * 状态：连接超时
     * 接口描述：根据物联网接入号或iccid查询该号码机卡绑定信息
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 请求参数
     *参数表示	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	getTelephonePlus	固定显示
     * user_id	账号	String	Y	test	调用API的客户名
     * access_number或iccid	接入号码或者iccid	String	Y	898****000	物联网卡所属接入号码或iccid
     * passWord	密码	String	Y	32****000	user_id对应的密码经过加密之后的结果
     * sign	签名	String	Y	03A****C81	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果。
     */
    @PostMapping("/api/card/v1/queryBindDetection")
    @ResponseBody
    public Result queryBindDetection(@RequestBody FlowParamVo vo) {
        try {
            BindDetect entity = cmpapiService.queryBindDetection(vo);
            return Result.SUCCESS(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**
     *接口名称：机卡查询接口
     * 接口描述：该接口可协助判断物联网终端所绑定的物联网号卡信息。根据历史会话/话单信息查询最近一次物联网终端IEMI号绑定的物联网号卡信息
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 请求参数
     *参数表示	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	getTelephonePlus	固定显示
     * user_id	账号	String	Y	test	调用API的客户名
     * IMEI	物联网终端IMEI号码	String	Y	8650780493822361	物联网终端IMEI号码
     * passWord	密码	String	Y	32****000	user_id对应的密码经过加密之后的结果
     * sign	签名	String	Y	03A****C81	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果。
     */
    @PostMapping("/api/card/v1/queryImsi")
    @ResponseBody
    public Result queryImsi(@RequestBody FlowParamVo vo) {
        try {
            QueryImsi entity = cmpapiService.queryImsi(vo);
            return Result.SUCCESS(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**
     *接口名称： 流量查询(时间段)接口
     * 接口描述：根据接入号或iccid、时间段查询SIM卡流量详单明细或者总量
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 响应格式：XML格式
     * 调动方向：第三方客户->连接管理子系统
     * 调用频次 ： 500次/分钟
     * 请求参数
     *参数表示	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	queryTrafficByDate	queryTrafficByDate表示流量查询(时间段)接口
     * user_id	账号	String	Y	test	用户名,即连接管理子系统账号
     * access_number或iccid	接入号码或者iccid	String	Y	14****000	物联网接入号(149或10649号段)或iccid
     * startDate	开始时间	String	Y	查询起始时间	例如：20161001
     * endDate	结束时间	String	Y	查询截止时间	例如：20161021（截止时间与起始时间需在一个自然月内，不可跨月）
     * passWord	密码	String	Y	32****000	user_id对应的密码经过加密之后的结果
     * needDtl	是否需要明细	String	N	0	0：只返回总使用量，1：返回明细。建议使用0
     * sign	签名	String	Y	03A****C81	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果。
     */
    @PostMapping("/api/card/v1/queryTrafficByDate")
    @ResponseBody
    public Result queryTrafficByDate(@RequestBody FlowParamVo vo) {
        try {
            TrafficInfo entity = cmpapiService.queryTrafficByDate(vo);
            return Result.SUCCESS(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**
     *接口名称： 流量查询(当月)接口
     * 接口描述：根据接入号或iccid、时间段查询SIM卡流量详单明细或者总量
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 响应格式：XML格式
     * 调动方向：第三方客户->连接管理子系统
     * 调用频次 ： 500次/分钟
     * 请求参数
     *参数表示	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	queryTrafficByDate	queryTrafficByDate表示流量查询(时间段)接口
     * user_id	账号	String	Y	test	用户名,即连接管理子系统账号
     * access_number或iccid	接入号码或者iccid	String	Y	14****000	物联网接入号(149或10649号段)或iccid
     * passWord	密码	String	Y	32****000	user_id对应的密码经过加密之后的结果
     * needDtl	是否需要明细	String	N	0	0：只返回总使用量，1：返回明细。建议使用0
     * sign	签名	String	Y	03A****C81	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果。
     */
    @PostMapping("/api/card/v1/queryTraffic")
    @ResponseBody
    public Result queryTraffic(@RequestBody FlowParamVo vo) {
        try {
            TrafficInfo entity = cmpapiService.queryTraffic(vo);
            return Result.SUCCESS(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**
     *接口名称：SIM卡列表查询接口
     * 接口描述：通过接口获取所管SIM列表
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 请求参数
     *参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	getSIMList	接口名称
     * user_id	账号	String	Y	test	必选参数，即APPKEY
     * custId	客户ID	String	N	500000907329	可选参数
     * pageIndex	分页索引	String	Y	1	起始值为1，每页固定50条记录。 当分页索引值超过了符合条件的总页数时，返回空记录
     * access_number	接入号	String	N	14910000000	物联网接入号
     * iccid	iccid	String	N	8986031642100390000	iccid
     * activationTimeBegin	激活时间段：起始	String	N	20181010	格式YYYYMMDD 激活时间段都为闭区间 [activationTimeBegin, activationTimeEnd]
     * activationTimeEnd	激活时间段：截止	String	N	20181110	格式YYYYMMDD 截止时间应晚于或等于起始时间 激活时间段都为闭区间 [activationTimeBegin, activationTimeEnd]
     * simStatus	SIM卡状态	String	N	1	取值范围： 1：可激活 2：测试激活 3：测试去激活 4：在用 5：停机 6：运营商管理状态
     * groupId	群组ID	String	N	153384360	群组ID
     * passWord	密码	String	Y	32****000	user_id对应的密码经过加密之后的结果
     * sign	签名	String	Y	03A****C81	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果。
     */
    @PostMapping("/api/card/v1/getSimList")
    @ResponseBody
    public Result querySimList(@RequestBody FlowParamVo vo) {
        try {
            SimInfo entity = cmpapiService.getSimList(vo);
            return Result.SUCCESS(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**
     * 接口名称：产品资料查询接口
     * 接口描述：根据接入号查询SIM卡的产品资料，包含基础信息、套餐、状态及断网类型等
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 响应格式：XML格式
     * 调动方向：第三方客户->连接管理子系统
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * 参数表示	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	prodInstQuery(固定值)	prodInstQuery表示产品资料查询接口
     * user_id	账号	String	Y	test	用户名,即连接管理子系统账号
     * access_number	接入号码	String	Y	149****000	物联网接入号(149或10649号段)
     * passWord	密码	String	Y	03****AF	user_id对应的密码经过加密之后的结果
     * sign	签名	String	Y	03A****C81	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果。
     */
    @PostMapping("/api/card/v1/queryProducts")
    @ResponseBody
    public Result queryProducts(@RequestBody FlowParamVo vo) {
        try {
            ProdInfo entity = cmpapiService.queryProducts(vo);
            return Result.SUCCESS(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
}
