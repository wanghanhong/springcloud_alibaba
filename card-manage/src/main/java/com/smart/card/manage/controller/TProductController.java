package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TProduct;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITProductService;
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
@RequestMapping("/api/card/v1/tproduct")
public class TProductController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITProductService tProductService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tProductList")
    public Result tProductList(HttpServletRequest request,PageDomain page,TProduct entity) {
        try {
            IPage iPage = tProductService.tProductList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tProductAdd")
    public Result tProductAdd(@RequestBody TProduct entity) {
        try {
            tProductService.tProductAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tProductDel")
    public Result tProductDel(Long id) {
        try {
            tProductService.tProductDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tProductUpdate")
    public Result tProductUpdate(@RequestBody TProduct entity) {
        try {
            tProductService.tProductUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tProductDetail")
    public Result tProductDetail(HttpServletRequest request,TProduct entity) {
        try {
            TProduct vo = tProductService.tProductDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
