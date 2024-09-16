package com.smart.device.install.service.screen;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.vo.ScreenVo;
import java.util.List;

/**
 * @author f
 */
public interface DeviceInstallService extends IService<ScreenVo> {

    List<ScreenVo> deviceStateSmoke();
    List<ScreenVo> deviceStateGas();
    List<ScreenVo> deviceStateWaterpress();
    List<ScreenVo> deviceStateLiquidlevel();
    List<ScreenVo> deviceStateElectric();

    List<ScreenVo> deviceTypeSmoke();
    List<ScreenVo> deviceTypeGas();
    List<ScreenVo> deviceTypeWaterpress();
    List<ScreenVo> deviceTypeLiquidlevel();
    List<ScreenVo> deviceTypeElectric();

    List<ScreenVo> deviceSmokeNumByCompany();
    List<ScreenVo> deviceGasNumByCompany();
    List<ScreenVo> deviceWaterpressNumByCompany();
    List<ScreenVo> deviceLiquidlevelNumByCompany();
    List<ScreenVo> deviceElectricNumByCompany();

    List<ScreenVo> deviceBuildCompanyElectric();

}
