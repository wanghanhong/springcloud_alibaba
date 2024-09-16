package com.smart.card.manage.controller;

import com.smart.card.manage.entity.vo.TCardVo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TCardProductData;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITCardProductDataService;
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
@RequestMapping("/api/card/v1/tcardproductdata")
public class TCardProductDataController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITCardProductDataService tCardProductDataService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tCardProductDataList")
    public Result tCardProductDataList(HttpServletRequest request, PageDomain page,TCardVo entity) {
        try {
            IPage iPage = tCardProductDataService.tCardProductDataList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tCardProductDataAdd")
    public Result tCardProductDataAdd(@RequestBody TCardProductData entity) {
        try {
            tCardProductDataService.tCardProductDataAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tCardProductDataDel")
    public Result tCardProductDataDel(Long id) {
        try {
            tCardProductDataService.tCardProductDataDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tCardProductDataUpdate")
    public Result tCardProductDataUpdate(@RequestBody TCardProductData entity) {
        try {
            tCardProductDataService.tCardProductDataUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tCardProductDataDetail")
    public Result tCardProductDataDetail(HttpServletRequest request,TCardProductData entity) {
        try {
            TCardProductData vo = tCardProductDataService.tCardProductDataDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
