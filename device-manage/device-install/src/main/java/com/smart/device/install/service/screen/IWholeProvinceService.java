package com.smart.device.install.service.screen;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.vo.ScreenProvinceVo;
import java.util.List;

/**
 * @author f
 */
public interface IWholeProvinceService extends IService<ScreenProvinceVo> {

    List<ScreenProvinceVo> deviceSmokeNum();
    List<ScreenProvinceVo> deviceGasNum();
    List<ScreenProvinceVo> deviceWaterpressNum();
    List<ScreenProvinceVo> deviceLiquidlevelNum();
    List<ScreenProvinceVo> deviceElectricNum();

    int deviceSmokeAll();
    int deviceGasAll();
    int deviceWaterpressAll();
    int deviceLiquidlevelAll();
    int deviceElectricAll();

    int companyAll();
    int fireCompanyAll();

    List<ScreenProvinceVo> deviceByCity();

}
