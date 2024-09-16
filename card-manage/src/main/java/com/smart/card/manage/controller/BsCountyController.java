package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.BsCounty;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.IBsCountyService;
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
@RequestMapping("/api/card/v1/bscounty")
public class BsCountyController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private IBsCountyService bsCountyService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/bsCountyList")
    public Result bsCountyList(HttpServletRequest request,PageDomain page,BsCounty entity) {
        try {
            IPage iPage = bsCountyService.bsCountyList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/bsCountyAdd")
    public Result bsCountyAdd(@RequestBody BsCounty entity) {
        try {
            bsCountyService.bsCountyAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/bsCountyDel")
    public Result bsCountyDel(Long id) {
        try {
            bsCountyService.bsCountyDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/bsCountyUpdate")
    public Result bsCountyUpdate(@RequestBody BsCounty entity) {
        try {
            bsCountyService.bsCountyUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/bsCountyDetail")
    public Result bsCountyDetail(HttpServletRequest request,BsCounty entity) {
        try {
            BsCounty vo = bsCountyService.bsCountyDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
