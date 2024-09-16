package com.smart.device.install.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.device.common.install.entity.TBaseBuilding;
import com.smart.device.common.service.UserService;
import com.smart.device.install.service.ITBaseBuildingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/api/v2/install/base/building")
public class TBaseBuildingController {

    /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBaseBuildingService iTBaseBuildingService;
    @Resource
    private UserService userService;

    @ApiOperation(value = "建筑物查询接口")
    @ApiImplicitParam
    @GetMapping("/baseBuildingList")
    public Result baseBuildingList(HttpServletRequest request,PageDomain page, TBaseBuilding entity, String username) {
        try {
            userService.setDataAuth(request,entity);
            IPage iPage = iTBaseBuildingService.baseBuildingList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_BUILDING);
        }
    }

    @ApiOperation(value = "建筑物添加接口")
    @ApiImplicitParam
    @PostMapping("/baseBuildingAdd")
    public Result baseBuildingAdd(@RequestBody TBaseBuilding entity) {
        try {
            log.info("添加原始数据");
            log.info(JSONObject.toJSONString(entity));
            iTBaseBuildingService.baseBuildingAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_BUILDING);
        }
    }

    @ApiOperation(value = "建筑物删除接口")
    @ApiImplicitParam
    @GetMapping("/baseBuildingDel")
    public Result baseBuildingDel(Long id) {
        try {
            int res = iTBaseBuildingService.baseBuildingDel(id);
            if(res > 0){
                return Result.SUCCESS();
            }else{
                return new Result(54101,"该建筑物已绑定设备，请先解绑设备。",false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_BUILDING);
        }
    }

    @ApiOperation(value = "修改建筑物接口")
    @ApiImplicitParam
    @PostMapping("/baseBuildingUpdate")
    public Result baseBuildingUpdate(@RequestBody TBaseBuilding entity) {
        try {
            iTBaseBuildingService.baseBuildingUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_BUILDING);
        }
    }

    @ApiOperation(value = "根据ID查询建筑物详情")
    @ApiImplicitParam
    @GetMapping("/baseBuildingDetail")
    public Result selectBaseBuildingByID(Long id) {
        try {
            TBaseBuilding vo = iTBaseBuildingService.selectBaseBuildingByID(id);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_BUILDING);
        }
    }
    /**------基本方法结束-----------------------------------------*/

    @ApiOperation(value = "所属建筑物-下拉选择")
    @ApiImplicitParam
    @GetMapping("/selectBuildings")
    public Result selectBuildings(HttpServletRequest request,TBaseBuilding entity) {
        try {
            userService.setDataAuth(request,entity);
            List<TBaseBuilding> list = iTBaseBuildingService.selectBuildings(entity);
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_BUILDING);
        }
    }

    @ApiOperation(value = "楼层-下拉选择")
    @ApiImplicitParam
    @GetMapping("/selectFloors")
    public Result selectFloors(String id) {
        try {
            List<Integer> list = iTBaseBuildingService.selectFloors(id);
            return Result.SUCCESS(list);
        } catch (Exception e) {
            log.error("获取楼层失败！", e);
            return Result.ERROR(ResultCode.ERROR_MANAGER_BUILDING);
        }
    }

    @ApiOperation(value = "根据单位查询建筑物列表，然后在查询设备详情")
    @ApiImplicitParam
    @GetMapping("/baseBuildingAndSonList")
    public Result baseBuildingAndSonList(Long companyId,Long buildingId) {
        try {
            List<TBaseBuilding> list = iTBaseBuildingService.baseBuildingAndSonList(companyId,buildingId);
            return Result.SUCCESS(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_BUILDING);
        }
    }


    @GetMapping("/hello")
    public String testfeign() {
        return "ok";
    }

}
