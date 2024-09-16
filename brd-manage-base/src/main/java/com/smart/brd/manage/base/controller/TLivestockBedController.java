package com.smart.brd.manage.base.controller;

import com.smart.brd.manage.base.entity.*;
import com.smart.brd.manage.base.entity.vo.TLivestockBedVo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITLivestockBedService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author
 */
@RestController
@Api(tags = "栏")
@RequestMapping("/api/v1/brd/tlivestockbed")
public class TLivestockBedController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITLivestockBedService tLivestockBedService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tLivestockBedList")
    @RequiresPermissions("tlivestockbed:view")
    public Result tLivestockBedList(HttpServletRequest request,PageDomain page,BarnsVo entity) {
        try {
            IPage<BarnsVo> iPage = tLivestockBedService.tLivestockBedList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tLivestockBedAdd")
    public Result tLivestockBedAdd(@RequestBody BarnsVo entity) {
        try {
            tLivestockBedService.barnsAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tLivestockBedDel")
    public Result tLivestockBedDel(Long bedId) {
        try {
            tLivestockBedService.tLivestockBedDel(bedId);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tLivestockBedUpdate")
    public Result tLivestockBedUpdate(@RequestBody BarnsVo entity) {
        try {
            tLivestockBedService.barnsUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tLivestockBedDetail")
    public Result tLivestockBedDetail(HttpServletRequest request,Long bedId) {
        try {
            BarnsVo barnsVo = tLivestockBedService.barnsInfo(bedId);
            return Result.SUCCESS(barnsVo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/

    @ApiOperation(value = "查询公司下：所有栏接口-不分页")
    @GetMapping("/queryBedList")
    public Result queryBedList(HttpServletRequest request,TLivestockBed entity) {
        try {
            List<TLivestockBed> list = tLivestockBedService.queryBedList(request,entity);
            return Result.SUCCESS(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
    @ApiOperation(value = "舍栏树结构")
    @GetMapping(value = "/selectsuperid")
    public Result selectSmallId (TLivestockAnalysis tla){
        List<Tree> lists= tLivestockBedService.selectTree(tla);
        return Result.SUCCESS(lists);
    }
    @ApiOperation(value = "查询公司下：所有养殖员")
    @GetMapping("/queryBreederList")
    public Result queryBreederList(HttpServletRequest request, TLivestockBedVo entity) {
        try {
            List<TLivestockBedVo> list = tLivestockBedService.queryBreederList(request,entity);
            return Result.SUCCESS(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

}
