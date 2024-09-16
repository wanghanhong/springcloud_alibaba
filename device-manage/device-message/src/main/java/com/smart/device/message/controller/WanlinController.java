package com.smart.device.message.controller;

import com.smart.device.message.data.mapper.TIpWhiteMapper;
import com.smart.device.message.factory.fire.DeviceParseFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author l
 */
@Slf4j
@RestController
@RequestMapping
public class WanlinController {
    @Resource
    private DeviceParseFactory deviceParseFactory;
    @Resource
    private TIpWhiteMapper tIpWhiteMapper;

    /**
     * http://113.141.93.157:9200/device-message/api/iot/getDeviceData
     *
     * @param type
     * @param param
     * @return
     */
    @PostMapping("/api/iot/getWanlinData")
    public String getTopicHttp(HttpServletRequest request,String token,String type, @RequestBody String param) {
        String msg = "无权限。";
        log.info("  vvv-canshu=" + token);
        if(StringUtils.isBlank(token) || !IpAddressUtil.token_pass.equals(token)){
            return msg;
        }
        try {
            String ip = IpAddressUtil.getIpAddress(request);
            log.info("  vvv-ip=" + ip);
            List<String> list = tIpWhiteMapper.queryTIpWhiteList();
            if(!list.contains(ip)){
                return msg;
            }
            log.info("zzz-type=" + type+"lian-"+param);
            if(param.contains("&")){
                param = param.substring(param.indexOf("&")+1);
            }
            String data = URLDecoder.decode(param, "utf-8");
            if (StringUtils.isNotBlank(data)) {
                if (data.endsWith("=")) {
                    data = data.substring(0, data.length() - 1);
                }
            }
            String result = deviceParseFactory.analysis(type, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Inspection pass";
    }



}
