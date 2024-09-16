package com.smart.device.message.data.controller.screen;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.domain.Result;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.entity.UserBean;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.install.entity.vo.ScreenMainVo;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.common.service.UserService;
import com.smart.device.message.data.service.screen.ScreenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(tags = "")
@Slf4j
@RestController
@RequestMapping("/api/v2/message/base/screen/")
public class ScreenController {

    @Resource
    private ScreenService screenService;
    @Resource
    private UserService userService;

    @ApiOperation(value = "联网单位分布")
    @GetMapping("/internetDept")
    public Result internetDept(HttpServletRequest request,DeviceCompanyVo vo) {
        userService.setDataAuth(request,vo);
        ScreenMainVo res = screenService.internetDept(vo);
        return Result.SUCCESS(res);
    }

    @ApiOperation(value = "告警趋势")
    @GetMapping("/deviceNewAlarmDay")
    public Result deviceNewAlarmDay(HttpServletRequest request,DeviceCompanyVo vo) {
        userService.setDataAuth(request,vo);
        List<Map<String,Object>> res = screenService.deviceNewAlarmDay(vo);
        return Result.SUCCESS(res);
    }

    @ApiOperation(value = "设备趋势烟感")
    @GetMapping("/deviceNewDay")
    public Result deviceNewDay(HttpServletRequest request,DeviceCompanyVo vo) {
        userService.setDataAuth(request,vo);
        List<Map<String,Object>> res = screenService.deviceNewDay(vo);
        return Result.SUCCESS(res);
    }

    @ApiOperation(value = "报警实时数据统计")
    @GetMapping("/alarmState")
    public Result alarmState(HttpServletRequest request,DeviceCompanyVo vo) {
        userService.setDataAuth(request,vo);
        List<Map<String,Object>> res = screenService.alarmState(vo);
        return Result.SUCCESS(res);
    }
    @ApiOperation(value = "获取单位数、设备报警数、设备数")
    @GetMapping("/deviceFaultDay")
    public Result deviceFaultDay(HttpServletRequest request,DeviceCompanyVo vo) {
        userService.setDataAuth(request,vo);
        List<Map<String,Object>> res = screenService.deviceFaultDay(vo);
        return Result.SUCCESS(res);
    }

    @ApiOperation(value = "获取单位数详情")
    @GetMapping("/deptInfoDay")
    public Result deptInfoDay(HttpServletRequest request,DeviceCompanyVo vo) {
        userService.setDataAuth(request,vo);
        List<DeviceCompanyVo> res = screenService.deptInfoDay(vo);
        return Result.SUCCESS(res);
    }

    @ApiOperation(value = "获取设备数详情")
    @GetMapping("/deviceInfoDay")
    public Result deviceInfoDay(HttpServletRequest request,DeviceCompanyVo vo) {
        userService.setDataAuth(request,vo);
        List<DeviceCompanyVo> res = screenService.deviceInfoDay(vo);
        return Result.SUCCESS(res);
    }

    @ApiOperation(value = "获取报警数详情")
    @GetMapping("/alarmInfoDay")
    public Result alarmInfoDay(HttpServletRequest request,DeviceCompanyVo vo) {
        userService.setDataAuth(request,vo);
        List<DeviceCompanyVo> res = screenService.alarmInfoDay(vo);
        return Result.SUCCESS(res);
    }
    @ApiOperation(value = "获取历史警情单位统计")
    @GetMapping("/alarmDeptDay")
    public Result alarmDeptDay(HttpServletRequest request,DeviceCompanyVo vo) {
        userService.setDataAuth(request,vo);
        List<Map<String,Object>> res = screenService.alarmDeptDay(vo);
        return Result.SUCCESS(res);
    }

    @ApiOperation(value = "获取语音呼叫统计")
    @GetMapping("/voiceCall")
    public Result voiceCall(HttpServletRequest request,DeviceCompanyVo vo) {
        userService.setDataAuth(request,vo);
        List<Map<String,Object>> res = screenService.voiceCall(vo);
        return Result.SUCCESS(res);
    }

    @ApiOperation(value = "获取单位处理列表")
    @GetMapping("/deptStateList")
    public Result deptStateList(HttpServletRequest request,DeviceCompanyVo vo) {
        userService.setDataAuth(request,vo);
        List<DeviceCompanyVo> res = screenService.deptStateList(vo);
        return Result.SUCCESS(res);
    }

    /****************-地图上展示------------------------------------------------------*/
    protected Map<String, Object> getDataTable(IPage<?> pageInfo) {
        Map<String, Object> rspData = new HashMap<>(16);
        rspData.put("rows", pageInfo.getRecords());
        rspData.put("total", pageInfo.getTotal());
        return rspData;
    }
    @ApiOperation(value = "获取在线离线统计")
    @GetMapping("/MapDevicesGroupByLL")
    public Result MapDevicesGroupByLL(HttpServletRequest request,DeviceCompanyVo vo) {
        try {
            userService.setDataAuth(request,vo);
            Map<String, Object> dataTable = getDataTable(screenService.MapDevicesGroupByLL(vo) );
            return Result.SUCCESS(dataTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.SUCCESS(null);
    }

    @ApiOperation(value = "获取在线离线详细")
    @PostMapping("/MapDevicesDetailByLL")
    public Result MapDevicesDetailByLL(PageDomain page,@RequestBody AlarmVo vo) {
        Map<String, Object> dataTable = new HashMap<>();
        dataTable =getDataTable(screenService.MapDevicesDetailByID(page,vo));
        return Result.SUCCESS(dataTable);
    }

    @GetMapping("/screenSendPhone")
    public Result screenSendPhone(DeviceCompanyVo vo) {
        try {
            screenService.screenSendPhone(vo);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.SUCCESS(null);
    }


}