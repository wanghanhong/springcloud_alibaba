package com.smart.card.common.dict.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.common.dict.entity.TBaseDictType;
import com.smart.card.common.dict.service.ITBaseDictTypeService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 
 */
@RestController
@Api(tags = "字典类型")
@RequestMapping("/api/v1/tbasedicttype")
public class TBaseDictTypeController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBaseDictTypeService tBaseDictTypeService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tBaseDictTypeList")
    public Result tBaseDictTypeList(HttpServletRequest request,PageDomain page,TBaseDictType entity) {
        try {
            IPage iPage = tBaseDictTypeService.tBaseDictTypeList(page,entity);
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
            Result res = tBaseDictTypeService.tBaseDictTypeAdd(entity);
            return res;
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
