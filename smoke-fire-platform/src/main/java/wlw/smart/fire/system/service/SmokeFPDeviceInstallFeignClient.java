package wlw.smart.fire.system.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Component
@FeignClient(name = "device-install")
public interface SmokeFPDeviceInstallFeignClient {

    @GetMapping("/api/v2/install/base/firefighter/selectBaseFirefighter")
    public Map selectBaseFirefighter(@RequestParam("phone") String phone);



}
