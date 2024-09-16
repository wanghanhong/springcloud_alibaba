package com.smart.fire.platform.web.feign;

import com.smart.device.common.install.entity.vo.ScreenListVo;
import com.smart.device.common.install.entity.vo.ScreenVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;

@Component
@FeignClient(name = "device-message")
public interface DeviceMessageFeign {

    @GetMapping("/api/v2/message/base/screen/alarmSmokeNum")
    public int alarmSmokeNum(@RequestParam("ids") String ids,@RequestParam("type") int type);
    @GetMapping("/api/v2/message/base/screen/alarmWaterpressNum")
    public int alarmWaterpressNum(@RequestParam("ids") String ids,@RequestParam("type") int type);
    @GetMapping("/api/v2/message/base/screen/alarmElectricNum")
    public int alarmElectricNum(@RequestParam("ids") String ids,@RequestParam("type") int type);

    @GetMapping("/api/v2/message/base/screen/event/deviceSmokeEventNum")
    public List<ScreenVo> deviceSmokeEventNum();
    @GetMapping("/api/v2/message/base/screen/event/deviceGasEventNum")
    public List<ScreenVo> deviceGasEventNum();
    @GetMapping("/api/v2/message/base/screen/event/deviceWaterpressEventNum")
    public List<ScreenVo> deviceWaterpressEventNum();
    @GetMapping("/api/v2/message/base/screen/event/deviceLiquidlevelEventNum")
    public List<ScreenVo> deviceLiquidlevelEventNum();
    @GetMapping("/api/v2/message/base/screen/event/deviceElectricEventNum")
    public List<ScreenVo> deviceElectricEventNum();


    @GetMapping("/api/v2/message/base/screen/event/eventNumByCompanySmoke")
    public List<ScreenVo> eventNumByCompanySmoke();
    @GetMapping("/api/v2/message/base/screen/event/eventNumByCompanyGas")
    public List<ScreenVo> eventNumByCompanyGas();
    @GetMapping("/api/v2/message/base/screen/event/eventNumByCompanyWaterpress")
    public List<ScreenVo> eventNumByCompanyWaterpress();
    @GetMapping("/api/v2/message/base/screen/event/eventNumByCompanyLiquidlevel")
    public List<ScreenVo> eventNumByCompanyLiquidlevel();
    @GetMapping("/api/v2/message/base/screen/event/eventNumByCompanyElectric")
    public List<ScreenVo> eventNumByCompanyElectric();

    @GetMapping("/api/v2/message/base/screen/event/eventNumByDeviceSmoke")
    public List<ScreenVo> eventNumByDeviceSmoke();
    @GetMapping("/api/v2/message/base/screen/event/eventNumByDeviceGas")
    public List<ScreenVo> eventNumByDeviceGas();
    @GetMapping("/api/v2/message/base/screen/event/eventNumByDeviceWaterpress")
    public List<ScreenVo> eventNumByDeviceWaterpress();
    @GetMapping("/api/v2/message/base/screen/event/eventNumByDeviceLiquidlevel")
    public List<ScreenVo> eventNumByDeviceLiquidlevel();
    @GetMapping("/api/v2/message/base/screen/event/eventNumByDeviceElectric")
    public ScreenListVo eventNumByDeviceElectric();

    @GetMapping("/api/v2/message/base/screen/event/eventNumByMonthSmoke")
    public Map<String,Object> eventNumByMonthSmoke();
    @GetMapping("/api/v2/message/base/screen/event/eventNumByMonthGas")
    public Map<String,Object> eventNumByMonthGas();
    @GetMapping("/api/v2/message/base/screen/event/eventNumByMonthWaterpress")
    public Map<String,Object> eventNumByMonthWaterpress();
    @GetMapping("/api/v2/message/base/screen/event/eventNumByMonthLiquidlevel")
    public Map<String,Object> eventNumByMonthLiquidlevel();
    @GetMapping("/api/v2/message/base/screen/event/eventNumByMonthElectric")
    public Map<String,Object> eventNumByMonthElectric();

}
