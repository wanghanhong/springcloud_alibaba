package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TPoolPackageUse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITPoolPackageUseService;
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
@RequestMapping("/api/card/v1/tpoolpackageuse")
public class TPoolPackageUseController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITPoolPackageUseService tPoolPackageUseService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tPoolPackageUseList")
    public Result tPoolPackageUseList(HttpServletRequest request,PageDomain page,TPoolPackageUse entity) {
        try {
            IPage iPage = tPoolPackageUseService.tPoolPackageUseList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tPoolPackageUseAdd")
    public Result tPoolPackageUseAdd(@RequestBody TPoolPackageUse entity) {
        try {
            tPoolPackageUseService.tPoolPackageUseAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tPoolPackageUseDel")
    public Result tPoolPackageUseDel(Long id) {
        try {
            tPoolPackageUseService.tPoolPackageUseDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tPoolPackageUseUpdate")
    public Result tPoolPackageUseUpdate(@RequestBody TPoolPackageUse entity) {
        try {
            tPoolPackageUseService.tPoolPackageUseUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tPoolPackageUseDetail")
    public Result tPoolPackageUseDetail(HttpServletRequest request,TPoolPackageUse entity) {
        try {
            TPoolPackageUse vo = tPoolPackageUseService.tPoolPackageUseDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
