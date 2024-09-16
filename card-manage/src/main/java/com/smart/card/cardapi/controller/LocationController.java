package com.smart.card.cardapi.controller;

import com.smart.card.cardapi.entity.location.LocationByPhone;
import com.smart.card.cardapi.entity.location.RouteLocation;
import com.smart.card.cardapi.entity.vo.FlowParamVo;
import com.smart.card.cardapi.service.LocationService;
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
public class LocationController {

    @Resource
    private LocationService locationService;

    /**
     *接口名称：基站粗定位接口
     * 接口描述：基于基站粗定位系统获取物联网卡实时的经纬度数据信息，终端必须开机附着网络下发起定位，
     * 目前支持2/3/4G及NB定位。注：物联网卡默认无定位权限，需集团开通定位白名单权限，如果需要使用定位功能
     * ，需要联系客户经理提交定位功能申请表和客户确认书；2G卡定位需要开通短信业务
     * 请求方式：HTTP-GET或HTTP-POST
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/app/location.do
     * 响应格式：JSON格式
     * 调动方向：第三方客户->连接管理子系统
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	getLocationByPhone	表示基站粗定位接口
     * user_id	账号	String	Y	te****st	用户名,即自管理门户账号
     * access_number	号码	String	Y	1064****0000	物联网接入号
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     */
    @PostMapping("/api/card/v1/getLocationByPhone")
    @ResponseBody
    public Result getLocationByPhone(@RequestBody FlowParamVo vo) {
        try {
            LocationByPhone obj = locationService.getLocationByPhone(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }

    /**
     *接口名称：历史轨迹查询接口
     * 接口描述：根据日期与用户ID查询SIM卡的历史轨迹 说明 只支持查询 当月的数据。
     * 请求地址：http://api.ct10649.com:9001/m2m_ec/query.do
     * 请求参数
     * 参数标识	参数名称	参数类型	是否必须	示例值	说明
     * method	接口标识	String	Y	queryRoutes (固定值)	queryRoutes，卡历史轨迹API
     * user_id	账号	String	Y	te****st	用户名,即自管理门户账号
     * access_number	号码	String	Y	1064****0000	物联网接入号
     * startDate	起始日期	String	Y	20200201	格式YYYYMMDD。代表待查询的结束日 只支持当月
     * endDate	终止日期	String	Y	20200206	格式YYYYMMDD。代表待查询的结束日 只支持当月
     * passWord	密码	String	Y	32****63	user_id对应的密码经过DES算法加密后的密文
     * sign	签名	String	Y	32****00	sign参数为接入号码、用户名、密码、method经过自然排序后拼接成的以逗号分隔的字符串，再通过DES加密算法加密之后所得结果
     */
    @PostMapping("/api/card/v1/queryRoutes")
    @ResponseBody
    public Result queryRoutes(@RequestBody FlowParamVo vo) {
        try {
            RouteLocation obj = locationService.queryRoutes(vo);
            return Result.SUCCESS(obj);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }
}
