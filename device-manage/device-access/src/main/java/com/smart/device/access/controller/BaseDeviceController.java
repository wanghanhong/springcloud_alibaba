package com.smart.device.access.controller;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONObject;
import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import com.smart.device.common.access.entity.vo.DeviceAccessVO;
import com.smart.device.access.entity.vo.ImportDeviceVO;
import com.smart.device.access.service.SdDeviceService;
import com.smart.device.common.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Map;

/**
 * @author ms
 * @Time 2020/5/26
 */
@Api(tags = "")
@RestController
@Slf4j
@RequestMapping("/api/v2/device")
public class BaseDeviceController extends BaseController {

    @Autowired
    private SdDeviceService sDeviceService;
    @Resource
    private UserService userService;

    @ApiOperation(value = "设备导入")
    @PostMapping("/devicesImport")
    public Result devicesImport(HttpServletRequest request,@RequestParam  MultipartFile file,@RequestParam Map map) {
        Result result = Result.SUCCESS();
        try {
            ImportDeviceVO deviceVO = JSONObject.parseObject(JSONObject.toJSONString(map), ImportDeviceVO.class);
            InputStream is = file.getInputStream();
            result = sDeviceService.devicesImport(request,is, deviceVO);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("读取文件失败", e.getMessage());
            return Result.FAIL(HttpStatus.HTTP_INTERNAL_ERROR, "读取文件失败");
        }
        return result;
    }

    /**
     * 分页查询不同设备信息列表
     *
     * @param
     * @return
     */
    @ApiOperation(value = "查询设备列表")
    @GetMapping("/selectDevicesList")
    public Result selectDevicesList(HttpServletRequest request, DeviceAccessVO entity) {
        try {
            userService.setDataAuth(request,entity);
            return sDeviceService.selectDevicesList(entity);
        } catch (Exception e) {
            log.error("查询失败", e.getMessage());
            return Result.FAIL(HttpStatus.HTTP_INTERNAL_ERROR, "查询失败");
        }
    }

    //同步设备到ctwing平台
    @PostMapping("/ctwingDeviceAdd")
    public Result ctwingDeviceAdd(@RequestBody DeviceAccessVO deviceVO) {
        try {
            return sDeviceService.ctwingDeviceAdd(deviceVO);
        } catch (Exception e) {
            log.error("同步数据失败！", e);
            return Result.FAIL(HttpStatus.HTTP_INTERNAL_ERROR, "同步数据失败");
        }
    }

    @ApiOperation(value = "设备删除")
    @GetMapping("/devicesDelete")
    public Result devicesDelete(DeviceAccessVO deviceVO) {
        try {
            sDeviceService.devicesDelete(deviceVO);
            return Result.SUCCESS("操作成功");
        } catch (Exception e) {
            log.error("查询失败", e.getMessage());
            return Result.FAIL(HttpStatus.HTTP_INTERNAL_ERROR, "查询失败");
        }
    }


}
