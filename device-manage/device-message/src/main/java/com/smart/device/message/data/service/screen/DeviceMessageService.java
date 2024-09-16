package com.smart.device.message.data.service.screen;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.vo.ScreenListVo;
import com.smart.device.common.install.entity.vo.ScreenProvinceVo;
import com.smart.device.common.install.entity.vo.ScreenVo;
import java.util.List;
import java.util.Map;

/**
 * @author f
 */
public interface DeviceMessageService extends IService<ScreenProvinceVo> {

    List<ScreenVo> deviceSmokeEventNum();
    List<ScreenVo> deviceGasEventNum();
    List<ScreenVo> deviceWaterpressEventNum();
    List<ScreenVo> deviceLiquidleveEventNum();
    List<ScreenVo> deviceElectricEventNum();

    List<ScreenVo> eventNumByCompanySmoke();
    List<ScreenVo> eventNumByCompanyGas();
    List<ScreenVo> eventNumByCompanyWaterpress();
    List<ScreenVo> eventNumByCompanyLiquidleve();
    List<ScreenVo> eventNumByCompanyElectric();

    List<ScreenVo> eventNumByDeviceSmoke();
    List<ScreenVo> eventNumByDeviceGas();
    List<ScreenVo> eventNumByDeviceWaterpress();
    List<ScreenVo> eventNumByDeviceLiquidleve();
    ScreenListVo eventNumByDeviceElectric();

    Map<String,Object> eventNumByMonthSmoke();
    Map<String,Object> eventNumByMonthGas();
    Map<String,Object> eventNumByMonthWaterpress();
    Map<String,Object> eventNumByMonthLiquidleve();
    Map<String,Object> eventNumByMonthElectric();
}
