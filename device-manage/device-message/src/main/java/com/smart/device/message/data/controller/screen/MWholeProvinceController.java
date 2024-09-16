package com.smart.device.message.data.controller.screen;

import com.smart.device.message.data.service.screen.MWholeProvinceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@Api(tags = "")
@Slf4j
@RestController
@RequestMapping("/api/v2/message/base/screen")
public class MWholeProvinceController {

    @Resource
    private MWholeProvinceService mWholeProvinceService;

    @ApiOperation(value = "获取全省各市设备-报警数量-烟感表（2类）")
    @GetMapping("/alarmSmokeNum")
    public int alarmSmokeNum(String ids,Integer type) {
        int smokeNum = mWholeProvinceService.alarmSmokeNum(ids,type);
        return smokeNum;
    }

    @ApiOperation(value = "获取全省各市设备-报警数量-水压液位（2类）")
    @GetMapping("/alarmWaterpressNum")
    public int alarmWaterpressNum(String ids,Integer type) {
        int waterpressNum = mWholeProvinceService.alarmWaterpressNum(ids,type);
        return waterpressNum;
    }
    @ApiOperation(value = "获取全省各市设备-报警数量-电气（1类）")
    @GetMapping("/alarmElectricNum")
    public int alarmElectricNum(String ids,Integer type) {
        int electricNum = mWholeProvinceService.alarmElectricNum(ids,type);
        return electricNum;
    }



}
