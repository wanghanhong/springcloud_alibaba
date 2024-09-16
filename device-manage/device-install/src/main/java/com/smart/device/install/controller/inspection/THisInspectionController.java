package com.smart.device.install.controller.inspection;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.device.common.entity.UserBean;
import com.smart.device.common.install.entity.TBaseInspection;
import com.smart.device.common.service.UserService;
import com.smart.device.install.entity.vo.HisInspectionVo;
import com.smart.device.install.service.inspection.ITHisInspectionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author f
 */
@Slf4j
@RestController
@RequestMapping("/api/v2/install/his/inspection")
public class THisInspectionController {

    @Resource
    private UserService userService;

    /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITHisInspectionService iTHisInspectionService;

    @ApiOperation(value = "巡检记录添加接口-开始巡检-第一步")
    @ApiImplicitParam
    @PostMapping("/hisInspectionAddOne")
    public Result hisInspectionAddOne(@RequestBody HisInspectionVo entity) {
        try {
            iTHisInspectionService.hisInspectionAddOne(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_HIS_INSPECTION);
        }
    }

    @ApiOperation(value = "巡检记录查询接口")
    @ApiImplicitParam
    @GetMapping("/hisInspectionList")
    public Result hisInspectionList(HttpServletRequest request,PageDomain page, TBaseInspection entity) {
        try {
            UserBean user = userService.getUserByToken(request);
            if(user != null  && user.getDeptId() != null){
                entity.setCompanyId(user.getDeptId());
            }
            log.info("巡检计划查询接口"+ JSONObject.toJSONString(entity));
            IPage iPage = iTHisInspectionService.hisInspectionList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_INSPECTION);
        }
    }



}
