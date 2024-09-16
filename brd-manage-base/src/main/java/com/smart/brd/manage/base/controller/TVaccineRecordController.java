package com.smart.brd.manage.base.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TVaccineRecord;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITVaccineRecordService;
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
@Api(tags = "疫苗防疫记录")
@RequestMapping("/api/v1/brd/vaccinerecord")
public class TVaccineRecordController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITVaccineRecordService vaccineRecordService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/vaccineRecordList")
    public Result vaccineRecordList(HttpServletRequest request, PageDomain page, TVaccineRecord entity) {
        try {
            IPage<TVaccineRecord> iPage = vaccineRecordService.vaccineRecordList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/vaccineRecordAdd")
    public Result vaccineRecordAdd(@RequestBody TVaccineRecord entity) {
        try {
            vaccineRecordService.vaccineRecordAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/vaccineRecordDel")
    public Result vaccineRecordDel(Long id) {
        try {
            vaccineRecordService.vaccineRecordDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/vaccineRecordUpdate")
    public Result vaccineRecordUpdate(@RequestBody TVaccineRecord entity) {
        try {
            vaccineRecordService.vaccineRecordUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/vaccineRecordDetail")
    public Result vaccineRecordDetail(HttpServletRequest request, TVaccineRecord entity) {
        try {
            TVaccineRecord vo = vaccineRecordService.vaccineRecordDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
