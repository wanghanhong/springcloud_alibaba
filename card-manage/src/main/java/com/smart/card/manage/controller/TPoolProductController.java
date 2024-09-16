package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TPoolProduct;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITPoolProductService;
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
@RequestMapping("/api/card/v1/tpoolproduct")
public class TPoolProductController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITPoolProductService tPoolProductService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tPoolProductList")
    public Result tPoolProductList(HttpServletRequest request,PageDomain page,TPoolProduct entity) {
        try {
            IPage iPage = tPoolProductService.tPoolProductList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tPoolProductAdd")
    public Result tPoolProductAdd(@RequestBody TPoolProduct entity) {
        try {
            tPoolProductService.tPoolProductAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tPoolProductDel")
    public Result tPoolProductDel(Long id) {
        try {
            tPoolProductService.tPoolProductDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tPoolProductUpdate")
    public Result tPoolProductUpdate(@RequestBody TPoolProduct entity) {
        try {
            tPoolProductService.tPoolProductUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tPoolProductDetail")
    public Result tPoolProductDetail(HttpServletRequest request,TPoolProduct entity) {
        try {
            TPoolProduct vo = tPoolProductService.tPoolProductDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
