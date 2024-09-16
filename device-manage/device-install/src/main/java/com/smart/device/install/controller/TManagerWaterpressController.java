package com.smart.device.install.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.entity.UserBean;
import com.smart.device.common.install.entity.TManagerWaterpress;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.service.UserService;
import com.smart.device.install.feign.DeviceAccessFeign;
import com.smart.device.install.service.ITManagerWaterpressService;
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
@RequestMapping("/api/v2/install/waterpress")
public class TManagerWaterpressController {

    @Resource
    private ITManagerWaterpressService itManagerWaterpressService;
    @Resource
    private DeviceAccessFeign deviceAccessFeign;
    @Resource
    private UserService userService;

    @ApiOperation(value = "水利查询接口")
    @ApiImplicitParam
    @GetMapping("/waterpressList")
    public Result waterpressList(HttpServletRequest request, PageDomain page, TManagerWaterpress entity) {
        try {
            userService.setDataAuth(request,entity);
            IPage iPage = itManagerWaterpressService.managerWaterpressList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }

    @ApiOperation(value = "水利添加接口")
    @ApiImplicitParam
    @PostMapping("/waterpressAdd")
    public Result waterpressAdd(@RequestBody TManagerWaterpress entity) {
        try {
            itManagerWaterpressService.managerWaterpressAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }


    @ApiOperation(value = "水利删除接口")
    @ApiImplicitParam
    @GetMapping("/waterpressDel")
    public Result waterpressDel(Long id) {
        try {
            itManagerWaterpressService.managerWaterpressDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }

    @ApiOperation(value = "修改水利接口")
    @ApiImplicitParam
    @PostMapping("/waterpressUpdate")
    public Result waterpressUpdate(@RequestBody TManagerWaterpress entity) {
        try {
            itManagerWaterpressService.managerWaterpressUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }

    @ApiOperation(value = "根据IMEI条件查询水压是否存在")
    @ApiImplicitParam
    @GetMapping("/selectWaterpress")
    public Result selectWaterpress(TManagerWaterpress entity) {
        try {
            if(entity.getImei() == null){
                return new Result(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS.getCode(),"IMEI不能为空",false);
            }
            DeviceBaseVo deviceBaseVo = deviceAccessFeign.selectDeviceWaterpress(entity.getImei());
            if(deviceBaseVo != null){
                // 再次检测是否已经绑定
                TManagerWaterpress exist = itManagerWaterpressService.getManagerWaterpress(entity);
                if(exist !=null){
                    return new Result(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS.getCode(),"该设备已经绑定",false,exist);
                }else{
                    return Result.SUCCESS(deviceBaseVo);
                }
            }else {
                return new Result(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS.getCode(),"查询无数据",false);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }

    @ApiOperation(value = "根据ID查询水压详情")
    @ApiImplicitParam
    @GetMapping("/selectWaterpressDetail")
    public Result selectWaterpressByID(Long id) {
        try {
            TManagerWaterpress vo = itManagerWaterpressService.selectWaterpressByID(id);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_WATERPRESS);
        }
    }

    // 根据deviceID 查询 设备关联之联系人
    @ApiOperation(value = "根据deviceID 查询 设备关联之联系人")
    @ApiImplicitParam
    @GetMapping("/waterpressPerSonByDeviceId")
    public DeviceCompanyVo waterpressPerSonByDeviceId(Long deviceId) {
        try {
            DeviceCompanyVo vo = itManagerWaterpressService.waterpressPerSonByDeviceId(deviceId);
            return vo;
        } catch (Exception e) {
            e.printStackTrace();
            return new DeviceCompanyVo();
        }
    }

    @ApiOperation(value = "修改水压接口")
    @ApiImplicitParam
    @PostMapping("/updateWaterpressStatus")
    public Result updateWaterpressStatus(@RequestBody TManagerWaterpress entity) {
        try {
            itManagerWaterpressService.updateWaterpressStatus(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }
    
}
