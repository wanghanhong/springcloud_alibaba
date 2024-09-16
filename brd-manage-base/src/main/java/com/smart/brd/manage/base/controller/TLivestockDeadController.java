package com.smart.brd.manage.base.controller;

import com.smart.brd.manage.base.entity.vo.DeadVo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITLivestockDeadService;
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
@Api(tags = "家畜死亡")
@RequestMapping("/api/v1/brd/tlivestockdead")
public class TLivestockDeadController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITLivestockDeadService tLivestockDeadService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tLivestockDeadList")
    @RequiresPermissions("deathmanagement:view")
    public Result tLivestockDeadList(HttpServletRequest request, PageDomain page,DeadVo entity) {
        try {
            IPage<DeadVo> iPage = tLivestockDeadService.tLivestockDeadList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tLivestockDeadAdd")
    public Result tLivestockDeadAdd(@RequestBody DeadVo entity) {
        try {
            tLivestockDeadService.tLivestockDeadAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tLivestockDeadDel")
    public Result tLivestockDeadDel(Long id) {
        try {
            tLivestockDeadService.tLivestockDeadDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tLivestockDeadUpdate")
    public Result tLivestockDeadUpdate(@RequestBody DeadVo entity) {
        try {
            tLivestockDeadService.tLivestockDeadUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tLivestockDeadDetail")
    public Result tLivestockDeadDetail(HttpServletRequest request,DeadVo entity) {
        try {
            DeadVo vo = tLivestockDeadService.tLivestockDeadDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
