package com.smart.device.message.feign;

import com.smart.device.common.install.entity.*;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.install.entity.vo.ScreenVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
@FeignClient(name = "device-install")
public interface DeviceInstallFeignClient{

    // ,url = "http://192.168.0.79:9200/device-install"
    // ,url = "http://172.22.1.21:9200/device-install"
    @GetMapping("/api/v2/install/electric/electricPerSonByDeviceId")
    public DeviceCompanyVo electricPerSonByDeviceId(@RequestParam("deviceId") Long deviceId);

    @GetMapping("/api/v2/install/smoke/smokePerSonByDeviceId")
    public DeviceCompanyVo smokePerSonByDeviceId(@RequestParam("deviceId") Long deviceId);

    @GetMapping("/api/v2/install/waterpress/waterpressPerSonByDeviceId")
    public DeviceCompanyVo waterpressPerSonByDeviceId(@RequestParam("deviceId") Long deviceId);

    @GetMapping("/api/v2/region/selectRegionsByCode")
    public List<TBaseRegion> selectRegionsByCode(@RequestParam("province")String province, @RequestParam("city")String city, @RequestParam("county")String county, @RequestParam("town")String town);

    @GetMapping("/api/v2/install/base/screen/install/deviceBuildCompanyElectric")
    public List<ScreenVo> deviceBuildCompanyElectric();

    // 更改设备状态
    @PostMapping("/api/v2/install/smoke/updateSmokeStatus")
    public Map updateSmokeStatus(@RequestBody TManagerSmoke entity);
    @PostMapping("api/v2/install/waterpress/updateWaterpressStatus")
    public Map updateWaterpressStatus(@RequestBody TManagerWaterpress entity);
    @PostMapping("api/v2/install/electric/updateElectricStatus")
    public Map updateElectricStatus(@RequestBody TManagerElectric entity);

    @GetMapping("/api/v2/region/mapRegions")
    public Map<String,String> mapRegions(@RequestParam("regionCodes") String regionCodes);

    // 字典
    @GetMapping("/api/v2/device/dict/dictListByType")
    List<TBaseDict> dictListByType(@RequestParam("type") String type);


}
