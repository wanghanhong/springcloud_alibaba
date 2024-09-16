package com.smart.publicize.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.common.validator.Validator;
import com.smart.common.validator.ValidatorUtils;
import com.smart.publicize.manage.entity.NousEntity;
import com.smart.publicize.manage.service.NousService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * USER: gll
 * DATE: 2020/5/22
 * TIME: 16:52
 * Describe:
 */
@RestController
@Api(value = "消防常识controller", tags = "消防常识接口")
@RequestMapping("/api/v2/nous")
public class NousController extends BaseController {
    @Resource
    NousService nousService;

    @ApiOperation(value = "消防常识添加接口", notes = "消防常识添加接口")
    @ApiImplicitParam(name = "NousEntity", value = "需要传递的参数nousName，examineStatus,nousContent,createId,createName，文件 filessss", dataType = "NousEntity")
    @PostMapping(value = "/add")
    public Result add(@RequestBody NousEntity nousEntity) throws IOException {
        //参数校验
        String msg = new Validator<NousEntity>().validate(nousEntity, new String[]{"nousName"});
        if (msg != null) {
            return Result.FAIL(ResultCode.PUBLICIZE_PARAM_FAIL.getCode(), msg);
        }
        return nousService.add(nousEntity);
    }


    @ApiOperation(value = "消防常识删除接口", notes = "消防常识删除接口")
    @ApiImplicitParam(name = "接口参数", value = "id，updateId，updateName", dataType = "NousEntity")

    @DeleteMapping("/del")
    public Result del(@RequestBody NousEntity nousEntity) {
        //参数校验
        String msg = new Validator<NousEntity>().validate(nousEntity, new String[]{"id"});
        if (msg != null) {
            return Result.FAIL(ResultCode.PUBLICIZE_PARAM_FAIL.getCode(), msg);
        }
        return nousService.del(nousEntity);
    }

    @ApiOperation(value = "消防常识修改接口", notes = "消防常识修改接口")
    @ApiImplicitParam(name = "NousEntity", value = "需要传递的参数需要传递的参数nousName，examineStatus,nousContent，文件 filessss ,id，updateId，updateName", dataType = "NousEntity")
    @PostMapping("/update")
    public Result update(@RequestBody NousEntity nousEntity, @RequestParam(value = "filessss", required = false) MultipartFile[] file) {
        //参数校验
        String msg = new Validator<NousEntity>().validate(nousEntity, new String[]{"id"});
        if (msg != null) {
            return Result.FAIL(ResultCode.PUBLICIZE_PARAM_FAIL.getCode(), msg);
        }
        return nousService.updateNous(nousEntity, file);
    }

    @ApiOperation(value = "消防常识id查询接口", notes = "消防常识id查询接口")
    @ApiImplicitParam(name = "id", value = "根据id查询", dataType = "long")
    @GetMapping("/getById")
    public Result getById(Long id) {
        NousEntity nousEntity = nousService.getByIds(id);
        return Result.SUCCESS(nousEntity);
    }

    @ApiOperation(value = "消防常识分页查询接口", notes = "消防常识分页查询接口")
    @ApiImplicitParam(name = "NousEntity", value = "查询参数examineStatus，pageNum.pageSize,create_time(创建时间排序条件) isAsc(asc,desc)", dataType = "int")
    @GetMapping("/getNousInfo")
    public Result getNousInfo(PageDomain pageDomain, NousEntity nousEntity) {
        Page<T> page = startPage();
        IPage<NousEntity> nousEntityIPageList = nousService.getNousInfo(page, nousEntity);
        return Result.SUCCESS(new PageResult(nousEntityIPageList));
    }

    @ApiOperation(value = "消防常识审核接口", notes = "消防常识审核接口")
    @ApiImplicitParam(name = "接口参数", value = "id，examineStatus，", dataType = "StatuteEntity")

    @PutMapping("/examine")
    public Result examine(@RequestBody NousEntity entity) {
        //参数校验
        String msg = new Validator<NousEntity>().validate(entity, new String[]{"id", "examineStatus"});
        if (msg != null) {
            return Result.FAIL(ResultCode.PUBLICIZE_PARAM_FAIL.getCode(), msg);
        }
        return nousService.examine(entity);
    }



}

