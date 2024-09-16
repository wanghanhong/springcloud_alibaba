package com.smart.card.manage.controller;

import com.smart.card.common.dict.entity.DictDto;
import com.smart.card.common.dict.entity.TBaseDict;
import com.smart.card.manage.entity.vo.TCardVo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.card.manage.entity.TCard;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.card.manage.service.ITCardService;
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
@RequestMapping("/api/card/v1/tcard")
public class TCardController {

   /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITCardService tCardService;

    @ApiOperation(value = "查询接口")
    @GetMapping("/tCardList")
    public Result tCardList(HttpServletRequest request,PageDomain page,TCard entity) {
        try {
            IPage<TCardVo> iPage = tCardService.tCardList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "添加接口")
    @PostMapping("/tCardAdd")
    public Result tCardAdd(@RequestBody TCard entity) {
        try {
            tCardService.tCardAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "删除接口")
    @GetMapping("/tCardDel")
    public Result tCardDel(Long id) {
        try {
            tCardService.tCardDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "修改接口")
    @PostMapping("/tCardUpdate")
    public Result tCardUpdate(@RequestBody TCard entity) {
        try {
            tCardService.tCardUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/tCardDetail")
    public Result tCardDetail(HttpServletRequest request,TCard entity) {
        try {
            TCard vo = tCardService.tCardDetail(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "查询余额")
    @GetMapping("/tCardRemain")
    public Result tCardRemain(HttpServletRequest request,TCard entity) {
        try {
            TCard vo = tCardService.tCardRemain(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "获取状态列表")
    @GetMapping("/tCardStatus")
    public Result tCardStatus(HttpServletRequest request) {
        try {
            List<DictDto> vo = tCardService.tCardStatus();
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "获取标签列表")
    @GetMapping("/tCardTags")
    public Result tTags(HttpServletRequest request) {
        try {
            List<DictDto> vo = tCardService.tTags();
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "获取流量池状态")
    @GetMapping("/tPoolMember")
    public Result tPoolMember(HttpServletRequest request) {
        try {
            List<DictDto> vo = tCardService.tPoolMember();
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
    /**------基本方法结束-----------------------------------------*/


}
