package com.smart.message.manage.yunhu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smart.message.manage.monitor.service.SdDeviceVoiceService;
import com.smart.message.manage.yunhu.PhoneConstants;
import com.smart.message.manage.yunhu.common.Account;
import com.smart.message.manage.yunhu.common.YunHttp;
import com.smart.message.manage.yunhu.service.YunHuServiceInter;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 * <p>
 * 主账户ID：75eac8dc768a3764de4501c2370c3d46
 * 主账户Token：0306ec07773338698348a8bd3aae6637
 * 应用ID：848d78b68b1de5b4265bdd77f4e3a762
 * 子账户ID：2776db0f82dc0e6207d196cc4b8ab8b0
 * 子账户Token: 679491e095d4ebdc75b2a88a86fb6deb
 * 绑定云总机号码: 02987898166
 * @author l
 */
@Slf4j
@Service
public class YunHuServiceImpl implements YunHuServiceInter {


    @Resource
    private RestTemplate restTemplate;
    @Resource
    private SdDeviceVoiceService sdDeviceVoiceService;


    @Override
    public JSONObject getAccounts() {

        Account account = new Account();
        return account.getAccounts(restTemplate);
    }

    @Override
    public JSONObject uploadText(String text) {
        HttpHeaders headers = YunHttp.getHeader(text);
        String url = PhoneConstants.UPLOADTEXT_URL.replace("s_accountSid", PhoneConstants.ACCOUNTSID).replace("s_sig", headers.get("sig").get(0));

        /**
         *  变量格式：变量内容（参数）请使用{数字}，按序填写，如{1}，变量内容必须为半角字符，外侧括号为英文花括号，
         *         其中的数字必须从1开始顺序排列，如：尊敬的用户您好，您的{1}已送至1号101楼快递中心，快递单号为{2}，提货码{3}，
         *         请于早9点至晚6点到店取货。
         *         template	0-纯文本；
         *          1-模板
         */
        String str = "尊敬的用户您好，{1}{2}{3}{4}{5}层，编号为{6}的设备存在报警信息或者出现异常情况，请安排相关责任人确认报警信息，并立即处理。";

        JSONObject objs = new JSONObject();
        objs.put("appId", PhoneConstants.APPID);
        objs.put("text", text);
        objs.put("template", "1");
        objs.put("maxAge", "0");
        JSONObject oall = new JSONObject();
        oall.put("uploadText", objs);

        String jsonStr = JSONObject.toJSONString(oall);
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonStr, headers);
        ResponseEntity<String> responseEntiry = restTemplate.postForEntity(url, httpEntity, String.class);
        String body = responseEntiry.getBody();
        //解析数据
        log.info(body);
        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject;
    }



    @Override
    public String voicenotifyBack(String param) {
        System.out.println("传过来的xml信息转换成实体类如下：==========");
        System.out.println(param.toString());
        Map<String, String> map = getParseDom4j(param);
        String res = "";
        String callId = map.get("callId");
        if (callId != null && !"".equals(callId)) {
            res = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                    "<response>\n" +
                    "<retcode>00000</retcode>\n" +
                    "<reason>成功</reason>\n" +
                    "</response>";
        } else {
            res = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                    "<response>\n" +
                    "<retcode>1</retcode>\n" +
                    "<reason>失败</reason>\n" +
                    "</response>";
        }

        sdDeviceVoiceService.updateDeviceVoices(callId, "1");

        log.info(param);
        return res;
    }


    public static Map<String, String> getParseDom4j(String xmlStr) {
        Map<String, String> map = new HashMap<>();
        Document doc = null;
        try {
            doc = (Document) DocumentHelper.parseText(xmlStr);
            org.dom4j.Element message = doc.getRootElement();
            Iterator elements = message.elementIterator();
            while (elements.hasNext()) {
                org.dom4j.Element element = (org.dom4j.Element) elements.next();
                if ("callId".equals(element.getName())) {
                    map.put("callId", element.getText());
                }
                if ("state".equals(element.getName())) {
                    map.put("state", element.getText());
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }


    public JSONObject getAccountInterface(String urlpart, String bodys) {

        HttpHeaders header = YunHttp.getHeader(bodys);
        String sig = header.get("sig").get(0);
        String url = PhoneConstants.GETACCOUNTINTERFACE_URL.replace("s_accountSid", PhoneConstants.ACCOUNTSID).replace("s_sig", sig).replace("s_urlpart", urlpart);
        HttpEntity<String> httpEntity = new HttpEntity<>(bodys, header);
        ResponseEntity<String> responseEntiry = restTemplate.postForEntity(url, httpEntity, String.class);
        String body = responseEntiry.getBody();
        //解析数据
        log.info(body);
        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject;
    }

    public JSONObject getSubAccountInterface(String urlpart, String bodys) {
        HttpHeaders header = YunHttp.getHeader(bodys);
        String sig = header.get("sig").get(0);
        String url = "https://api.emicloud.com/20171106/SubAccounts/" + PhoneConstants.SUBACCOUNTSID + "/" + urlpart + "?sig=" + sig;
        HttpEntity<String> httpEntity = new HttpEntity<>(bodys, header);
        ResponseEntity<String> responseEntiry = restTemplate.postForEntity(url, httpEntity, String.class);
        String body = responseEntiry.getBody();
        //解析数据
        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject;
    }


    public static void main(String[] args) throws Exception {
//        String xmlStr =  "<request><callId>api1320000000115821930263432lJ4M</callId><accountSid>75eac8dc768a3764de4501c2370c3d46</accountSid><appId>848d78b68b1de5b4265bdd77f4e3a762</appId><caller>02987898166</caller><called>19991932716</called><state>0</state><reason>1012</reason><duration>4</duration><type>5</type><userData>19991932719</userData><ringDuration>10</ringDuration><startTime>1582193044</startTime><stopTime>1582193048</stopTime></request>";
//        long begin = System.currentTimeMillis();
//
//        YunHuServiceImpl impl = new YunHuServiceImpl();
//        impl.voicenotifyBack(xmlStr);
        String voiceNotify = "{\"voiceNotify\":{\"textId\":\"19112939\",\"outNumber\":\"02987898166\",\"userData\":\"19959886236\",\"appId\":\"848d78b68b1de5b4265bdd77f4e3a762\",\"to\":\"19959886236\",\"content\":\"\"}}";
        System.out.println(voiceNotify);
        System.out.println(voiceNotify.length());


    }


}
