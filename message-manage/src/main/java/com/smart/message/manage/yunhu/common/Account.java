package com.smart.message.manage.yunhu.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smart.message.manage.common.constant.Constants;
import com.smart.message.manage.yunhu.PhoneConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Account {

    public JSONObject getAccounts(RestTemplate restTemplate) {
        String url = PhoneConstants.GETACCOUNTS_URL.replace("s_accountSid", PhoneConstants.ACCOUNTSID).replace("s_sig", YunHttp.getAuth().get("sig"));
        HttpEntity<String> httpEntity = new HttpEntity<>("", YunHttp.getHeader());
        ResponseEntity<String> responseEntiry = restTemplate.postForEntity(url, httpEntity, String.class);
        String body = responseEntiry.getBody();
        //解析数据
        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject;
    }
}
