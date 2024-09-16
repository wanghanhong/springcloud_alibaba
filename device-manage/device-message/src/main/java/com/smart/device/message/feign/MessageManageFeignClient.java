package com.smart.device.message.feign;

import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "message-manage")
public interface MessageManageFeignClient {

    // ,url = "http://192.168.0.79:9200/message-manage"
    // ,url = "http://172.22.1.21:9200/message-manage"

    @GetMapping("/api/v2/mq/mqSend")
    public DeviceBaseVo mqSend(@RequestParam("phones")String phones,@RequestParam("smsParam")String smsParam,@RequestParam("phoneParam")String phoneParam,@RequestParam("strategyType")Integer strategyType,@RequestParam("deviceCode")String deviceCode);



}
