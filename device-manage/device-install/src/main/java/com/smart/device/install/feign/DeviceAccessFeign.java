package com.smart.device.install.feign;

import com.smart.device.common.access.entity.TDeviceDict;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "device-access")
public interface DeviceAccessFeign {

    @GetMapping("/api/v2/device/smoke/selectDeviceSmoke")
    public DeviceBaseVo selectDeviceSmoke(@RequestParam("imei") Long imei);
    @GetMapping("/api/v2/device/waterpress/selectDeviceWaterpress")
    public DeviceBaseVo selectDeviceWaterpress(@RequestParam("imei") Long imei);
    @GetMapping("/api/v2/device/electric/selectDeviceElectric")
    public DeviceBaseVo selectDeviceElectric(@RequestParam("imei") Long imei);
    @GetMapping("/api/v2/device/cameras/selectDeviceCameras")
    public DeviceBaseVo selectDeviceCameras(@RequestParam("sn") String sn);

    //  主要查询设备
    @GetMapping("/api/v2/device/dict/getDictBydeviceType")
    public DeviceBaseVo getDictBydeviceType(@RequestParam("deviceType") String deviceType);

    @GetMapping("/api/v2/device/dict/queryDeviceParentTypeList")
    public List<TDeviceDict> queryDeviceParentTypeList();

}
