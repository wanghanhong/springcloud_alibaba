package com.smart.brd.manage.base.controller;

import com.smart.brd.manage.base.common.utils.ExcelUtils;
import com.smart.brd.manage.base.entity.TBaseDict;
import com.smart.brd.manage.base.entity.vo.DiseaseVo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TDisease;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITDiseaseService;
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
@Api(tags = "畜病")
@RequestMapping("/api/v1/brd/tdisease")
public class TDiseaseController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITDiseaseService tDiseaseService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tDiseaseList")
    @RequiresPermissions("diseasePig:view")
    public Result tDiseaseList(HttpServletRequest request,PageDomain page,TDisease entity) {
        try {
            IPage<TDisease> iPage = tDiseaseService.tDiseaseList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tDiseaseAdd")
    public Result tDiseaseAdd(@RequestBody DiseaseVo entity) {
        try {
            tDiseaseService.tDiseaseAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tDiseaseDel")
    public Result tDiseaseDel(Long diseaseId) {
        try {
            tDiseaseService.tDiseaseDel(diseaseId);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tDiseaseUpdate")
    public Result tDiseaseUpdate(@RequestBody DiseaseVo entity) {
        try {
            tDiseaseService.tDiseaseUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tDiseaseDetail")
    public Result tDiseaseDetail(HttpServletRequest request,TDisease entity) {
        try {
            DiseaseVo vo = tDiseaseService.tDiseaseDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/

    @ApiOperation(value = "获取类别")
    @GetMapping("/tDictList")
    public Result tDictList(HttpServletRequest request) {
        try {
            List<TBaseDict> vo = tDiseaseService.getType();
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询所有疾病-不分页")
    @GetMapping("/queryDiseaseList")
    public Result queryDiseaseList(HttpServletRequest request,TDisease entity) {
        try {
            List<TDisease> list = tDiseaseService.queryDiseaseList(request,entity);
            return Result.SUCCESS(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
    @ApiOperation(value = "根据疾病查询症状")
    @GetMapping("/getDisease")
    public Result getDisease(HttpServletRequest request,TDisease entity) {
        try {
            TDisease list = tDiseaseService.getDisease(request,entity);
            return Result.SUCCESS(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
//    @ApiOperation(value = "导入接口")
//    @PostMapping("/exportTDisease")
//    public Result exportTDisease(HttpServletRequest request, MultipartFile path) {
//        try {
//            tDiseaseService.exportTDisease(path);
//            return Result.SUCCESS();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.ERROR(ResultCode.FAIL);
//        }
//    }
//    @ApiOperation(value = "导出接口")
//    @PostMapping("/importTDisease")
//    public Result importTDisease(HttpServletResponse response) {
//        try {
//            List<TDisease> list = tDiseaseService.list();
//            ExcelUtils.exportExcel(list, null, "test", TDisease.class, "test.xls", response);
//            return null;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.ERROR(ResultCode.FAIL);
//        }
//    }
}
