package com.smart.publicize.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.common.validator.Validator;
import com.smart.publicize.manage.entity.StatuteEntity;
import com.smart.publicize.manage.entity.StatuteVo;
import com.smart.publicize.manage.service.StatuteService;
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
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * USER: gll
 * DATE: 2020/5/22
 * TIME: 16:53
 * Describe:宣传教育法规
 *
 * @author l
 */

@Api(value = "消防法规controller", tags = "消防法规接口")
@RestController
@RequestMapping("/api/v2/statue")
public class StatuteController extends BaseController {

    @Resource
    private StatuteService statuteService;

    @ApiOperation(value = "法规添加接口", notes = "法规添加接口")
    @ApiImplicitParam(name = "StatuteEntity", value = "需要传递的参数statuteName，examineStatus,statuteType,issuingAuthority,approvalAuthority，issueNo,issueDatetime,statuteContent.statueUrl,createId,createName", dataType = "StatuteEntity")
    @PostMapping("/add")
    public Result add(@RequestBody StatuteEntity statuteEntity) {
        //参数校验
        String msg = new Validator<StatuteEntity>().validate(statuteEntity, new String[]{"statuteName", "statuteType"});
        if (msg != null) {
            return Result.FAIL(ResultCode.PUBLICIZE_PARAM_FAIL.getCode(), msg);
        }
        return statuteService.add(statuteEntity);
    }

    @ApiOperation(value = "法规删除接口", notes = "法规删除接口")
    @ApiImplicitParam(name = "接口参数", value = "id，updateId，updateName", dataType = "StatuteEntity")
    @DeleteMapping("/del")
    public Result del(@RequestBody StatuteEntity entity) {
        //参数校验
        String msg = new Validator<StatuteEntity>().validate(entity, new String[]{"id"});
        if (msg != null) {
            return Result.FAIL(ResultCode.PUBLICIZE_PARAM_FAIL.getCode(), msg);
        }
        return statuteService.del(entity);
    }

    @ApiOperation(value = "法规修改接口", notes = "法规修改接口")
    @ApiImplicitParam(name = "StatuteEntity", value = "需要传递的参数statuteName，examineStatus,statuteType,issuingAuthority,approvalAuthority，issueNo,issueDatetime,statuteContent.statueUrl ,id，updateId，updateName", dataType = "StatuteEntity")
    @PutMapping("/update")
    public Result update(@RequestBody StatuteEntity entity) {
        //参数校验
        String msg = new Validator<StatuteEntity>().validate(entity, new String[]{"id"});
        if (msg != null) {
            return Result.FAIL(ResultCode.PUBLICIZE_PARAM_FAIL.getCode(), msg);
        }
        return statuteService.update(entity);
    }

    @ApiOperation(value = "法规分页查询接口", notes = "法规分页查询接口")
    @ApiImplicitParam(name = "StatuteEntity", value = "参数{issueDatetime}颁布时间", dataType = "StatuteEntity")
    @GetMapping("/getInfo")
    public Result getInfo(PageDomain pageDomain, StatuteEntity entity) {
        Page<T> page = startPage();
        IPage<StatuteEntity> statuteEntityIPage = statuteService.getInfo(page, entity);
        return Result.SUCCESS(new PageResult(statuteEntityIPage));
    }

    @ApiOperation(value = "法规id查询接口", notes = "法规id查询接口")
    @ApiImplicitParam(name = "id", value = "根据id查询", dataType = "long")
    @GetMapping("/getById")
    public Result getById(Long id) {
        StatuteEntity statuteEntity = statuteService.getByIds(id);
        return Result.SUCCESS(statuteEntity);
    }

    @ApiOperation(value = "法规审核接口", notes = "法规审核接口")
    @ApiImplicitParam(name = "接口参数", value = "id，examineStatus，", dataType = "StatuteEntity")

    @PutMapping("/examine")
    public Result examine(@RequestBody StatuteEntity entity) {
        //参数校验
        String msg = new Validator<StatuteEntity>().validate(entity, new String[]{"id", "examineStatus"});
        if (msg != null) {
            return Result.FAIL(ResultCode.PUBLICIZE_PARAM_FAIL.getCode(), msg);
        }
        return statuteService.examine(entity);
    }
}
