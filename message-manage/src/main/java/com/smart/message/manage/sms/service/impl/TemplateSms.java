package com.smart.message.manage.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.smart.message.manage.common.utils.DateUtil;
import com.smart.message.manage.sms.SmsConstants;
import com.smart.message.manage.sms.service.SmsTemplate;
import com.smart.message.manage.sms.service.TemplateInter;
import com.smart.message.manage.sms.smsVerification.HttpInvoker;
import com.smart.message.manage.sms.smsVerification.RandomUtil;
import com.smart.message.manage.sms.smsVerification.TokenUtil;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板短信DEMO
 */
@Service
public class TemplateSms implements TemplateInter {

    @Override
    public String getAccessTokenStr(String oautodel) {
        JSONObject obj = new JSONObject();
        String accessToken = TokenUtil.getAccessToken(oautodel);
        obj = JSONObject.parseObject(accessToken);
        if (obj != null && "0".equals(String.valueOf(obj.get("res_code")))) {
            String token = String.valueOf(obj.get("access_token"));
            return token;
        }
        return null;
    }

    @Override
    public void sendSmsByPhones(String phones, String... param) throws Exception {
        String[] arr = phones.split(",");
        for (int i = 0; i < arr.length; i++) {
            sendSms(arr[i], param);
        }
    }

    @Override
    public Map<String, String> sendSms(String tel, String... param) throws Exception {
        //访问令牌AT-------CC模式，AC模式都可，推荐CC模式获取令牌
        String ACCESS_TOKEN = getAccessTokenStr("CC");
        //获取当前时间
        String timestamp = DateUtil.dateToString();
        Gson gson = new Gson();
        Map<String, String> map = null;
        String str = RandomUtil.randomFor6();
        if (param.length > 0) {
            map = SmsTemplate.templat(param);
        }
        //这里存放模板参数，如果模板没有参数直接用template_param={}
        String template_param = gson.toJson(map);
        System.out.println(template_param + "=====template_param");
        String postEntity = "app_id=" + SmsConstants.APP_ID + "&access_token="
                + ACCESS_TOKEN + "&acceptor_tel=" + tel + "&template_id="
                + SmsConstants.TEMPLATE_ID + "&template_param=" + template_param
                + "&timestamp=" + URLEncoder.encode(timestamp, "utf-8");
        System.out.println(postEntity);
        String resJson = "";
        Map<String, String> map2 = new HashMap<String, String>();

        resJson = HttpInvoker.httpPost1(SmsConstants.POSTURL, null, postEntity);
        map2 = gson.fromJson(resJson,
                new TypeToken<Map<String, String>>() {
                }.getType());
        System.err.println(resJson);
        map2.put("vcode", str);
        return map2;
    }




}
