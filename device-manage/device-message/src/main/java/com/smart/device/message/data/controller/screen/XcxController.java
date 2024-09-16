package com.smart.device.message.data.controller.screen;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.entity.UserBean;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.common.service.UserService;
import com.smart.device.message.data.service.TAlarmElectricService;
import com.smart.device.message.data.service.TAlarmSmokeService;
import com.smart.device.message.data.service.TAlarmWaterpressService;
import com.smart.device.message.data.service.screen.XcxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@Api(tags = "")
@Slf4j
@RestController
@RequestMapping("/api/v2/xcx/")
public class XcxController {

    @Resource
    private XcxService xcxService;
    @Resource
    private TAlarmSmokeService alarmSmokeService;
    @Resource
    private TAlarmWaterpressService alarmWaterpressService;
    @Resource
    private TAlarmElectricService alarmElectricService;
    @Resource
    private UserService userService;

    @ApiOperation(value = "报警查询接口")
    @ApiImplicitParam
    @GetMapping("/getAlarmList")
    public Result alarmSmokeList(HttpServletRequest request,DeviceCompanyVo vo, String username) {
        PageDomain page = new PageDomain();
        page.setPageNum(vo.getPageNum());
        page.setPageSize(vo.getPageSize());
        try {
            UserBean user = userService.getUserByToken(request);
            if(user == null){
                return new Result(ResultCode.ERROR_DEVICE_INSTALL_SMOKE.getCode(),"无权限",false);
            }
            if(user != null && user.getIsXcx() != null ){
                if(user.getIsXcx() != null && user.getIsXcx() > 0){
                    vo.setOpUserId(user.getUserId());
                }else{
                    vo.setCompanyId(user.getDeptId());
                }
            }
            IPage iPage = new Page();
            List<AlarmVo> list = new ArrayList<>();
            if(vo.getDeviceType() == null){
                IPage<AlarmVo> ip1 = alarmSmokeService.alarmSmokeList(page,vo);
                if(ip1 != null){
                    list.addAll(ip1.getRecords());
                }
                IPage<AlarmVo> ip2 = alarmElectricService.alarmElectricList(page,vo);
                if(ip2 != null){
                    list.addAll(ip2.getRecords());
                }
                IPage<AlarmVo> ip3 = alarmWaterpressService.alarmWaterpressList(page,vo);
                if(ip3 != null){
                    list.addAll(ip3.getRecords());
                }
                iPage.setRecords(list);
                iPage.setTotal(list.size());
            }else if (DeviceConstant.device_type_waterpress.equals(vo.getDeviceType()) ||
                    DeviceConstant.device_type_liquidlevel.equals(vo.getDeviceType()) ){
                iPage = alarmWaterpressService.alarmWaterpressList(page,vo);
            }else if (DeviceConstant.device_type_electric.equals(vo.getDeviceType()) ){
                iPage = alarmElectricService.alarmElectricList(page,vo);
            }else{
                iPage = alarmSmokeService.alarmSmokeList(page,vo);
            }
            return Result.SUCCESS(new PageResult(iPage));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }

    //   报警处理
    @PostMapping("/handleAlarm")
    public Result handleAlarm(@RequestBody AlarmVo vo) {
        try {
            if(vo.getDeviceId() == null ){
                return Result.ERROR(ResultCode.ERROR_DEVICE_MONITOR);
            }
            xcxService.handleAlarm(vo);
            return Result.SUCCESS();
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_MONITOR);
        }
    }

    @ApiOperation(value = "报警查询接口")
    @ApiImplicitParam
    @GetMapping("/getAlarmListUser")
    public Result alarmSmokeListUser(HttpServletRequest request,DeviceCompanyVo vo,String username) {
        PageDomain page = new PageDomain();
        page.setPageNum(vo.getPageNum());
        page.setPageSize(vo.getPageSize());
        try {
            String token = request.getHeader("Authorization");
            if(token == null){
                return new Result(ResultCode.ERROR_DEVICE_INSTALL_SMOKE.getCode(),"无权限",false);
            }
            UserBean user = userService.getUserByToken(request);
            if(user != null && user.getIsXcx() != null ){
                if(user.getIsXcx() != null && user.getIsXcx() > 0){
                    vo.setOpUserId(user.getUserId());
                }else{
                    vo.setCompanyId(user.getDeptId());
                }
            }
            IPage iPage = new Page();
            List<AlarmVo> list = new ArrayList<>();
            if(vo.getDeviceType() == null){
                IPage<AlarmVo> ip1 = alarmSmokeService.alarmSmokeList(page,vo);
                if(ip1 != null){
                    list.addAll(ip1.getRecords());
                }
                IPage<AlarmVo> ip2 = alarmElectricService.alarmElectricList(page,vo);
                if(ip2 != null){
                    list.addAll(ip2.getRecords());
                }
                IPage<AlarmVo> ip3 = alarmWaterpressService.alarmWaterpressList(page,vo);
                if(ip3 != null){
                    list.addAll(ip3.getRecords());
                }
                iPage.setRecords(list);
                iPage.setTotal(list.size());
            }else if (DeviceConstant.device_type_waterpress.equals(vo.getDeviceType()) ||
                    DeviceConstant.device_type_liquidlevel.equals(vo.getDeviceType()) ){
                iPage = alarmWaterpressService.alarmWaterpressListUser(page,vo);
            }else if (DeviceConstant.device_type_electric.equals(vo.getDeviceType()) ){
                iPage = alarmElectricService.alarmElectricListUser(page,vo);
            }else{
                iPage = alarmSmokeService.alarmSmokeListUser(page,vo);
            }
            return Result.SUCCESS(new PageResult(iPage));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }


}