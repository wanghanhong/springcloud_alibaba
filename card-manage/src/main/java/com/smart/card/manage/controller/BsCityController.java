package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.BsCity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.IBsCityService;
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
@RequestMapping("/api/card/v1/bscity")
public class BsCityController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private IBsCityService bsCityService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/bsCityList")
    public Result bsCityList(HttpServletRequest request,PageDomain page,BsCity entity) {
        try {
            IPage iPage = bsCityService.bsCityList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/bsCityAdd")
    public Result bsCityAdd(@RequestBody BsCity entity) {
        try {
            bsCityService.bsCityAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/bsCityDel")
    public Result bsCityDel(Long id) {
        try {
            bsCityService.bsCityDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/bsCityUpdate")
    public Result bsCityUpdate(@RequestBody BsCity entity) {
        try {
            bsCityService.bsCityUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/bsCityDetail")
    public Result bsCityDetail(HttpServletRequest request,BsCity entity) {
        try {
            BsCity vo = bsCityService.bsCityDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
