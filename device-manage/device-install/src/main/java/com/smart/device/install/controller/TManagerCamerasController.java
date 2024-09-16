package com.smart.device.install.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.entity.UserBean;
import com.smart.device.common.install.entity.TManagerCameras;
import com.smart.device.common.service.UserService;
import com.smart.device.install.feign.DeviceAccessFeign;
import com.smart.device.install.service.ITManagerCamerasService;
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
import java.util.List;

/**
 * @author f
 */
@Api(tags = "")
@RestController
@RequestMapping("/api/v2/install/cameras")
public class TManagerCamerasController {

    @Resource
    private ITManagerCamerasService itManagerCamerasService;
    @Resource
    private DeviceAccessFeign deviceAccessFeign;
    @Resource
    private UserService userService;

    @ApiOperation(value = "摄像头查询接口")
    @ApiImplicitParam
    @GetMapping("/camerasList")
    public Result camerasList(HttpServletRequest request, PageDomain page, TManagerCameras entity) {
        try {
            userService.setDataAuth(request,entity);
            IPage iPage = itManagerCamerasService.managerCamerasList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_CAMERAS);
        }
    }

    @ApiOperation(value = "摄像头添加接口")
    @ApiImplicitParam
    @PostMapping("/camerasAdd")
    public Result camerasAdd(@RequestBody TManagerCameras entity) {
        try {
            itManagerCamerasService.managerCamerasAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_CAMERAS);
        }
    }


    @ApiOperation(value = "摄像头删除接口")
    @ApiImplicitParam
    @GetMapping("/camerasDel")
    public Result camerasDel(Long id) {
        try {
            itManagerCamerasService.managerCamerasDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_CAMERAS);
        }
    }

    @ApiOperation(value = "修改摄像头接口")
    @ApiImplicitParam
    @PostMapping("/camerasUpdate")
    public Result camerasUpdate(@RequestBody TManagerCameras entity) {
        try {
            itManagerCamerasService.managerCamerasUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_CAMERAS);
        }
    }

    @ApiOperation(value = "根据IMEI条件查询摄像头是否存在")
    @ApiImplicitParam
    @GetMapping("/selectCameras")
    public Result selectCameras(TManagerCameras entity) {
        try {
            if(entity.getSn() == null){
                return new Result(ResultCode.ERROR_DEVICE_INSTALL_CAMERAS.getCode(),"设备号不能为空",false);
            }
            DeviceBaseVo deviceBaseVo = deviceAccessFeign.selectDeviceCameras(entity.getSn());
            if(deviceBaseVo != null){
                // 再次检测是否已经绑定
                TManagerCameras exist = itManagerCamerasService.getManagerCameras(entity);
                if(exist !=null){
                    return new Result(ResultCode.ERROR_DEVICE_INSTALL_CAMERAS.getCode(),"该设备已经绑定",false,exist);
                }else{
                    return Result.SUCCESS(deviceBaseVo);
                }
            }else {
                return new Result(ResultCode.ERROR_DEVICE_INSTALL_CAMERAS.getCode(),"查询无数据",false);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_CAMERAS);
        }
    }

    @ApiOperation(value = "根据ID查询摄像头详情")
    @ApiImplicitParam
    @GetMapping("/selectCamerasDetail")
    public Result selectCamerasByID(Long id) {
        try {
            TManagerCameras entity = itManagerCamerasService.selectCamerasByID(id);
            return Result.SUCCESS(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_CAMERAS);
        }
    }

    @ApiOperation(value = "获取所有摄像头接口")
    @ApiImplicitParam
    @GetMapping("/camerasListAll")
    public Result camerasList(TManagerCameras entity) {
        try {
            List<TManagerCameras> list = itManagerCamerasService.camerasListNoPage(entity);
            return Result.SUCCESS(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_CAMERAS);
        }
    }

}
