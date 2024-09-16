package com.smart.card.manage.controller;

import com.smart.card.common.dict.entity.DictDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TCardHistory;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITCardHistoryService;
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
@RequestMapping("/api/card/v1/tcardhistory")
public class TCardHistoryController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITCardHistoryService tCardHistoryService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tCardHistoryList")
    public Result tCardHistoryList(HttpServletRequest request,PageDomain page,TCardHistory entity) {
        try {
            IPage iPage = tCardHistoryService.tCardHistoryList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tCardHistoryAdd")
    public Result tCardHistoryAdd(@RequestBody TCardHistory entity) {
        try {
            tCardHistoryService.tCardHistoryAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tCardHistoryDel")
    public Result tCardHistoryDel(Long id) {
        try {
            tCardHistoryService.tCardHistoryDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tCardHistoryUpdate")
    public Result tCardHistoryUpdate(@RequestBody TCardHistory entity) {
        try {
            tCardHistoryService.tCardHistoryUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tCardHistoryDetail")
    public Result tCardHistoryDetail(HttpServletRequest request,TCardHistory entity) {
        try {
            TCardHistory vo = tCardHistoryService.tCardHistoryDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "获取订单类型")
    @GetMapping("/tHisType")
    public Result tHisType(HttpServletRequest request) {
        try {
            List<DictDto> vo = tCardHistoryService.tHisType();
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "获取订单状态")
    @GetMapping("/tHisStatus")
    public Result tHisStatus(HttpServletRequest request) {
        try {
            List<DictDto> vo = tCardHistoryService.tHisStatus();
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "获取订单来源")
    @GetMapping("/tHisSource")
    public Result tHisSource(HttpServletRequest request) {
        try {
            List<DictDto> vo = tCardHistoryService.tHisSource();
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
