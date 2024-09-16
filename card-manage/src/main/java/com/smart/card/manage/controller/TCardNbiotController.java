package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TCardNbiot;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITCardNbiotService;
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
@RequestMapping("/api/card/v1/tcardnbiot")
public class TCardNbiotController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITCardNbiotService tCardNbiotService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tCardNbiotList")
    public Result tCardNbiotList(HttpServletRequest request,PageDomain page,TCardNbiot entity) {
        try {
            IPage iPage = tCardNbiotService.tCardNbiotList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tCardNbiotAdd")
    public Result tCardNbiotAdd(@RequestBody TCardNbiot entity) {
        try {
            tCardNbiotService.tCardNbiotAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tCardNbiotDel")
    public Result tCardNbiotDel(Long id) {
        try {
            tCardNbiotService.tCardNbiotDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tCardNbiotUpdate")
    public Result tCardNbiotUpdate(@RequestBody TCardNbiot entity) {
        try {
            tCardNbiotService.tCardNbiotUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tCardNbiotDetail")
    public Result tCardNbiotDetail(HttpServletRequest request,TCardNbiot entity) {
        try {
            TCardNbiot vo = tCardNbiotService.tCardNbiotDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
