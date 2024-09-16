package com.smart.device.access.controller;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.access.entity.vo.SdDeviceCtWingVO;
import com.smart.device.access.service.CtWingAPIService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 三多
 * @Time 2020/4/7
 */
@Api(tags = "")
@Controller
@Slf4j
public class CtWingNorthController {

    @Value("${appKey}")
    public String appKey;
    @Value("${appSecret}")
    public String appSecret;

    @Resource
    private CtWingAPIService ctWingAPIService;

    /**
     * --ctwing API 调用接口--LWM2M 协议------------------------------
     */

    @PostMapping("/api/v2/device/lwm2m/createDevice")
    @ResponseBody
    public Map createDevice(@RequestBody SdDeviceCtWingVO vo) {
        Map Map = new HashMap();
        try {
            JSONObject obj = ctWingAPIService.createDevice(vo);
            String code = String.valueOf(obj.get("code"));
            if ("0".equals(code)) {
                if (obj.get("result") != null) {
                    Map.put("data", obj.get("result"));
                    Map.put("code", "200");
                }
            } else {
                Map.put("data", obj.get("msg"));
                Map.put("code", "0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Map.put("code", "0");
            Map.put("msg", "创建设备失败");
        }
        return Map;
    }

    @PostMapping("/api/v2/device/lwm2m/updateDevice")
    @ResponseBody
    public Map updateDevice(@RequestBody SdDeviceCtWingVO vo) {
        Map Map = new HashMap();
        try {
            JSONObject obj = ctWingAPIService.updateDevice(vo);
            String code = String.valueOf(obj.get("code"));
            if ("0".equals(code)) {
                if (obj.get("result") != null) {
                    Map.put("data", obj.get("result"));
                    Map.put("code", "200");
                }
            } else {
                Map.put("data", obj.get("msg"));
                Map.put("code", "0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Map.put("code", "0");
            Map.put("msg", "创建设备失败");
        }
        return Map;
    }

    @PostMapping("/api/v2/device/lwm2m/deleteDevice")
    @ResponseBody
    public Map deleteDevice(String deviceIds, Integer type) {
        Map Map = new HashMap();
        try {
            JSONObject obj = ctWingAPIService.deleteDevice(deviceIds, type);
            String code = String.valueOf(obj.get("code"));
            if ("0".equals(code)) {
                Map.put("data", obj.get("msg"));
                Map.put("code", "200");
            } else {
                Map.put("data", obj.get("msg"));
                Map.put("code", "0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Map.put("code", "0");
            Map.put("msg", "删除设备失败");
        }
        return Map;
    }

    @GetMapping("/api/v2/device/queryDevice")
    @ResponseBody
    public Map queryDevice(String deviceId, Integer type) {
        Map Map = new HashMap();
        try {
            JSONObject obj = ctWingAPIService.queryDevice(deviceId, type);
            String code = String.valueOf(obj.get("code"));
            if ("0".equals(code)) {
                Map.put("data", obj.get("msg"));
                Map.put("code", "200");
            } else {
                Map.put("data", obj.get("msg"));
                Map.put("code", "0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Map.put("code", "0");
            Map.put("msg", "查询失败");
        }
        return Map;
    }

    @GetMapping("/api/v2/device/queryDeviceList")
    @ResponseBody
    public Map queryDeviceList(SdDeviceCtWingVO vo) {
        Map Map = new HashMap();
        try {
            JSONObject obj = ctWingAPIService.queryDeviceList(vo);
            String code = String.valueOf(obj.get("code"));
            if ("0".equals(code)) {
                Map.put("data", obj.get("msg"));
                Map.put("code", "200");
            } else {
                Map.put("data", obj.get("msg"));
                Map.put("code", "0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Map.put("code", "0");
            Map.put("msg", "查询失败");
        }
        return Map;
    }

    @GetMapping("/api/v2/device/queryDeviceStatus")
    @ResponseBody
    public Map queryDeviceStatus(String deviceId, Integer type) {
        Map Map = new HashMap();
        try {
            JSONObject obj = ctWingAPIService.queryDeviceStatus(deviceId, type);
            Map.put("data", obj);
            Map.put("code", "200");
        } catch (Exception e) {
            e.printStackTrace();
            Map.put("code", "0");
            Map.put("msg", "查询失败");
        }
        return Map;
    }

    @PostMapping("/api/v2/device/queryEventList")
    @ResponseBody
    public Map queryEventList(String deviceId, Integer type) {
        Map Map = new HashMap();
        try {
            JSONObject obj = ctWingAPIService.queryEventList(deviceId, type);
            Map.put("data", obj);
            Map.put("code", "200");
        } catch (Exception e) {
            e.printStackTrace();
            Map.put("code", "0");
            Map.put("msg", "查询失败");
        }
        return Map;
    }
}
