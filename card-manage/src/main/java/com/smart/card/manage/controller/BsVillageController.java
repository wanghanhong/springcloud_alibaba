package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.BsVillage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.IBsVillageService;
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
@RequestMapping("/api/card/v1/bsvillage")
public class BsVillageController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private IBsVillageService bsVillageService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/bsVillageList")
    public Result bsVillageList(HttpServletRequest request,PageDomain page,BsVillage entity) {
        try {
            IPage iPage = bsVillageService.bsVillageList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/bsVillageAdd")
    public Result bsVillageAdd(@RequestBody BsVillage entity) {
        try {
            bsVillageService.bsVillageAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/bsVillageDel")
    public Result bsVillageDel(Long id) {
        try {
            bsVillageService.bsVillageDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/bsVillageUpdate")
    public Result bsVillageUpdate(@RequestBody BsVillage entity) {
        try {
            bsVillageService.bsVillageUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/bsVillageDetail")
    public Result bsVillageDetail(HttpServletRequest request,BsVillage entity) {
        try {
            BsVillage vo = bsVillageService.bsVillageDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
