package com.smart.brd.manage.message.controller;

import com.smart.brd.manage.message.factory.DeviceParseFactory;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * @author junglelocal
 */
@RestController
@Api(tags = "设备数据解析")
@Slf4j
@RequestMapping("/api/v1/message/parse")
public class DeviceParseController {

    @Resource
    private DeviceParseFactory deviceParseFactory;

    @PostMapping("/deviceData")
    public String getCtWingDeviceData(HttpServletRequest request,String token,String type,@RequestBody String param) {
        String result = "";
        try {
            log.info("Data type =" + type + "device-"+ param);
            String data = URLDecoder.decode(param, "utf-8");
            if (StringUtils.isBlank(data)) {
                return "数据为空";
            }
            type = "11";
            result = deviceParseFactory.analysis(type, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "数据解析成功\n" + result;
    }
    //需要修改
    @PostMapping("/newdeviceData")
    public String getCtWingDeviceData22(HttpServletRequest request,String token,String type,@RequestBody String param) {
        String result = "";
        try {
            log.info("Data type =" + type + "device-"+ param);
            String data = URLDecoder.decode(param, "utf-8");
            if (StringUtils.isBlank(data)) {
                return "数据为空";
            }
            type = "11";
            result = deviceParseFactory.analysisNew(type, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result==""){
            return "未包含有效数据";
        }
        else{
        return "数据解析成功\n" + result;
        }
    }
}
