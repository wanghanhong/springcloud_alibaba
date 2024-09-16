package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TBaseDict;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITBaseDictService;
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
@RequestMapping("/api/card/v1/tbasedict")
public class TBaseDictController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBaseDictService tBaseDictService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tBaseDictList")
    public Result tBaseDictList(HttpServletRequest request,PageDomain page,TBaseDict entity) {
        try {
            IPage iPage = tBaseDictService.tBaseDictList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tBaseDictAdd")
    public Result tBaseDictAdd(@RequestBody TBaseDict entity) {
        try {
            tBaseDictService.tBaseDictAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tBaseDictDel")
    public Result tBaseDictDel(Long id) {
        try {
            tBaseDictService.tBaseDictDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tBaseDictUpdate")
    public Result tBaseDictUpdate(@RequestBody TBaseDict entity) {
        try {
            tBaseDictService.tBaseDictUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tBaseDictDetail")
    public Result tBaseDictDetail(HttpServletRequest request,TBaseDict entity) {
        try {
            TBaseDict vo = tBaseDictService.tBaseDictDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
