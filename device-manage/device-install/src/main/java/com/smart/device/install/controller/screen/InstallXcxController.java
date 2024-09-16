package com.smart.device.install.controller.screen;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.access.entity.TDeviceDict;
import com.smart.device.common.constant.DeviceConstant;
import com.smart.device.common.entity.UserBean;
import com.smart.device.common.install.entity.TManagerElectric;
import com.smart.device.common.install.entity.TManagerSmoke;
import com.smart.device.common.install.entity.TManagerWaterpress;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.service.UserService;
import com.smart.device.install.feign.DeviceAccessFeign;
import com.smart.device.install.service.ITManagerElectricService;
import com.smart.device.install.service.ITManagerSmokeService;
import com.smart.device.install.service.ITManagerWaterpressService;
import com.smart.device.install.service.screen.InstallXcxService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v2/install/xcx/")
public class InstallXcxController {

    @Resource
    private DeviceAccessFeign deviceAccessFeign;

    @Resource
    private InstallXcxService installXcxService;
    @Resource
    private ITManagerSmokeService itManagerSmokeService;
    @Resource
    private ITManagerWaterpressService itManagerWaterpressService;
    @Resource
    private ITManagerElectricService itManagerElectricService;
    @Resource
    private UserService userService;

    @GetMapping("/queryDeviceParentTypeList")
    public Result queryDeviceParentTypeList() {
        try {
            List<TDeviceDict> list = deviceAccessFeign.queryDeviceParentTypeList();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            log.error("查询失败", e.getMessage());
            return Result.FAIL(HttpStatus.HTTP_INTERNAL_ERROR, "查询失败");
        }
    }


    @GetMapping("/queryInstallDevicesList")
    public Result queryInstallDevicesList(HttpServletRequest request, PageDomain page, DeviceCompanyVo vo) {
        try {
            userService.setDataAuth(request,vo);
            IPage<DeviceCompanyVo> ipage = installXcxService.queryInstallDevicesList(page,vo);
            return Result.SUCCESS(ipage);
        } catch (Exception e) {
            log.error("查询失败", e.getMessage());
            return Result.FAIL(HttpStatus.HTTP_INTERNAL_ERROR, "查询失败");
        }
    }
    // 个人注册用户-保存设备安装
    @ApiOperation(value = "设备安装接口")
    @ApiImplicitParam
    @PostMapping("/devicesAdd")
    public Result devicesAdd(HttpServletRequest request,@RequestBody DeviceCompanyVo vo) {
        try {
            UserBean user = userService.getUserByToken(request);
            if (DeviceConstant.device_type_smoke.equals(vo.getDeviceType()) ||
                    DeviceConstant.device_type_gas.equals(vo.getDeviceType()) ){
                TManagerSmoke entity = new TManagerSmoke();
                setDeviceAttr1(vo, entity);
                if(user != null && user.getIsXcx() != null ){
                    if(user.getIsXcx() != null && user.getIsXcx() > 0){
                        entity.setOpUserId(user.getUserId());
                    }else{
                        entity.setCompanyId(user.getDeptId());
                    }
                }
                itManagerSmokeService.managerSmokeAdd(entity);
            }else if (DeviceConstant.device_type_waterpress.equals(vo.getDeviceType()) ||
                    DeviceConstant.device_type_liquidlevel.equals(vo.getDeviceType()) ){
                TManagerWaterpress entity = new TManagerWaterpress();
                setDeviceAttr2(vo, entity);
                if(user != null && user.getIsXcx() != null ){
                    if(user.getIsXcx() != null && user.getIsXcx() > 0){
                        entity.setOpUserId(user.getUserId());
                    }else{
                        entity.setCompanyId(user.getDeptId());
                    }
                }
                itManagerWaterpressService.managerWaterpressAdd(entity);
            }else if (DeviceConstant.device_type_electric.equals(vo.getDeviceType()) ){
                TManagerElectric entity = new TManagerElectric();
                setDeviceAttr3(vo, entity);
                if(user != null && user.getIsXcx() != null ){
                    if(user.getIsXcx() != null && user.getIsXcx() > 0){
                        entity.setOpUserId(user.getUserId());
                    }else{
                        entity.setCompanyId(user.getDeptId());
                    }
                }
                itManagerElectricService.managerElectricAdd(entity);
            }
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }
    // 个人注册用户-保存设备安装
    @ApiOperation(value = "设备安装接口")
    @ApiImplicitParam
    @PostMapping("/devicesAddSP")
    public Result devicesAddSP(HttpServletRequest request,@RequestBody DeviceCompanyVo vo) {
        try {
            UserBean user = userService.getUserByToken(request);
            // 监测 设备存在
            PageDomain page = new PageDomain();
            IPage<DeviceCompanyVo> ipage = installXcxService.queryInstallDevicesList(page,vo);
            Integer deviceType = 11;
            // 此处从数据库查询了 设备类型
            // 检测 设备已安装
            if(ipage != null && ipage.getRecords() != null && ipage.getRecords().size() > 0){
                DeviceCompanyVo dcv = ipage.getRecords().get(0);
                deviceType = dcv.getParentType();
            }else{
//                return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_SMOKE,"设备不存在，请重新输入!");

            }
            String res = "";
            if (DeviceConstant.device_type_smoke.equals(deviceType) ||
                    DeviceConstant.device_type_gas.equals(deviceType) ){
                TManagerSmoke entity = new TManagerSmoke();
                setDeviceAttr1(vo, entity);
                if(user != null && user.getIsXcx() != null ){
                    if(user.getIsXcx() > 0){
                        entity.setOpUserId(user.getUserId());
                    }else{
                        entity.setCompanyId(user.getDeptId());
                    }
                }
                res = itManagerSmokeService.managerSmokeAddSP(entity);
            }else if (DeviceConstant.device_type_waterpress.equals(deviceType) ||
                    DeviceConstant.device_type_liquidlevel.equals(deviceType) ){
                TManagerWaterpress entity = new TManagerWaterpress();
                setDeviceAttr2(vo, entity);
                if(user != null && user.getIsXcx() != null ){
                    if(user.getIsXcx() != null && user.getIsXcx() > 0){
                        entity.setOpUserId(user.getUserId());
                    }else{
                        entity.setCompanyId(user.getDeptId());
                    }
                }
                itManagerWaterpressService.managerWaterpressAdd(entity);
            }else if (DeviceConstant.device_type_electric.equals(deviceType) ){
                TManagerElectric entity = new TManagerElectric();
                setDeviceAttr3(vo, entity);
                if(user != null && user.getIsXcx() != null ){
                    if(user.getIsXcx() != null && user.getIsXcx() > 0){
                        entity.setOpUserId(user.getUserId());
                    }else{
                        entity.setCompanyId(user.getDeptId());
                    }
                }
                itManagerElectricService.managerElectricAdd(entity);
            }
            return new Result(200,res,true);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_SMOKE);
        }
    }


    private void setDeviceAttr1(DeviceCompanyVo vo, TManagerSmoke entity) {
        entity.setDeviceName(vo.getDeviceName());
        entity.setDeviceType(vo.getDeviceType());
        entity.setDeviceTypeName(vo.getDeviceTypeName());
        entity.setImei(vo.getImei());

        entity.setCompanyId(vo.getCompanyId());
        entity.setCompanyName(vo.getCompanyName());
        entity.setBuildingId(vo.getBuildingId());
        entity.setBuildingName(vo.getBuildingName());
        entity.setBuildingFloor(vo.getBuildingFloor());
        entity.setLocation(vo.getLocation());

        entity.setProvince(vo.getProvince());
        entity.setCity(vo.getCity());
        entity.setCounty(vo.getCounty());
        entity.setTown(vo.getTown());
        entity.setHousing(vo.getHousing());
    }
    private void setDeviceAttr2(DeviceCompanyVo vo,TManagerWaterpress entity) {
        entity.setDeviceName(vo.getDeviceName());
        entity.setDeviceType(vo.getDeviceType());
        entity.setDeviceTypeName(vo.getDeviceTypeName());
        entity.setImei(vo.getImei());

        entity.setCompanyId(vo.getCompanyId());
        entity.setCompanyName(vo.getCompanyName());
        entity.setBuildingId(vo.getBuildingId());
        entity.setBuildingName(vo.getBuildingName());
        entity.setBuildingFloor(vo.getBuildingFloor());
        entity.setLocation(vo.getLocation());

        entity.setProvince(vo.getProvince());
        entity.setCity(vo.getCity());
        entity.setCounty(vo.getCounty());
        entity.setTown(vo.getTown());
        entity.setHousing(vo.getHousing());

    }
    private void setDeviceAttr3(DeviceCompanyVo vo,TManagerElectric entity) {
        entity.setDeviceName(vo.getDeviceName());
        entity.setDeviceType(vo.getDeviceType());
        entity.setDeviceTypeName(vo.getDeviceTypeName());
        entity.setImei(vo.getImei());

        entity.setCompanyId(vo.getCompanyId());
        entity.setCompanyName(vo.getCompanyName());
        entity.setBuildingId(vo.getBuildingId());
        entity.setBuildingName(vo.getBuildingName());
        entity.setBuildingFloor(vo.getBuildingFloor());
        entity.setLocation(vo.getLocation());

        entity.setProvince(vo.getProvince());
        entity.setCity(vo.getCity());
        entity.setCounty(vo.getCounty());
        entity.setTown(vo.getTown());
        entity.setHousing(vo.getHousing());
    }

}
