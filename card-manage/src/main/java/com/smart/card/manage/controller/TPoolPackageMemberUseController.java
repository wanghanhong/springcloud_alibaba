package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TPoolPackageMemberUse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITPoolPackageMemberUseService;
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
@RequestMapping("/api/card/v1/tpoolpackagememberuse")
public class TPoolPackageMemberUseController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITPoolPackageMemberUseService tPoolPackageMemberUseService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tPoolPackageMemberUseList")
    public Result tPoolPackageMemberUseList(HttpServletRequest request,PageDomain page,TPoolPackageMemberUse entity) {
        try {
            IPage iPage = tPoolPackageMemberUseService.tPoolPackageMemberUseList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tPoolPackageMemberUseAdd")
    public Result tPoolPackageMemberUseAdd(@RequestBody TPoolPackageMemberUse entity) {
        try {
            tPoolPackageMemberUseService.tPoolPackageMemberUseAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tPoolPackageMemberUseDel")
    public Result tPoolPackageMemberUseDel(Long id) {
        try {
            tPoolPackageMemberUseService.tPoolPackageMemberUseDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tPoolPackageMemberUseUpdate")
    public Result tPoolPackageMemberUseUpdate(@RequestBody TPoolPackageMemberUse entity) {
        try {
            tPoolPackageMemberUseService.tPoolPackageMemberUseUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tPoolPackageMemberUseDetail")
    public Result tPoolPackageMemberUseDetail(HttpServletRequest request,TPoolPackageMemberUse entity) {
        try {
            TPoolPackageMemberUse vo = tPoolPackageMemberUseService.tPoolPackageMemberUseDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
