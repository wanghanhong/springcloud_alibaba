package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TBaseGroup;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITBaseGroupService;
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
@RequestMapping("/api/card/v1/tbasegroup")
public class TBaseGroupController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBaseGroupService tBaseGroupService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tBaseGroupList")
    public Result tBaseGroupList(HttpServletRequest request,PageDomain page,TBaseGroup entity) {
        try {
            IPage iPage = tBaseGroupService.tBaseGroupList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tBaseGroupAdd")
    public Result tBaseGroupAdd(@RequestBody TBaseGroup entity) {
        try {
            tBaseGroupService.tBaseGroupAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tBaseGroupDel")
    public Result tBaseGroupDel(Long id) {
        try {
            tBaseGroupService.tBaseGroupDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tBaseGroupUpdate")
    public Result tBaseGroupUpdate(@RequestBody TBaseGroup entity) {
        try {
            tBaseGroupService.tBaseGroupUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tBaseGroupDetail")
    public Result tBaseGroupDetail(HttpServletRequest request,TBaseGroup entity) {
        try {
            TBaseGroup vo = tBaseGroupService.tBaseGroupDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
