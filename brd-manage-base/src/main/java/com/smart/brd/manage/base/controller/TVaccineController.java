package com.smart.brd.manage.base.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TVaccine;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITVaccineService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author
 */
@RestController
@Api(tags = "疫苗")
@RequestMapping("/api/v1/brd/tvaccine")
public class TVaccineController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITVaccineService tVaccineService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tVaccineList")
    @RequiresPermissions("vaccinePig:view")
    public Result tVaccineList(HttpServletRequest request,PageDomain page,TVaccine entity) {
        try {
            IPage<TVaccine> iPage = tVaccineService.tVaccineList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tVaccineAdd")
    public Result tVaccineAdd(@RequestBody TVaccine entity) {
        try {
            tVaccineService.tVaccineAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tVaccineDel")
    public Result tVaccineDel(Long vaccineId) {
        try {
            tVaccineService.tVaccineDel(vaccineId);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tVaccineUpdate")
    public Result tVaccineUpdate(@RequestBody TVaccine entity) {
        try {
            tVaccineService.tVaccineUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tVaccineDetail")
    public Result tVaccineDetail(HttpServletRequest request,TVaccine entity) {
        try {
            TVaccine vo = tVaccineService.tVaccineDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/

    @ApiOperation(value = "查询接口-不分页")
    @GetMapping("/queryVaccineList")
    public Result queryVaccineList(HttpServletRequest request,TVaccine entity) {
        try {
            List<TVaccine> tVaccineList=tVaccineService.queryVaccineList(request,entity);
            return Result.SUCCESS(tVaccineList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
}
