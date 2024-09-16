package com.smart.message.manage.yunhu.common;

import com.smart.message.manage.common.utils.DateUtil;
import com.smart.message.manage.yunhu.PhoneConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求header设置
 */
public class YunHttp {
    /**
     * 基本信息
     *
     * @return
     */
    static HttpHeaders getHeaderBase() {
        HttpHeaders headers = new HttpHeaders();
        Map map = new HashMap();
        map = getAuth();
        MediaType type = MediaType.parseMediaType("application/json;charset=utf-8");
        headers.setContentType(type);
        headers.set("Accept", "application/json");
        headers.add("Authorization", (String) map.get("auth"));
        headers.add("sig", (String) map.get("sig"));
        return headers;
    }

    /**
     * Content-Length设置（无参时默认1024）
     *
     * @return
     */
    public static HttpHeaders getHeader() {
        HttpHeaders headers = getHeaderBase();
        headers.set("Content-Length", "1024");
        return headers;
    }

    /**
     * Content-Length设置
     *
     * @return
     */
    public static HttpHeaders getHeader(String textlength) {
        HttpHeaders headers = getHeaderBase();
        headers.set("Content-Length",textlength);
        return headers;
    }

    public static String getLengthInteger(String text) {
        Integer textlength = 1024;
        try {
            textlength = text.getBytes("UTF-8").length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(textlength);
    }


    public static Map<String, String> getAuth() {
        Map<String, String> map = new HashMap<String, String>();
        String yyyy = DateUtil.getyyyy();
        String sig = DateUtil.getSig(PhoneConstants.SUBACCOUNTSID, PhoneConstants.SUBACCOUNTTOKEN, yyyy);
        String auth = getAuth(PhoneConstants.SUBACCOUNTSID, yyyy);
        map.put("sig", sig);
        map.put("auth", auth);
        return map;
    }

    public static String getAuth(String accountid, String yyyy) {
        String str = "<" + accountid + ":" + yyyy + ">";
        str = accountid + ":" + yyyy;
        String md5DigestAsHex = new sun.misc.BASE64Encoder().encode(str.getBytes());
        return md5DigestAsHex;
    }


}
