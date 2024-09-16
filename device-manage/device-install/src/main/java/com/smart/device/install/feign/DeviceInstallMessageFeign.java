package com.smart.device.install.feign;

import com.smart.device.common.install.entity.vo.SdDeviceVo;
import com.smart.device.common.message.vo.AlarmVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "device-message" )
public interface DeviceInstallMessageFeign {

    @PostMapping("/api/v2/xcx/handleAlarm")
    public SdDeviceVo handleAlarm(@RequestBody AlarmVo vo);


}
