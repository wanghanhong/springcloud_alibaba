package com.smart.brd.manage.base.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TVaccinePrevention;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITVaccinePreventionService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author
 */
@RestController
@Api(tags = "疫苗防疫")
@RequestMapping("/api/v1/brd/tvaccineprevention")
public class TVaccinePreventionController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITVaccinePreventionService tVaccinePreventionService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tVaccinePreventionList")
    @RequiresPermissions("preventionPig:view")
    public Result tVaccinePreventionList(HttpServletRequest request,PageDomain page,TVaccinePrevention entity) {
        try {
            IPage<TVaccinePrevention> iPage = tVaccinePreventionService.tVaccinePreventionList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tVaccinePreventionAdd")
    public Result tVaccinePreventionAdd(@RequestBody TVaccinePrevention entity) {
        try {
            tVaccinePreventionService.tVaccinePreventionAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tVaccinePreventionDel")
    public Result tVaccinePreventionDel(Long preventionId) {
        try {
            tVaccinePreventionService.tVaccinePreventionDel(preventionId);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tVaccinePreventionUpdate")
    public Result tVaccinePreventionUpdate(@RequestBody TVaccinePrevention entity) {
        try {
            tVaccinePreventionService.tVaccinePreventionUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tVaccinePreventionDetail")
    public Result tVaccinePreventionDetail(HttpServletRequest request,TVaccinePrevention entity) {
        try {
            TVaccinePrevention vo = tVaccinePreventionService.tVaccinePreventionDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
