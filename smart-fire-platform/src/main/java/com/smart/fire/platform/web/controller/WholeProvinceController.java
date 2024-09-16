package com.smart.fire.platform.web.controller;

import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.device.common.install.entity.vo.ScreenProvinceVo;
import com.smart.fire.platform.web.entity.vo.ScreenCount;
import com.smart.fire.platform.web.entity.vo.WholeProvinceVo;
import com.smart.fire.platform.web.feign.DeviceInstallFeign;
import com.smart.fire.platform.web.feign.PublicizeManageFeign;
import com.smart.fire.platform.web.service.IWholeProvinceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: wueryong
 * @Date: 2020/6/9 15
 * @Description:
 */
@Api(tags = "全省大屏预览")
@RestController
@RequestMapping("/api/v2/screen/")
public class WholeProvinceController {

    @Resource
    private IWholeProvinceService iWholeProvinceService;
    @Resource
    private PublicizeManageFeign publicizeManageFeign;

    /**
     * 1 各个地市-健康指数
     * 2 各个地市-安装比例
     * 2 各个地市-设备故障报警信息
     *
     * @return
     */
    @ApiOperation("各个地市-健康指数/安装比例总量/设备故障报警信息")
    @GetMapping("/deviceInfo")
    public Result getWholeProvinceDeviceInfo() {
        List<WholeProvinceVo> info = iWholeProvinceService.getWholeProvinceDeviceInfo();
        return Result.SUCCESS(info);
    }

    /**
     * 整体数据
     * 消防单位机构
     * 接入设备总数
     * 全平台用户数
     * @return
     */
    @ApiOperation("安装总数")
    @GetMapping("/getWhole")
    public Result getWhole() {
        try {
            ScreenCount screenCount = iWholeProvinceService.getAll();
            return Result.SUCCESS(screenCount);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation("城市/安装比例总量")
    @GetMapping("/getCity")
    public Result getCity() {
        try {
            List<ScreenProvinceVo> listdata = iWholeProvinceService.getCity();
            return Result.SUCCESS(listdata);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }

    }

    @ApiOperation("大屏提示信息")
    @GetMapping("/getNotify")
    public Result getNotify() {
        try {
            List<Map<String,Object>> list = publicizeManageFeign.queryNoticeListLimit();
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }

    }




}
