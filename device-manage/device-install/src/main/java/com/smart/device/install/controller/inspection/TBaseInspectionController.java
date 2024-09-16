package com.smart.device.install.controller.inspection;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.device.common.entity.UserBean;
import com.smart.device.common.install.entity.TBaseInspection;
import com.smart.device.common.install.entity.THisInspection;
import com.smart.device.common.service.UserService;
import com.smart.device.install.entity.vo.InspectionVo;
import com.smart.device.install.service.inspection.ITBaseInsBuildService;
import com.smart.device.install.service.inspection.ITBaseInspectionService;
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
 *
 * @author f
 */

@Slf4j
@RestController
@RequestMapping("/api/v2/install/base/inspection")
public class TBaseInspectionController {

    /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBaseInspectionService iTBaseInspectionService;
    @Resource
    private ITBaseInsBuildService itBaseInsBuildService;
    @Resource
    private UserService userService;

    @ApiOperation(value = "巡检计划查询接口")
    @ApiImplicitParam
    @GetMapping("/baseInspectionList")
    public Result baseInspectionList(HttpServletRequest request,PageDomain page, TBaseInspection entity) {
        try {
            UserBean user = userService.getUserByToken(request);
            if(user != null  && user.getDeptId() != null){
                entity.setCompanyId(user.getDeptId());
            }
            log.info("巡检计划查询接口"+JSONObject.toJSONString(entity));
            IPage iPage = iTBaseInspectionService.baseInspectionList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_INSPECTION);
        }
    }

    @ApiOperation(value = "巡检计划添加接口-1")
    @ApiImplicitParam
    @PostMapping("/baseInspectionAdd1")
    public Result baseInspectionAdd(@RequestBody TBaseInspection entity) {
        try {
            log.info("巡检计划添加接口-1"+JSONObject.toJSONString(entity));
            TBaseInspection bean = iTBaseInspectionService.baseInspectionAdd(entity);
            return Result.SUCCESS(bean);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_INSPECTION);
        }
    }

    @ApiOperation(value = "巡检计划添加接口-2")
    @ApiImplicitParam
    @PostMapping("/baseInsBuildFloorAdd2")
    public Result baseInsBuildFloorAdd(@RequestBody InspectionVo entity) {
        try {
            log.info("巡检计划添加接口-2"+JSONObject.toJSONString(entity));
            itBaseInsBuildService.baseInsBuildListAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_HIS_INSPECTION);
        }
    }


    @ApiOperation(value = "巡检计划删除接口")
    @ApiImplicitParam
    @GetMapping("/baseInspectionDel")
    public Result baseInspectionDel(Long id) {
        try {
            log.info("巡检计划删除接口"+JSONObject.toJSONString(id));
            iTBaseInspectionService.baseInspectionDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_INSPECTION);
        }
    }

    @ApiOperation(value = "修改巡检计划接口--修改-第一步 保存")
    @ApiImplicitParam
    @PostMapping("/baseInspectionUpdate")
    public Result baseInspectionUpdate(@RequestBody TBaseInspection entity) {
        try {
            log.info("第一步 保存"+JSONObject.toJSONString(entity));
            iTBaseInspectionService.baseInspectionUpdateAndFirefighter(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_INSPECTION);
        }
    }

    @ApiOperation(value = "根据ID查询巡检计划详情-修改-第一步回显")
    @ApiImplicitParam
    @GetMapping("/selectbaseInspectionDetail")
    public Result selectbaseInspectionByID(Long id) {
        try {
            log.info("第一步回显"+JSONObject.toJSONString(id));
            TBaseInspection vo = iTBaseInspectionService.selectBaseInspectionByID(id);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_INSPECTION);
        }
    }
    /**------基本方法结束-----------------------------------------*/

    @ApiOperation(value = "根据人员查询巡检计划详情-巡检计划创建第三步使用")
    @ApiImplicitParam
    @GetMapping("/insAndInsBuildsList")
    public Result insAndInsBuildsList(HttpServletRequest request,TBaseInspection vo){
        try {
            UserBean user = userService.getUserByToken(request);
            if(user != null  && user.getDeptId() != null){
                vo.setCompanyId(user.getDeptId());
            }
            Thread.sleep(500);
            log.info("巡检计划创建第三步使用"+JSONObject.toJSONString(vo));
            List<TBaseInspection> list = iTBaseInspectionService.insAndInsBuildsList(vo);
            return Result.SUCCESS(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_INSPECTION);
        }
    }

    @ApiOperation(value = "根据人员查询巡检计划详情-小程序使用")
    @ApiImplicitParam
    @GetMapping("/insAndInsBuildsListXCX")
    public Result insAndInsBuildsListXCX(HttpServletRequest request,TBaseInspection vo){
        try {
            UserBean user = userService.getUserByToken(request);
            if(user != null  && user.getDeptId() != null){
                vo.setCompanyId(user.getDeptId());
            }
            vo.setStatus(0);// 查询未完成的巡检计划
            List<TBaseInspection> list = iTBaseInspectionService.insAndInsBuildsList(vo);
            return Result.SUCCESS(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_INSPECTION);
        }
    }



}
