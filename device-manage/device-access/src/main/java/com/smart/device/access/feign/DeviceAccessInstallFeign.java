package com.smart.device.access.feign;

import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.TManagerElectric;
import com.smart.device.common.install.entity.TManagerSmoke;
import com.smart.device.common.install.entity.TManagerWaterpress;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.install.entity.vo.ScreenVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Component
@FeignClient(name = "device-install" )
public interface DeviceAccessInstallFeign {

    // 更改设备状态
    @PostMapping("/api/v2/install/smoke/updateSmokeStatus")
    public Map updateSmokeStatus(@RequestBody TManagerSmoke entity);
    @PostMapping("api/v2/install/waterpress/updateWaterpressStatus")
    public Map updateWaterpressStatus(@RequestBody TManagerWaterpress entity);
    @PostMapping("api/v2/install/electric/updateElectricStatus")
    public Map updateElectricStatus(@RequestBody TManagerElectric entity);


}
