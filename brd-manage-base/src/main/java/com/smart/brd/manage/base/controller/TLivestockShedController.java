package com.smart.brd.manage.base.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.brd.manage.base.entity.TLivestockShed;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.service.ITLivestockShedService;
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
@Api(tags = "舍")
@RequestMapping("/api/v1/brd/tlivestockshed")
public class TLivestockShedController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITLivestockShedService tLivestockShedService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tLivestockShedList")
    public Result tLivestockShedList(HttpServletRequest request,PageDomain page,TLivestockShed entity) {
        try {
            IPage<TLivestockShed> iPage = tLivestockShedService.tLivestockShedList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tLivestockShedAdd")
    public Result tLivestockShedAdd(@RequestBody TLivestockShed entity) {
        try {
            tLivestockShedService.tLivestockShedAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tLivestockShedDel")
    public Result tLivestockShedDel(Long shedId) {
        try {
            tLivestockShedService.tLivestockShedDel(shedId);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tLivestockShedUpdate")
    public Result tLivestockShedUpdate(@RequestBody TLivestockShed entity) {
        try {
            tLivestockShedService.tLivestockShedUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tLivestockShedDetail")
    public Result tLivestockShedDetail(HttpServletRequest request,TLivestockShed entity) {
        try {
            TLivestockShed vo = tLivestockShedService.tLivestockShedDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    /**------基本方法结束-----------------------------------------*/

    @ApiOperation(value = "查询公司下：所有舍接口-不分页")
    @GetMapping("/queryShedList")
    public Result queryShedList(HttpServletRequest request,TLivestockShed entity) {
        try {
            List<TLivestockShed> list = tLivestockShedService.queryShedList(entity);
            return Result.SUCCESS(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }


}
