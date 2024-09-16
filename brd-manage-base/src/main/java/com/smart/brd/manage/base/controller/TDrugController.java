package com.smart.brd.manage.base.controller;

import com.smart.brd.manage.base.common.utils.ExcelUtils;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TDrug;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITDrugService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 
 */
@RestController
@Api(tags = "兽药")
@RequestMapping("/api/v1/brd/tdrug")
public class TDrugController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITDrugService tDrugService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tDrugList")
    @RequiresPermissions("drugPig:view")
    public Result tDrugList(HttpServletRequest request,PageDomain page,TDrug entity) {
        try {
            IPage<TDrug> iPage = tDrugService.tDrugList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tDrugAdd")
    public Result tDrugAdd(@RequestBody TDrug entity) {
        try {
            tDrugService.tDrugAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tDrugDel")
    public Result tDrugDel(Long drugId) {
        try {
            tDrugService.tDrugDel(drugId);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tDrugUpdate")
    public Result tDrugUpdate(@RequestBody TDrug entity) {
        try {
            tDrugService.tDrugUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tDrugDetail")
    public Result tDrugDetail(HttpServletRequest request,TDrug entity) {
        try {
            TDrug vo = tDrugService.tDrugDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/

    @ApiOperation(value = "查询接口-不分页")
    @GetMapping("/queryTDrugList")
    public Result queryTDrugList(HttpServletRequest request,TDrug entity) {
        try {
            List<TDrug> tTDrugList=tDrugService.queryTDrugList(request,entity);
            return Result.SUCCESS(tTDrugList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
//    @ApiOperation(value = "导出接口")
//    @PostMapping("/exportTDrug")
//    public Result exportTDrug(HttpServletResponse response) {
//        try {
//            List<TDrug> list = tDrugService.list();
//            ExcelUtils.exportExcel(list, null, "test", TDrug.class, "test.xls", response);
//            return null;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.ERROR(ResultCode.FAIL);
//        }
//    }
//    @ApiOperation(value = "导入接口")
//    @PostMapping("/importTDrug")
//    public Result importTDrug(HttpServletRequest request, MultipartFile path) {
//        try {
//            tDrugService.importTDrug(path);
//            return Result.SUCCESS();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.ERROR(ResultCode.FAIL);
//        }
//    }

}
