package com.smart.device.install.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.entity.UserBean;
import com.smart.device.common.install.entity.TManagerSmoke;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.service.UserService;
import com.smart.device.install.feign.DeviceAccessFeign;
import com.smart.device.install.service.ITManagerSmokeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author f
 */
@Api(tags = "")
@RestController
@RequestMapping("/api/v2/install/smoke")
public class TManagerSmokeController {

    @Resource
    private ITManagerSmokeService itManagerSmokeService;
    @Resource
    private DeviceAccessFeign deviceAccessFeign;
    @Resource
    private UserService userService;

    @ApiOperation(value = "烟感查询接口")
    @ApiImplicitParam
    @GetMapping("/smokeList")
    public Result SmokeList(HttpServletRequest request, PageDomain page, TManagerSmoke entity) {
        try {
            UserBean user = userService.setDataAuth(request,entity);
            if(user == null){
                return new Result(ResultCode.ERROR_DEVICE_INSTALL_SMOKE.getCode(),"无权限",false);
            }
            IPage iPage = itManagerSmokeService.managerSmokeList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_SMOKE);
        }
    }

    @ApiOperation(value = "烟感添加接口")
    @ApiImplicitParam
    @PostMapping("/smokeAdd")
    public Result smokeAdd(@RequestBody TManagerSmoke entity) {
        try {
            itManagerSmokeService.managerSmokeAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_SMOKE);
        }
    }

    @ApiOperation(value = "烟感删除接口")
    @ApiImplicitParam
    @GetMapping("/smokeDel")
    public Result smokeDel(Long id) {
        try {
            itManagerSmokeService.managerSmokeDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_SMOKE);
        }
    }

    @ApiOperation(value = "修改烟感接口")
    @ApiImplicitParam
    @PostMapping("/smokeUpdate")
    public Result smokeUpdate(@RequestBody TManagerSmoke entity) {
        try {
            itManagerSmokeService.managerSmokeUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_SMOKE);
        }
    }

    @ApiOperation(value = "根据IMEI条件查询烟感是否存在")
    @ApiImplicitParam
    @GetMapping("/selectSmoke")
    public Result selectSmoke(TManagerSmoke entity) {
        try {
            if(entity.getImei() == null){
                return new Result(ResultCode.ERROR_DEVICE_INSTALL_SMOKE.getCode(),"IMEI不能为空",false);
            }
            DeviceBaseVo deviceBaseVo = deviceAccessFeign.selectDeviceSmoke(entity.getImei());
            if(deviceBaseVo != null){
                // 再次检测是否已经绑定
                TManagerSmoke exist = itManagerSmokeService.getManagerSmoke(entity);
                if(exist !=null){
                    return new Result(ResultCode.SUCCESS.getCode(),"该设备已经绑定",false,exist);
                }else{
                    return Result.SUCCESS(deviceBaseVo);
                }
            }else {
                return new Result(ResultCode.ERROR_DEVICE_INSTALL_SMOKE.getCode(),"查询无数据",false);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_SMOKE);
        }
    }

    @ApiOperation(value = "根据ID查询烟感详情")
    @ApiImplicitParam
    @GetMapping("/selectSmokeDetail")
    public Result selectSmokeByID(Long id) {
        try {
            TManagerSmoke vo = itManagerSmokeService.selectSmokeByID(id);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_SMOKE);
        }
    }

    @ApiOperation(value = "查询 设备关联之联系人")
    @ApiImplicitParam
    @GetMapping("/smokePerSonByDeviceId")
    public DeviceCompanyVo smokePerSonByDeviceId(Long deviceId) {
        try {
            DeviceCompanyVo vo = itManagerSmokeService.smokePerSonByDeviceId(deviceId);
            return vo;
        } catch (Exception e) {
            e.printStackTrace();
            return new DeviceCompanyVo();
        }
    }

    @ApiOperation(value = "修改烟感接口")
    @ApiImplicitParam
    @PostMapping("/updateSmokeStatus")
    public Result updateSmokeStatus(@RequestBody TManagerSmoke entity) {
        try {
            itManagerSmokeService.updateSmokeStatus(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }

}
