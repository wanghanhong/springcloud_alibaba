package com.smart.device.access.controller;

import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.access.service.ITDeviceElectricService;
import com.smart.device.common.access.entity.TDeviceElectric;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author f
 */
@Api(tags = "")
@RestController
@RequestMapping("/api/v2/device/electric")
public class TDeviceElectricController {

    @Resource
    private ITDeviceElectricService itDeviceElectricService;

    @ApiOperation(value = "修改电力接口")
    @ApiImplicitParam
    @PostMapping("/electricUpdate")
    public Result deviceElectricUpdate(@RequestBody TDeviceElectric entity) {
        try {
            itDeviceElectricService.deviceElectricUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }

    @ApiOperation(value = "根据IMEI查询电力数据接口")
    @ApiImplicitParam
    @GetMapping("/selectDeviceElectric")
    public DeviceBaseVo selectDeviceElectric(Long id,Long imei) {
        try {
            DeviceBaseVo deviceBaseVo = itDeviceElectricService.selectDeviceElectric(id,imei);
            return deviceBaseVo;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     *  根据 IMEI 更改设备中 报警设置值，暂时全部放在设备安装里面。
     * @param entity
     * @return
     */
    @ApiOperation(value = "根据 IMEI 更改设备中报警设置值")
    @ApiImplicitParam
    @PostMapping("/updateDBElectric")
    public Result updateDBElectric(@RequestBody TDeviceElectric entity) {
        try {
            if(entity.getImei() != null){
                itDeviceElectricService.updateDBElectric(entity);
                return Result.SUCCESS();
            }else {
                return new Result(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC.getCode(),"IMEI不能为空",false);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }

}
