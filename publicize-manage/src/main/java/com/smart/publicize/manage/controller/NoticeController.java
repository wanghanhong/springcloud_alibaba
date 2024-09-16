package com.smart.publicize.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.common.validator.Validator;
import com.smart.publicize.manage.entity.NoticeEntity;
import com.smart.publicize.manage.entity.NoticeVo;
import com.smart.publicize.manage.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import java.util.List;

/**
 * USER: gll
 * DATE: 2020/5/22
 * TIME: 16:50
 * Describe:
 *
 * @author l
 */

@Api(value = "公告controller", tags = "公告接口")
@RequestMapping("/api/v2/notice")
@RestController
public class NoticeController extends BaseController {

    @Resource
    private NoticeService noticeService;

    @ApiOperation(value = "公告添加接口", notes = "公告添加接口")
    @ApiImplicitParam(name = "NoticeEntity", value = "需要传递的参数noticeName，noticeType,noticeContent,releaseType,releaseTime(立即发布时为空)，createId,createName,popStartTime.popEndTime,examineStatus(预留传空值),roleId (1,2,3)", dataType = "NoticeEntity")
    @PostMapping("/add")
    public Result add(@RequestBody NoticeEntity noticeEntity) {
        //参数校验
        String msg = new Validator<NoticeEntity>().validate(noticeEntity, new String[]{"noticeName", "noticeType", "noticeContent", "releaseType"});
        if (msg != null) {
            return Result.FAIL(ResultCode.PUBLICIZE_PARAM_FAIL.getCode(), msg);
        }
        return noticeService.add(noticeEntity);
    }


    @ApiOperation(value = "公告删除接口", notes = "公告删除接口")
    @ApiImplicitParam(name = "接口参数", value = "id，updateId，updateName", dataType = "NoticeEntity")

    @DeleteMapping("/del")
    public Result del(@RequestBody NoticeEntity entity) {
        return noticeService.del(entity);
    }

    @ApiOperation(value = "修改公告接口", notes = "修改公告接口")
    @ApiImplicitParam(name = "NoticeEntity", value = "需要传递的参数noticeName，noticeType,noticeContent,releaseType,releaseTime(立即发布时为空)，updateId,updateName,popStartTime.popEndTime,examineStatus(预留传空值),roleId (1,2,3),updateId,updateName", dataType = "NoticeEntity")
    @PutMapping("/update")
    public Result updateNotcie(@RequestBody NoticeEntity entity) {
        return noticeService.update(entity);
    }

    @ApiOperation(value = "公告分页查询接口", notes = "公告分页查询接口create_time(查询排序字段名)isAsc(排序 asc正,desc倒),(查询参数)pageNum,pageSize,releaseTime")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "create_time(查询排序字段名),pageNum,pageSize", value = "创建时间", dataType = "NoticeEntity")
    })

    @GetMapping("/selectByTime")
    public Result selectByTime(PageDomain pageDomain, NoticeEntity entity) {
        Page<T> page = startPage();
        IPage<NoticeVo> noticeList = noticeService.noticeList(page, entity);
        return Result.SUCCESS(new PageResult(noticeList));
    }

    @ApiOperation(value = "公告id查询接口", notes = "公告id查询接口")
    @ApiImplicitParam(name = "id", value = "根据id查询", dataType = "long")
    @GetMapping("/getById")
    public Result getById(Long id) {

        return noticeService.getByIds(id);
    }

    @ApiOperation(value = "公告审核接口", notes = "公告审核接口")
    @ApiImplicitParam(name = "接口参数", value = "id，examineStatus，", dataType = "StatuteEntity")

    @PutMapping("/examine")
    public Result examine(@RequestBody NoticeEntity entity) {
        //参数校验
        String msg = new Validator<NoticeEntity>().validate(entity, new String[]{"id", "examineStatus"});
        if (msg != null) {
            return Result.FAIL(ResultCode.PUBLICIZE_PARAM_FAIL.getCode(), msg);
        }
        return noticeService.examine(entity);
    }

    @ApiOperation(value = "公告查询接口,获取最近的前十")
    @GetMapping("/queryNoticeListLimit")
    public List<NoticeEntity> queryNoticeListLimit() {
        return noticeService.queryNoticeListLimit();
    }


}
