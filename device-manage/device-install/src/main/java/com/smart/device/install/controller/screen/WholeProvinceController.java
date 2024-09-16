package com.smart.device.install.controller.screen;

import com.smart.device.common.install.entity.vo.ScreenProvinceVo;
import com.smart.device.install.service.screen.IWholeProvinceService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: wueryong
 * @Date: 2020/6/11 15
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/api/v2/install/base/screen")
public class WholeProvinceController {

    @Resource
    private IWholeProvinceService iWholeProvinceService;

    @ApiOperation(value = "获取全省各市设备安装数量-烟感")
    @GetMapping("/deviceSmokeNum")
    public List<ScreenProvinceVo> deviceSmokeNum() {
        List<ScreenProvinceVo> list = iWholeProvinceService.deviceSmokeNum();
        return list;
    }
    @ApiOperation(value = "获取全省各市设备安装数量-气感")
    @GetMapping("/deviceGasNum")
    public List<ScreenProvinceVo> deviceGasNum() {
        List<ScreenProvinceVo> list = iWholeProvinceService.deviceGasNum();
        return list;
    }
    @ApiOperation(value = "获取全省各市设备安装数量-水压")
    @GetMapping("/deviceWaterpressNum")
    public List<ScreenProvinceVo> deviceWaterpressNum() {
        List<ScreenProvinceVo> list = iWholeProvinceService.deviceWaterpressNum();
        return list;
    }
    @ApiOperation(value = "获取全省各市设备安装数量-液位")
    @GetMapping("/deviceLiquidlevelNum")
    public List<ScreenProvinceVo> deviceLiquidlevelNum() {
        List<ScreenProvinceVo> list = iWholeProvinceService.deviceLiquidlevelNum();
        return list;
    }
    @ApiOperation(value = "获取全省各市设备安装数量-电气")
    @GetMapping("/deviceElectricNum")
    public List<ScreenProvinceVo> deviceElectricNum() {
        List<ScreenProvinceVo> list = iWholeProvinceService.deviceElectricNum();
        return list;
    }

    @ApiOperation(value = "获取全省设备安装数量-烟感")
    @GetMapping("/deviceSmokeAll")
    public int deviceSmokeAll() {
        int countNum = iWholeProvinceService.deviceSmokeAll();
        return countNum;
    }
    @ApiOperation(value = "获取全省设备安装数量-气感")
    @GetMapping("/deviceGasAll")
    public int deviceGasAll() {
        int countNum = iWholeProvinceService.deviceGasAll();
        return countNum;
    }
    @ApiOperation(value = "获取全省设备安装数量-气感")
    @GetMapping("/deviceWaterpressAll")
    public int deviceWaterpressAll() {
        int countNum = iWholeProvinceService.deviceWaterpressAll();
        return countNum;
    }
    @ApiOperation(value = "获取全省设备安装数量-气感")
    @GetMapping("/deviceLiquidlevelAll")
    public int deviceLiquidlevelAll() {
        int countNum = iWholeProvinceService.deviceLiquidlevelAll();
        return countNum;
    }
    @ApiOperation(value = "获取全省设备安装数量-电气")
    @GetMapping("/deviceElectricAll")
    public int deviceElectricAll() {
        int countNum = iWholeProvinceService.deviceElectricAll();
        return countNum;
    }

    @ApiOperation(value = "获取全省设备安装数量")
    @GetMapping("/deviceNumAll")
    public int deviceNumAll() {
        int countNum = 0;
        countNum += iWholeProvinceService.deviceSmokeAll();
        countNum += iWholeProvinceService.deviceGasAll();
        countNum += iWholeProvinceService.deviceWaterpressAll();
        countNum += iWholeProvinceService.deviceLiquidlevelAll();
        countNum += iWholeProvinceService.deviceElectricAll();
        return countNum;
    }
    @ApiOperation(value = "获取所有单位数量")
    @GetMapping("/companyAll")
    public int companyAll() {
        int countNum = iWholeProvinceService.companyAll();
        return countNum;
    }

    @ApiOperation(value = "获取消防单位数量")
    @GetMapping("/fireCompanyAll")
    public int fireCompanyAll() {
        int countNum = iWholeProvinceService.fireCompanyAll();
        return countNum;
    }

    @ApiOperation(value = "获取每个城市数量")
    @GetMapping("/deviceByCity")
    public List<ScreenProvinceVo> deviceByCity() {
        List<ScreenProvinceVo> list = iWholeProvinceService.deviceByCity();
        return list;
    }
}
