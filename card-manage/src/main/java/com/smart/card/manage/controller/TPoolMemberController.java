package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TPoolMember;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITPoolMemberService;
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
@RequestMapping("/api/card/v1/tpoolmember")
public class TPoolMemberController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITPoolMemberService tPoolMemberService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tPoolMemberList")
    public Result tPoolMemberList(HttpServletRequest request,PageDomain page,TPoolMember entity) {
        try {
            IPage iPage = tPoolMemberService.tPoolMemberList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tPoolMemberAdd")
    public Result tPoolMemberAdd(@RequestBody TPoolMember entity) {
        try {
            tPoolMemberService.tPoolMemberAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tPoolMemberDel")
    public Result tPoolMemberDel(Long id) {
        try {
            tPoolMemberService.tPoolMemberDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tPoolMemberUpdate")
    public Result tPoolMemberUpdate(@RequestBody TPoolMember entity) {
        try {
            tPoolMemberService.tPoolMemberUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tPoolMemberDetail")
    public Result tPoolMemberDetail(HttpServletRequest request,TPoolMember entity) {
        try {
            TPoolMember vo = tPoolMemberService.tPoolMemberDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
