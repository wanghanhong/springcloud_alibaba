package com.smart.card.cardapi.controller;

import com.smart.card.cardapi.entity.service.CardStatus;
import com.smart.card.cardapi.entity.service.RequestActive;
import com.smart.card.cardapi.entity.service.ServiceResponse;
import com.smart.card.cardapi.entity.vo.FlowParamVo;
import com.smart.card.cardapi.service.LifeCycleService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author junglelocal
 */
@Controller
@Slf4j
public class LifeCycleController {
    @Resource
    private LifeCycleService lifeCycleService;

    /**
     * 接口名称：活卡激活接口
     * 接口描述：根据物联网接入号或iccid对二次激活的卡进行二次激活(强制激活)，强制激活后即开始计费，该操作是不可逆的，仅需调用一次激活接口即可，卡激活成功之后无需再调用此接口。如果频繁调用该接口，则会提示 {
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 响应格式：JSON格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     * 参数表示	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	requestServActive(固定值)	requestServActive表示活卡激活接口
     * user_id	账号	String	Y	test	用户名,即连接管理子系统账号
     * access_number	接入号码	String	Y	149****000	物联网接入号(149或10649号段)
     * passWord	密码	String	Y	03****AF	user_id对应的密码经过加密之后的结果
     * sign	签名	String	Y	03A****C81	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果。
     */
    @PostMapping("/api/card/v1/requestServActive")
    @ResponseBody
    public Result requestServActive(@RequestBody FlowParamVo vo) {
        try {
            RequestActive obj = lifeCycleService.requestServActive(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }

    /**
     * 接口名称：停机保号/复机/测试期去激活
     * 接口描述：根据物联网接入号对物联网卡进行停机保号、复机、测试期去激活等操作。
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 响应格式：XML格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     * 参数表示	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	disabledNumber(固定值)	disabledNumber表示停机保号相关接口
     * user_id	账号	String	Y	test	用户名,即连接管理子系统户账号
     * access_number	接入号码	String	Y	14****000	物联网接入号(149或10649号段)
     * acctCd	固定字段	String	Y	保持为空	固定字段，保持为空
     * orderTypeId	类型	String	Y	停机保号类型	19表示停机保号，20表示停机保号后复机,21表示测试期去激活，22表示测试期去激活后回到测试激活
     * passWord	密码	String	Y	03****AF	user_id对应的密码经过加密之后的结果
     * sign	签名	String	Y	03A****C81	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果。
     */
    @PostMapping("/api/card/v1/disabledNumber")
    @ResponseBody
    public Result disabledNumber(@RequestBody FlowParamVo vo) {
        try {
            ServiceResponse obj = lifeCycleService.disabledNumber(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }

    /**
     * 接口名称：卡主状态查询接口
     * 接口描述：根据接入号查询SIM卡处于生命周期的状态
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 请求方式： HTTP-GET或HTTP-POST
     * 响应格式： JSON格式
     * 调动方向： 第三方客户->连接管理子系统
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	queryCardMainStatus	方法名，固定
     * user_id	账号	String	Y	test	调用API的账号
     * access_number	接入号码	String	Y	14910000000	物联网接入号(149或10649号段)
     * queryDropCard	是否需要查询拆机状态	String	N	true	若该字段存在且传值为true，则查询拆机状态，否则不查询拆机
     */
    @PostMapping("/api/card/v1/queryCardMainStatus")
    @ResponseBody
    public Result queryCardMainStatus(@RequestBody FlowParamVo vo) {
        try {
            CardStatus obj = lifeCycleService.queryCardMainStatus(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }
}
