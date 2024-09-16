package com.smart.message.manage.yunhu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smart.message.manage.yunhu.PhoneConstants;
import com.smart.message.manage.yunhu.common.YunHttp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


/**
 * USER: gll
 * DATE: 2020/4/28
 * TIME: 10:17
 * Describe:
 * @author l
 */
@Slf4j
@Service
public class YunHuService {
    @Resource
    private RestTemplate restTemplate;

    public JSONObject voiceNotify(String phones, String content) throws Exception {

        /**
         * 语音通知被叫号码，被叫号码可以不止一个:
         * 1)被叫号码最多可以有500个；
         * 2)号码之间用英文字符“,”隔开；
         * 号码不可重复，否则直接返回错误。
         *
         *  content	语音文本模板变量内容（使用语音文本模板时必须输入）：
         * 1）多个变量之间用英文字符“,”隔开；
         * 2）变量数必须与模板变量数一致；
         * 3）如果被叫号码不止一个，则:
         * a)如果所有号码的播放内容相同，则使用同一组变量；
         * b)如果多个号码播放内容不一致，则必须使用多组变量，每组变量之间用英文字母“;”隔开，且变量组数必须与被叫号码数相等。
         *
         */

        JSONObject obj = new JSONObject();
//        appId 应用ID
        obj.put("appId", PhoneConstants.APPID);
//        语音文本Id，上传语音文本后返回
        obj.put("textId", PhoneConstants.TEXTID);
//        发送语音通知的号码
//        obj.put("outNumber", OUTNUMBER);
//        语音通知被叫号码，被叫号码可以不止一个 to
        obj.put("to", phones);
//        obj.put("content",""content":"西安市,雁塔区,高新街道,电信大楼,18,10009309"");
        obj.put("content", content);
//        用户透传数据，回调时返回给用户，可用于认证
        obj.put("userData", phones);
        JSONObject oall = new JSONObject();
        oall.put("voiceNotify", obj);
        String voiceNotify = JSONObject.toJSONString(oall);
        String textlength = YunHttp.getLengthInteger(voiceNotify);
        HttpHeaders httpHead = YunHttp.getHeader(textlength);
        String url = PhoneConstants.VOICENOTIFY_URL.replace("s_subAccountSid", PhoneConstants.SUBACCOUNTSID).replace("s_sig", httpHead.get("sig").get(0));
        log.info("电话url--"+url);
        HttpEntity<String> httpEntity = new HttpEntity<>(voiceNotify, httpHead);
        log.info("电话httpEntity--"+JSONObject.toJSONString(httpEntity));

        ResponseEntity<String> responseEntiry = restTemplate.postForEntity(url, httpEntity, String.class);
        String body = responseEntiry.getBody();
        //解析数据
        log.info("电话--"+phones+"电话解析数据--"+body);
        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject;
    }

}
