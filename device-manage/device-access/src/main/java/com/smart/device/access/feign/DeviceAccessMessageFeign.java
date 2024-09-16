package com.smart.device.access.feign;

import com.smart.device.common.install.entity.vo.SdDeviceVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "device-message")
public interface DeviceAccessMessageFeign {

    @GetMapping("/api/v2/heart/selectSmokeLast")
    public SdDeviceVo selectSmokeLast(@RequestParam("deviceId") Long deviceId);
    @GetMapping("/api/v2/heart/selectWaterpressLast")
    public SdDeviceVo selectWaterpressLast(@RequestParam("deviceId") Long deviceId);
    @GetMapping("/api/v2/heart/selectElectricLast")
    public SdDeviceVo selectElectricLast(@RequestParam("deviceId") Long deviceId);


}
