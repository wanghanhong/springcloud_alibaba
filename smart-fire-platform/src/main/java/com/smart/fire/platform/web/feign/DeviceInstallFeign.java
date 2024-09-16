package com.smart.fire.platform.web.feign;

import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.vo.ScreenProvinceVo;
import com.smart.device.common.install.entity.vo.ScreenVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Component
@FeignClient(name = "device-install")
public interface DeviceInstallFeign {


    @GetMapping("/api/v2/install/base/city/queryCitysByCode")
    public List<TBaseRegion> queryCitysByCode(@RequestParam("code") String code);

    @GetMapping("/api/v2/install/base/screen/deviceSmokeNum")
    public List<ScreenProvinceVo> deviceSmokeNum();
    @GetMapping("/api/v2/install/base/screen/deviceGasNum")
    public List<ScreenProvinceVo> deviceGasNum();
    @GetMapping("/api/v2/install/base/screen/deviceWaterpressNum")
    public List<ScreenProvinceVo> deviceWaterpressNum();
    @GetMapping("/api/v2/install/base/screen/deviceLiquidlevelNum")
    public List<ScreenProvinceVo> deviceLiquidlevelNum();
    @GetMapping("/api/v2/install/base/screen/deviceElectricNum")
    public List<ScreenProvinceVo> deviceElectricNum();

    @GetMapping("/api/v2/install/base/screen/deviceSmokeAll")
    public ScreenProvinceVo deviceSmokeAll();
    @GetMapping("/api/v2/install/base/screen/deviceGasAll")
    public ScreenProvinceVo deviceGasAll();
    @GetMapping("/api/v2/install/base/screen/deviceWaterpressAll")
    public ScreenProvinceVo deviceWaterpressAll();
    @GetMapping("/api/v2/install/base/screen/deviceLiquidlevelAll")
    public ScreenProvinceVo deviceLiquidlevelAll();
    @GetMapping("/api/v2/install/base/screen/deviceElectricAll")
    public ScreenProvinceVo deviceElectricAll();
    @GetMapping("/api/v2/install/base/screen/deviceNumAll")
    public int deviceNumAll();

    @GetMapping("/api/v2/install/base/screen/companyAll")
    public int companyAll();
    @GetMapping("/api/v2/install/base/screen/fireCompanyAll")
    public int fireCompanyAll();

    @GetMapping("/api/v2/install/base/screen/deviceByCity")
    public List<ScreenProvinceVo> deviceByCity();

    @GetMapping("/api/v2/install/base/screen/install/deviceStateSmoke")
    public List<ScreenVo> deviceStateSmoke();
    @GetMapping("/api/v2/install/base/screen/install/deviceStateGas")
    public List<ScreenVo> deviceStateGas();
    @GetMapping("/api/v2/install/base/screen/install/deviceStateWaterpress")
    public List<ScreenVo> deviceStateWaterpress();
    @GetMapping("/api/v2/install/base/screen/install/deviceStateLiquidlevel")
    public List<ScreenVo> deviceStateLiquidlevel();
    @GetMapping("/api/v2/install/base/screen/install/deviceStateElectric")
    public List<ScreenVo> deviceStateElectric();

    @GetMapping("/api/v2/install/base/screen/install/deviceTypeSmoke")
    public List<ScreenVo> deviceTypeSmoke();
    @GetMapping("/api/v2/install/base/screen/install/deviceTypeGas")
    public List<ScreenVo> deviceTypeGas();
    @GetMapping("/api/v2/install/base/screen/install/deviceTypeWaterpress")
    public List<ScreenVo> deviceTypeWaterpress();
    @GetMapping("/api/v2/install/base/screen/install/deviceTypeLiquidlevel")
    public List<ScreenVo> deviceTypeLiquidlevel();
    @GetMapping("/api/v2/install/base/screen/install/deviceTypeElectric")
    public List<ScreenVo> deviceTypeElectric();

    @GetMapping("/api/v2/install/base/screen/install/deviceSmokeNumByCompany")
    public List<ScreenVo> deviceSmokeNumByCompany();
    @GetMapping("/api/v2/install/base/screen/install/deviceGasNumByCompany")
    public List<ScreenVo> deviceGasNumByCompany();
    @GetMapping("/api/v2/install/base/screen/install/deviceWaterpressNumByCompany")
    public List<ScreenVo> deviceWaterpressNumByCompany();
    @GetMapping("/api/v2/install/base/screen/install/deviceLiquidlevelNumByCompany")
    public List<ScreenVo> deviceLiquidlevelNumByCompany();
    @GetMapping("/api/v2/install/base/screen/install/deviceElectricNumByCompany")
    public List<ScreenVo> deviceElectricNumByCompany();


}
