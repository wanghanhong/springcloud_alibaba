package com.smart.brd.manage.base.controller;

import com.smart.brd.manage.base.entity.TBaseDict;
import com.smart.brd.manage.base.entity.vo.BrdFieldVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TBrdField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITBrdFieldService;
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
@Api(tags = "养殖场")
@RequestMapping("/api/v1/brd/tbrdfield")
public class TBrdFieldController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBrdFieldService tBrdFieldService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tBrdFieldList")
    public Result tBrdFieldList(HttpServletRequest request,PageDomain page,TBrdField entity) {
        try {
            IPage<BrdFieldVo> iPage = tBrdFieldService.tBrdFieldList(request,page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tBrdFieldAdd")
    public Result tBrdFieldAdd(@RequestBody BrdFieldVo entity) {
        try {
            return tBrdFieldService.tBrdFieldAdd(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tBrdFieldDel")
    public Result tBrdFieldDel(Long fieldId) {
        try {
            tBrdFieldService.tBrdFieldDel(fieldId);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tBrdFieldUpdate")
    public Result tBrdFieldUpdate(@RequestBody BrdFieldVo entity) {
        try {
            return tBrdFieldService.tBrdFieldUpdate(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tBrdFieldDetail")
    public Result tBrdFieldDetail(HttpServletRequest request,@RequestParam Long fieldId) {
        try {
            BrdFieldVo vo = tBrdFieldService.tBrdFieldDetail(fieldId);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/

    @ApiOperation(value = "获取类别")
    @GetMapping("/tDictList")
    public Result tDictList(HttpServletRequest request) {
        try {
            List<TBaseDict> vo = tBrdFieldService.getType();
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
}
