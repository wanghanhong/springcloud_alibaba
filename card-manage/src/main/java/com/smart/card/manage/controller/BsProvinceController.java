package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.BsProvince;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.IBsProvinceService;
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
@RequestMapping("/api/card/v1/bsprovince")
public class BsProvinceController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private IBsProvinceService bsProvinceService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/bsProvinceList")
    public Result bsProvinceList(HttpServletRequest request,PageDomain page,BsProvince entity) {
        try {
            IPage iPage = bsProvinceService.bsProvinceList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/bsProvinceAdd")
    public Result bsProvinceAdd(@RequestBody BsProvince entity) {
        try {
            bsProvinceService.bsProvinceAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/bsProvinceDel")
    public Result bsProvinceDel(Long id) {
        try {
            bsProvinceService.bsProvinceDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/bsProvinceUpdate")
    public Result bsProvinceUpdate(@RequestBody BsProvince entity) {
        try {
            bsProvinceService.bsProvinceUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/bsProvinceDetail")
    public Result bsProvinceDetail(HttpServletRequest request,BsProvince entity) {
        try {
            BsProvince vo = bsProvinceService.bsProvinceDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
