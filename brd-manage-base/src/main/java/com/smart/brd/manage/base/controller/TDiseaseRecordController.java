package com.smart.brd.manage.base.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TDiseaseRecord;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITDiseaseRecordService;
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
@Api(tags = "畜病记录")
@RequestMapping("/api/v1/brd/tdiseaserecord")
public class TDiseaseRecordController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITDiseaseRecordService tDiseaseRecordService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tDiseaseRecordList")
    public Result tDiseaseRecordList(HttpServletRequest request,PageDomain page,TDiseaseRecord entity) {
        try {
            IPage<TDiseaseRecord> iPage = tDiseaseRecordService.tDiseaseRecordList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tDiseaseRecordAdd")
    public Result tDiseaseRecordAdd(@RequestBody TDiseaseRecord entity) {
        try {
            tDiseaseRecordService.tDiseaseRecordAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tDiseaseRecordDel")
    public Result tDiseaseRecordDel(Long id) {
        try {
            tDiseaseRecordService.tDiseaseRecordDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tDiseaseRecordUpdate")
    public Result tDiseaseRecordUpdate(@RequestBody TDiseaseRecord entity) {
        try {
            tDiseaseRecordService.tDiseaseRecordUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tDiseaseRecordDetail")
    public Result tDiseaseRecordDetail(HttpServletRequest request,TDiseaseRecord entity) {
        try {
            TDiseaseRecord vo = tDiseaseRecordService.tDiseaseRecordDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
