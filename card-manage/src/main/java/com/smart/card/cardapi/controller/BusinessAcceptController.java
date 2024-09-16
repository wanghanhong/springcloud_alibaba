package com.smart.card.cardapi.controller;

import com.smart.card.cardapi.entity.service.OffNetResponse;
import com.smart.card.cardapi.entity.service.OrderFlow;
import com.smart.card.cardapi.entity.service.OrderPay;
import com.smart.card.cardapi.entity.vo.FlowParamVo;
import com.smart.card.cardapi.service.BusinessAcceptService;
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
public class BusinessAcceptController {

    @Resource
    private BusinessAcceptService businessAcceptService;

    /**
     *接口名称：套餐订购接口
     * 接口描述：根据物联网接入号或iccid进行套餐订购操作，如退订已订购的月加餐包、月流量包、月语音包等套餐
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/app/serviceAccept.do
     * 响应格式：JSON格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	orderFlow	表示套餐退订接口
     * user_id	账号	String	Y	te****st	用户名,即自管理门户账号
     * access_number或iccid	接入号码或者iccid	String	Y	149****0000	物联网接入号(149或10649号段)或者iccid
     * flowValue	套餐ID	String	Y	2007	参考可订购套餐列表
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     */
    @PostMapping("/api/card/v1/orderFlow")
    @ResponseBody
    public Result orderFlow(@RequestBody FlowParamVo vo) {
        try {
            OrderFlow obj = businessAcceptService.orderFlow(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }

    /**
     *接口名称：套餐退订接口
     * 接口描述：根据物联网接入号或iccid进行套餐退订操作，如退订已订购的月加餐包、月流量包、月语音包等套餐
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/app/serviceAccept.do
     * 响应格式：JSON格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	orderFlow	表示套餐退订接口
     * user_id	账号	String	Y	te****st	用户名,即自管理门户账号
     * access_number或iccid	接入号码或者iccid	String	Y	149****0000	物联网接入号(149或10649号段)或者iccid
     * flowValue	套餐ID	String	Y	2007	参考可订购套餐列表
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     */
    @PostMapping("/api/card/v1/unsubScribe")
    @ResponseBody
    public Result unsubScribe(@RequestBody FlowParamVo vo) {
        try {
            OrderFlow obj = businessAcceptService.unsubScribe(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }

    /**
     *接口名称：达量断网阈值新增、修改及取消达量断网功能接口
     * 接口描述：物联网卡默认是没有达量断网功能的，用户开卡受理时如果未统一设置达量断网功能，可以通过此接口进行达量断网断网阈值的新增、修改及取消达量断网功能操作；如果已经触发达量断网，需要恢复上网----用户级断网（type=1）：使用达量断网修改接口，调整阈值来恢复；套餐级断网（type=2）：使用套餐订购接口，订购加油包来恢复。
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 响应格式：XML格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	offNetAction	表示达量断网新增、修改及取消接口
     * user_id	账号	String	Y	te****st	用户名,即自管理门户账号
     * access_number	物联网接入号	String	Y	149****0000	物联网接入号(149或10649号段
     * action	执行动作	String	Y	1	action=1,表示新增达量断网阈值； action=2,表示修改达量断网阈值； action=3,表示取消达量断网功能。 注：已达量断网的物联网卡无法通过取消达量断网功能实现恢复上网。
     * quota	阈值	String	Y	1024	要添加或调整的断网阈值（单位：M）比如 1024， 注意：1)设为-1表示无限制 2)设为0表示有上网流量产生就会立即断网 3)只能设置为-1,0或正整数
     * type	类型	String	Y	1	type表示要添加或调整的断网类型: 设置为1:表示用户总使用量 设置为2:表示超出套餐外使用量
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     */
    @PostMapping("/api/card/v1/offNetAction")
    @ResponseBody
    public Result offNetAction(@RequestBody FlowParamVo vo) {
        try {
            OffNetResponse obj = businessAcceptService.offNetAction(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }

    /**
     *接口名称：机卡重绑接口
     * 接口描述：根据物联网接入号或iccid，对签约了机卡绑定的SIM卡进行重新绑定
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/app/serviceAccept.do
     * 请求参数
     *参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	IMEIReRecord	方法名，固定
     * user_id	账号	String	Y	test	调用API的账号
     * access_number	接入号码	String	Y	14910000000	物联网接入号(149或10649号段)
     * action	动作类型	String	Y	ADD	固定值
     * bind_type	重绑类型	String	N	2	不填，表示普通机卡重绑 填写2，表示固定机卡重绑
     * imei	设备号	String	N	865000055911181	只有在bind_typ=2时才需传入，表示固定绑定到该设备号
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     */
    @PostMapping("/api/card/v1/imeiReRecord")
    @ResponseBody
    public Result imeiReRecord(@RequestBody FlowParamVo vo) {
        try {
            OffNetResponse obj = businessAcceptService.imeiReRecord(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }

    /**
     *接口名称：三码充值接口
     * 接口描述：根据物联网接入号、iccid或imsi来对物联网预付费卡进行充值操作(充值之后钱将直接充到用户账户下)注：三码充值接口针对开卡类型为预付费的物联网卡使用，钱将直接充进用户账户
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/app/pay.do
     * 响应格式：JSON格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     * 参数表示	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	pay2 (固定值)	pay2表示三码充值接口
     * user_id	账号	String	Y	test	用户名,即连接管理子系统账号
     * access_number或iccid或imsi	接入号码或者iccid或者imsi	String	Y	149****00	物联网接入号或iccid或imsi
     * order_number	订单编号	String	Y	1025****3456	订单号生成规则：1025（4位）+yyyyMMddHHmmss（14位） +6位随机数 例如：102520190513091501123456，其中1025为固定值，20190513091501为订单生成时间（必须14位），123456为随机数（必须6位）由用户自己程序随机生成。
     * sub_bank_id	支付渠道	String	Y	ALIPAY	提供充值的银行编码: ALIPAY 支付宝 WXPAY 微信
     * funds_type	充值类型	String	Y	1	funds_type固定传1，表示给账户充值
     * pay_money	充值金额	String	Y	0.01	单位（元），可以有小数点，例如：0.01
     * callURL	地址	String	Y	http://callURL	callURL为支付成功后用来接收支付详细信息推送的地址
     * callbackURL	地址	String	Y	http://callbackURL	callbackURL为用户支付完成后想要跳回的地址
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     */
    @PostMapping("/api/card/v1/pay2")
    @ResponseBody
    public Result payCard(@RequestBody FlowParamVo vo) {
        try {
            OrderPay obj = businessAcceptService.pay(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }
}
