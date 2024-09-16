package com.smart.card.cardapi.test;

import com.smart.card.cardapi.util.DesUtils;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;

/**
 * @author junglelocal
 */
@Controller
@Slf4j
public class TestController {

    @Resource
    private RestTemplate restTemplate;
    private String userId="O5VVY4EX6Odz0215n8p239FvDJvx7euk";
    private String password="4H80Nt8tbvUA64Yt";
    private String aSecret="yak5485Yc";


    @PostMapping("/api/card/v1/testUrl")
    @ResponseBody
    public Result testUrl() {
        String iccid = "8986111827003024737";
        try {
            //用户名
            String user_id = userId;
            String paramAdd = "&iccid="+iccid;
            String method = "queryBalance";

            //加密数组，数组所需参数根据对应的接口文档，根据iccid查询时加密数组里面传入iccid
            String[] arr = {iccid,user_id,password,method};
             //key1,key2,key2为电信提供的9位长接口密钥平均分为三段所形成
            //key1为密钥前三位，key2为密钥中间三位，key3位密钥最后三位  yak5485Yc
            String key1 = aSecret.substring(0,3);
            String key2 = aSecret.substring(3,6);
            String key3 = aSecret.substring(6,9);
            //密码加密
            String password_1 = DesUtils.strEnc(password,key1,key2,key3);
            //生成sign加密值
            String sign = DesUtils.strEnc(DesUtils.naturalOrdering(arr),key1,key2,key3);

            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json;charset=utf-8");
            headers.setContentType(type);

            String path_url = "http://api.ct10649.com:9001/m2m_ec/query.do?method="+method+"&user_id="+user_id+"&passWord="+password_1
                    +"&sign="+sign+paramAdd;
            // http://api.ct10649.com:9001/m2m_ec/query.do?method=queryBalance&iccid=8986****4660&user_id=te****st&passWord=32****00&sign=C5****FD
// http://api.ct10649.com:9001/m2m_ec/query.do?method=queryBalance&user_id=O5VVY4EX6Odz0215n8p239FvDJvx7euk&passWord=62C5826BF93D6FEC7B2CBA10B352803AF66FF52F6A26D0A9300970C6890480A7&sign=B3D1B7ABED442F35D0B87ED1D36627AF1C90D2106FE841EF79D93C5D41EE30C9F33C53E27458584E220AF9E6B3FCB1A6975E9F5229D1F19604775CA920888879E14EA750C2281409427EDC7A9AE474DC9AD732DCE3859B28C63DADFBD452747A79BC23FB85E287E925DADE80991606E0443521956DB5B3C303CD1835125C1A9C&iccid=1410132003202
//
            log.info("url数据--"+path_url);
            String body = null;
            try {
                ResponseEntity<String> responseEntiry = restTemplate.getForEntity(path_url, String.class);
                body = responseEntiry.getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //解析数据
            log.info("解析数据--"+4+body);
            return Result.SUCCESS(body);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.SERVER_ERROR);
        }
    }

}
