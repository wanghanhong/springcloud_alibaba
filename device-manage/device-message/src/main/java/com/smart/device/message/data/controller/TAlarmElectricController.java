package com.smart.device.message.data.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import com.smart.device.common.message.entity.TAlarmElectric;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.message.data.service.TAlarmElectricService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author
 */
@RestController
@RequestMapping("/api/v2/alarm/electric")
public class TAlarmElectricController extends BaseController {

    @Resource
    private TAlarmElectricService tAlarmElectricService;


    @GetMapping("/electricList")
    public Result buildingsList(Page page, AlarmVo vo) {

//        Map<String, Object> dataTable = getDataTable(sdBaseBuildingService.buildingsList(request,sdBaseBuilding));
//        return Result.SUCCESS(code,list);
        return null;
    }

    @PostMapping("/electricAdd")
    public Result electricAdd(TAlarmElectric tAlarmElectric) {
        try {
            tAlarmElectricService.electricAdd(tAlarmElectric);
        }catch (Exception e){
            e.printStackTrace();
            return Result.FAIL();
        }
        return Result.SUCCESS();
    }
}
