package com.smart.device.message.feign;

import com.smart.common.core.domain.Result;
import com.smart.device.common.access.entity.*;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Component
@FeignClient(name = "device-access")
public interface DeviceBaseFeignClient {

    // ,url = "http://192.168.0.79:9200/device-access"
    // ,url = "http://172.22.1.21:9200/device-access"
    //根据类型查找
    @GetMapping("/api/v2/userInfoType/getDictByDeviceType")
    public UserInfoType getDictByDeviceType(@RequestParam("partId")String partId);

    @GetMapping("/api/v2/device/smoke/selectDeviceSmoke")
    public DeviceBaseVo selectDeviceSmokeById(@RequestParam("id") Long id);

    @GetMapping("/api/v2/device/waterpress/selectDeviceWaterpress")
    public DeviceBaseVo selectDeviceWaterpressById(@RequestParam("id") Long id);

    @GetMapping("/api/v2/device/electric/selectDeviceElectric")
    public DeviceBaseVo selectDeviceElectricById(@RequestParam("id") Long id);

    @GetMapping("/api/v2/device/cameras/selectDeviceCameras")
    public DeviceBaseVo selectDeviceCamerasById(@RequestParam("id") String id);

    @GetMapping("/api/v2/device/smoke/selectDeviceSmoke")
    public DeviceBaseVo selectDeviceSmokeByImei(@RequestParam("imei") Long imei);

    @GetMapping("/api/v2/device/waterpress/selectDeviceWaterpress")
    public DeviceBaseVo selectDeviceWaterpressByImei(@RequestParam("imei") Long imei);

    @GetMapping("/api/v2/device/electric/selectDeviceElectric")
    public DeviceBaseVo selectDeviceElectricByImei(@RequestParam("imei") Long imei);

    @GetMapping("/api/v2/device/cameras/selectDeviceCameras")
    public DeviceBaseVo selectDeviceCamerasBySn(@RequestParam("sn") String sn);



    // 查询心跳
    @GetMapping("api/v2/device/dict/getDictBydeviceType")
    public TDeviceDict getDictBydeviceType(@RequestParam("deviceType") Integer deviceType);
    // 更改设备状态
    @PostMapping("/api/v2/device/smoke/deviceSmokeUpdate")
    public Map deviceSmokeUpdate(@RequestBody TDeviceSmoke entity);
    @PostMapping("api/v2/device/waterpress/deviceWaterpressUpdate")
    public Map deviceWaterpressUpdate(@RequestBody TDeviceWaterpress entity);
    @PostMapping("api/v2/device/electric/electricUpdate")
    public Map deviceElectricUpdate(@RequestBody TDeviceElectric entity);

    //  查询所有的水压
    @GetMapping("/api/v2/device/waterpress/deviceWaterpresssAll")
    public Result deviceWaterpresssAll();
    @GetMapping("/api/v2/device/waterpress/deviceLiquidlevelAll")
    public Result deviceLiquidlevelAll();



}
