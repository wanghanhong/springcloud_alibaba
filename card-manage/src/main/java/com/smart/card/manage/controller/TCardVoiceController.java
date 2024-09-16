package com.smart.card.manage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TCardVoice;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITCardVoiceService;
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
@RequestMapping("/api/card/v1/tcardvoice")
public class TCardVoiceController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITCardVoiceService tCardVoiceService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tCardVoiceList")
    public Result tCardVoiceList(HttpServletRequest request,PageDomain page,TCardVoice entity) {
        try {
            IPage iPage = tCardVoiceService.tCardVoiceList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tCardVoiceAdd")
    public Result tCardVoiceAdd(@RequestBody TCardVoice entity) {
        try {
            tCardVoiceService.tCardVoiceAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tCardVoiceDel")
    public Result tCardVoiceDel(Long id) {
        try {
            tCardVoiceService.tCardVoiceDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tCardVoiceUpdate")
    public Result tCardVoiceUpdate(@RequestBody TCardVoice entity) {
        try {
            tCardVoiceService.tCardVoiceUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tCardVoiceDetail")
    public Result tCardVoiceDetail(HttpServletRequest request,TCardVoice entity) {
        try {
            TCardVoice vo = tCardVoiceService.tCardVoiceDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/


}
