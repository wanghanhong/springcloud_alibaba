package com.smart.device.message.controller;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.common.entity.TIpWhite;
import com.smart.device.message.data.mapper.TIpWhiteMapper;
import com.smart.device.message.factory.fire.DeviceParseFactory;
import com.smart.device.message.factory.fire.constant.CtWingDeviceConstant;
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

@Slf4j
@RestController
@RequestMapping
public class CtWingLAController {

    @Resource
    private DeviceParseFactory deviceParseFactory;
    @Resource
    private TIpWhiteMapper tIpWhiteMapper;


    /**
     *   烟感
     *   productId":"15014772
     *
     *   气感
     *   {"devi  col":"LWM2M","payload":{"valve_state":2,"temperature":0,"medium":0,"gas_value":0,"gas_sensor_state":0,"fan_state":2,"battery_voltage":1.1,"battery_value":100,"error_code":1}
     * }
     *
     * 用户信息传输装置
     * {{"upPacketSN":-1,"upDataSN":-1,"topic":"ad","timestamp":1609231209882,"tenantId":"10461180","serviceId":"","protocol":"tcp","productId":"15017085",
     * "payload":{"APPdata":"ewogICAiYiIgOiBbCiAgICAgIHsKICAgICAgICAgImlkIiA6ICIyMjIiLAogICAgICAgICAidiIgOiAiMSIKICAgICAgfQogICBdLAogICAiYyIgOiAyNywKICAgImlkIiA6IDE2MDkyMzEyMDk3NjcsCiAgICJ0bSIgOiAxNjA5MjMxMjA5NzY3Cn0="},
     * "messageType":"dataReport","deviceType":"","deviceId":"1501708501","assocAssetId":"","IMSI":"","IMEI":""}
      解密    57e1c5ecc91ee93289a9c469151ccebf
     * */
    @PostMapping("/api/iot/getCtWingDeviceData")
    public String getCtWingDeviceData(HttpServletRequest request,String token,String type, @RequestBody String param) {
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
            log.info("  zzz-LiAn type=" + type+"ctwing-"+param);
            String data = URLDecoder.decode(param, "utf-8");
            if (StringUtils.isBlank(data)) {
                return "数据为空";
            }
            JSONObject obj = JSONObject.parseObject(data);
            String productId = String.valueOf(obj.get("productId"));
            String ctype = CtWingDeviceConstant.TYPE_MAP.get(productId);
            deviceParseFactory.analysis(ctype, data);
        } catch (Exception e) {
            e.printStackTrace();
            return "数据解析错误。";
        }
        return "数据解析成功";
    }

    @PostMapping("/api/iot/getElectricData")
    public String getElectricData(HttpServletRequest request,String token,String type,@RequestBody String param) {
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
            log.info("zxx-LiAn-Electric-type=" + type+"ctwing-"+param);
            String data = URLDecoder.decode(param, "utf-8");
            if (StringUtils.isBlank(data)) {
                return "数据为空";
            }
            JSONObject obj = JSONObject.parseObject(data);
            String productId = String.valueOf(obj.get("productId"));
            String ctype = CtWingDeviceConstant.TYPE_MAP.get(productId);
            deviceParseFactory.analysis(ctype, data);
        } catch (Exception e) {
            e.printStackTrace();
            return "数据解析错误。";
        }
        return "连电力的数据解析成功";
    }


}
