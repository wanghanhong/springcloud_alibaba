package com.smart.brd.manage.base.controller;

import com.smart.brd.manage.base.entity.dto.DictDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TBaseDict;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITBaseDictService;
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
@Api(tags = "字典")
@RequestMapping("/api/v1/brd/tbasedict")
public class TBaseDictController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBaseDictService tBaseDictService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tBaseDictList")
    public Result tBaseDictList(HttpServletRequest request,PageDomain page,TBaseDict entity) {
        try {
            IPage<TBaseDict> iPage = tBaseDictService.tBaseDictList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tBaseDictAdd")
    public Result tBaseDictAdd(@RequestBody TBaseDict entity) {
        try {
            tBaseDictService.tBaseDictAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tBaseDictDel")
    public Result tBaseDictDel(Long id) {
        try {
            tBaseDictService.tBaseDictDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tBaseDictUpdate")
    public Result tBaseDictUpdate(@RequestBody TBaseDict entity) {
        try {
            tBaseDictService.tBaseDictUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tBaseDictDetail")
    public Result tBaseDictDetail(HttpServletRequest request,TBaseDict entity) {
        try {
            TBaseDict vo = tBaseDictService.tBaseDictDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/

    @ApiOperation(value = "字典类别查询")
    @GetMapping("/tByDict")
    public Result tByDict(String s) {
        try {
            List<DictDto> tBaseDicts=tBaseDictService.tByDict(s);
            return Result.SUCCESS(tBaseDicts);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }


}
