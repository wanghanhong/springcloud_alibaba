package com.smart.card.manage.controller;

import com.smart.card.common.dict.entity.DictDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TCardUse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITCardUseService;
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
@RequestMapping("/api/card/v1/tcarduse")
public class TCardUseController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITCardUseService tCardUseService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tCardUseList")
    public Result tCardUseList(HttpServletRequest request,PageDomain page,TCardUse entity) {
        try {
            IPage iPage = tCardUseService.tCardUseList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tCardUseAdd")
    public Result tCardUseAdd(@RequestBody TCardUse entity) {
        try {
            tCardUseService.tCardUseAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tCardUseDel")
    public Result tCardUseDel(Long id) {
        try {
            tCardUseService.tCardUseDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tCardUseUpdate")
    public Result tCardUseUpdate(@RequestBody TCardUse entity) {
        try {
            tCardUseService.tCardUseUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tCardUseDetail")
    public Result tCardUseDetail(HttpServletRequest request,TCardUse entity) {
        try {
            TCardUse vo = tCardUseService.tCardUseDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "获取详单类型")
    @GetMapping("/tCardBills")
    public Result tCardBills(HttpServletRequest request) {
        try {
            List<DictDto> vo = tCardUseService.tCardBills();
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
    /**------基本方法结束-----------------------------------------*/


}
