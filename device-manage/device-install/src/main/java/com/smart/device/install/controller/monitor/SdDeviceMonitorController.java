package com.smart.device.install.controller.monitor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.device.common.entity.UserBean;
import com.smart.device.common.install.entity.vo.DeviceMonitorVo;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.common.service.UserService;
import com.smart.device.install.feign.DeviceInstallMessageFeign;
import com.smart.device.install.service.monitor.SdDeviceMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/v2/device/control")
public class SdDeviceMonitorController{

    @Resource
    private SdDeviceMonitorService sdDeviceMonitorService;
    @Resource
    private DeviceInstallMessageFeign deviceInstallMessageFeign;
    @Resource
    private UserService userService;

    // 联网监控-按单位查询
    @GetMapping("/companyControl")
    public Result deptControl(HttpServletRequest request, PageDomain page, DeviceMonitorVo vo) {
        try {
            userService.setDataAuth(request,vo);
            IPage iPage = sdDeviceMonitorService.deptControl(page,vo);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_MONITOR);
        }
     }

    //   联网监控-查询
    @GetMapping("/deviceAlarmList")
    public Result deviceAlarmsList(PageDomain page,DeviceMonitorVo vo) {
        try {
            IPage iPage = sdDeviceMonitorService.deviceAlarmsList(page,vo);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_MONITOR);
        }
    }
    //   报警处理
    @PostMapping("/handleAlarm")
    public Result handleAlarm(@RequestBody AlarmVo vo) {
        try {
            if(vo.getDeviceId() == null ){
                return Result.FAIL("设备ID不能为空。");
            }
            deviceInstallMessageFeign.handleAlarm(vo);
            return Result.SUCCESS();
        }catch (Exception e){
            e.printStackTrace();
            return Result.FAIL("报警处理失败");
        }
    }


}