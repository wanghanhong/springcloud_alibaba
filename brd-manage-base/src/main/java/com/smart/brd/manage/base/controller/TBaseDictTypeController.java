package com.smart.brd.manage.base.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TBaseDictType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITBaseDictTypeService;
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
@Api(tags = "字典类型")
@RequestMapping("/api/v1/brd/tbasedicttype")
public class TBaseDictTypeController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBaseDictTypeService tBaseDictTypeService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tBaseDictTypeList")
    @RequiresPermissions("DictionaryManagement:view")
    public Result tBaseDictTypeList(HttpServletRequest request,PageDomain page,TBaseDictType entity) {
        try {
            IPage<TBaseDictType> iPage = tBaseDictTypeService.tBaseDictTypeList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tBaseDictTypeAdd")
    public Result tBaseDictTypeAdd(@RequestBody TBaseDictType entity) {
        try {
            tBaseDictTypeService.tBaseDictTypeAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tBaseDictTypeDel")
    public Result tBaseDictTypeDel(Long id) {
        try {
            tBaseDictTypeService.tBaseDictTypeDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tBaseDictTypeUpdate")
    public Result tBaseDictTypeUpdate(@RequestBody TBaseDictType entity) {
        try {
            tBaseDictTypeService.tBaseDictTypeUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tBaseDictTypeDetail")
    public Result tBaseDictTypeDetail(HttpServletRequest request,TBaseDictType entity) {
        try {
            TBaseDictType vo = tBaseDictTypeService.tBaseDictTypeDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/



}
