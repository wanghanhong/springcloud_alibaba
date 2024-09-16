package com.smart.device.install.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.device.common.install.entity.TBaseProduct;
import com.smart.device.install.service.ITBaseProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author f
 */
@Api(tags = "")
@RestController
@RequestMapping("/api/v2/install/base/product")
public class TBaseProductController {

    /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBaseProductService iTBaseProductService;

    @ApiOperation(value = "厂商查询接口")
    @ApiImplicitParam
    @GetMapping("/baseProductList")
    public Result baseProductList(PageDomain page, TBaseProduct entity) {
        try {
            IPage iPage = iTBaseProductService.baseProductList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_PRODUCT);
        }
    }

    @ApiOperation(value = "厂商添加接口")
    @ApiImplicitParam
    @PostMapping("/baseProductAdd")
    public Result baseProductAdd(@RequestBody TBaseProduct entity) {
        try {
            iTBaseProductService.baseProductAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_PRODUCT);
        }
    }

    @ApiOperation(value = "厂商删除接口")
    @ApiImplicitParam
    @GetMapping("/baseProductDel")
    public Result baseProductDel(Long id) {
        try {
            iTBaseProductService.baseProductDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_PRODUCT);
        }
    }

    @ApiOperation(value = "修改厂商接口")
    @ApiImplicitParam
    @PostMapping("/baseProductUpdate")
    public Result baseProductUpdate(@RequestBody TBaseProduct entity) {
        try {
            iTBaseProductService.baseProductUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_PRODUCT);
        }
    }

    @ApiOperation(value = "根据ID查询厂商详情")
    @ApiImplicitParam
    @GetMapping("/selectbaseProductDetail")
    public Result selectbaseProductByID(Long id) {
        try {
            TBaseProduct vo = iTBaseProductService.selectBaseProductByID(id);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_PRODUCT);
        }
    }
    /**------基本方法结束-----------------------------------------*/



}
