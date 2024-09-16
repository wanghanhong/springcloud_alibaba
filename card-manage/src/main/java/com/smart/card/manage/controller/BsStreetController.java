package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.BsStreet;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.IBsStreetService;
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
@RequestMapping("/api/card/v1/bsstreet")
public class BsStreetController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private IBsStreetService bsStreetService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/bsStreetList")
    public Result bsStreetList(HttpServletRequest request,PageDomain page,BsStreet entity) {
        try {
            IPage iPage = bsStreetService.bsStreetList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/bsStreetAdd")
    public Result bsStreetAdd(@RequestBody BsStreet entity) {
        try {
            bsStreetService.bsStreetAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/bsStreetDel")
    public Result bsStreetDel(Long id) {
        try {
            bsStreetService.bsStreetDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/bsStreetUpdate")
    public Result bsStreetUpdate(@RequestBody BsStreet entity) {
        try {
            bsStreetService.bsStreetUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/bsStreetDetail")
    public Result bsStreetDetail(HttpServletRequest request,BsStreet entity) {
        try {
            BsStreet vo = bsStreetService.bsStreetDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
