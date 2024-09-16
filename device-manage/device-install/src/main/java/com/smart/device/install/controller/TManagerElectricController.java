package com.smart.device.install.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.device.common.access.entity.vo.DeviceBaseVo;
import com.smart.device.common.entity.UserBean;
import com.smart.device.common.install.entity.TManagerElectric;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.service.UserService;
import com.smart.device.install.feign.DeviceAccessFeign;
import com.smart.device.install.service.ITManagerElectricService;
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
 * @author 
 */
@Api(tags = "")
@RestController
@RequestMapping("/api/v2/install/electric")
public class TManagerElectricController extends BaseController {

    @Resource
    private ITManagerElectricService itManagerElectricService;
    @Resource
    private DeviceAccessFeign deviceAccessFeign;
    @Resource
    private UserService userService;

    @ApiOperation(value = "智慧用电查询接口")
    @ApiImplicitParam
    @GetMapping("/electricList")
    public Result electricList(HttpServletRequest request, PageDomain page, TManagerElectric entity) {
        try {
            userService.setDataAuth(request,entity);
            IPage iPage = itManagerElectricService.managerElectricList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }

    @ApiOperation(value = "智慧用电添加接口")
    @ApiImplicitParam
    @PostMapping("/electricAdd")
    public Result electricAdd(@RequestBody TManagerElectric entity) {
        try {
            itManagerElectricService.managerElectricAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }


    @ApiOperation(value = "智慧用电删除接口")
    @ApiImplicitParam
    @GetMapping("/electricDel")
    public Result electricDel(Long id) {
        try {
            itManagerElectricService.managerElectricDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }


    @ApiOperation(value = "修改智慧用电接口")
    @ApiImplicitParam
    @PostMapping("/electricUpdate")
    public Result electricUpdate(@RequestBody TManagerElectric entity) {
        try {
            itManagerElectricService.managerElectricUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }


    @ApiOperation(value = "根据IMEI条件查询电力是否存在")
    @ApiImplicitParam
    @GetMapping("/selectElectric")
    public Result selectElectric(TManagerElectric entity) {
        try {
            if(entity.getImei() == null){
                return new Result(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC.getCode(),"IMEI不能为空",false);
            }
            DeviceBaseVo deviceBaseVo = deviceAccessFeign.selectDeviceElectric(entity.getImei());
            if(deviceBaseVo != null){
                // 再次检测是否已经绑定
                TManagerElectric exist = itManagerElectricService.getManagerElectric(entity);
                if(exist !=null){
                    return new Result(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC.getCode(),"该设备已经绑定",false,exist);
                }else{
                    return Result.SUCCESS(deviceBaseVo);
                }
            }else {
                return new Result(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC.getCode(),"查询无数据",false);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }

    @ApiOperation(value = "根据ID查询智慧用电详情")
    @ApiImplicitParam
    @GetMapping("/selectElectricDetail")
    public Result selectElectricByID(Long id) {
        try {
            TManagerElectric vo = itManagerElectricService.selectElectricByID(id);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }

    @ApiOperation(value = "根据deviceID 查询 设备关联之联系人")
    @ApiImplicitParam
    @GetMapping("/electricPerSonByDeviceId")
    public DeviceCompanyVo electricPerSonByDeviceId(Long deviceId) {
        try {
            DeviceCompanyVo vo = itManagerElectricService.electricPerSonByDeviceId(deviceId);
            return vo;
        } catch (Exception e) {
            e.printStackTrace();
            return new DeviceCompanyVo();
        }
    }

    @ApiOperation(value = "修改智慧用电接口")
    @ApiImplicitParam
    @PostMapping("/updateElectricStatus")
    public Result updateElectricStatus(@RequestBody TManagerElectric entity) {
        try {
            itManagerElectricService.updateElectricStatus(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_DEVICE_INSTALL_ELECTRIC);
        }
    }

}
