package com.smart.brd.manage.base.controller;

import com.smart.brd.manage.base.entity.dto.EscapeDto;
import com.smart.brd.manage.base.entity.vo.EscapeVo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITLivestockEscapeService;
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
@Api(tags = "家畜淘汰")
@RequestMapping("/api/v1/brd/tlivestockescape")
public class TLivestockEscapeController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITLivestockEscapeService tLivestockEscapeService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tLivestockEscapeList")
    @RequiresPermissions("escapePig:view")
    public Result tLivestockEscapeList(HttpServletRequest request, PageDomain page, EscapeVo entity) {
        try {
            IPage<EscapeDto> iPage = tLivestockEscapeService.tLivestockEscapeList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tLivestockEscapeAdd")
    public Result tLivestockEscapeAdd(@RequestBody EscapeVo entity) {
        try {
            tLivestockEscapeService.tLivestockEscapeAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tLivestockEscapeDel")
    public Result tLivestockEscapeDel(Long id) {
        try {
            tLivestockEscapeService.tLivestockEscapeDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tLivestockEscapeUpdate")
    public Result tLivestockEscapeUpdate(@RequestBody EscapeVo entity) {
        try {
            tLivestockEscapeService.tLivestockEscapeUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tLivestockEscapeDetail")
    public Result tLivestockEscapeDetail(HttpServletRequest request,EscapeVo entity) {
        try {
            EscapeDto vo = tLivestockEscapeService.tLivestockEscapeDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/

    @ApiOperation(value = "查询详情")
    @GetMapping("/queryEscapeAge")
    public Result queryEscapeAge(HttpServletRequest request,EscapeVo entity) {
        try {
            EscapeVo vo = tLivestockEscapeService.queryEscapeAge(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
}
