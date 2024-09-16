package com.smart.brd.manage.base.controller;

import com.smart.brd.manage.base.common.dict.DictCache;
import com.smart.brd.manage.base.common.dict.DictRedisService;
import com.smart.brd.manage.base.entity.vo.ComponentVo;
import com.smart.brd.manage.base.service.IComponentService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author dukzzz
 * @date 2021/3/29 10:15:上午
 * @desc
 */
@Api(tags = "养殖场大屏某栏舍相关数据")
@RestController
@RequestMapping("/api/v1/brd/component")
public class ComponentController {
    @Autowired
    private IComponentService iComponentService;
    @Resource
    private DictRedisService dictRedisService;


    @ApiOperation(value = "查询舍信息")
    @GetMapping("/tBedInfoDetail")
    public Result tBedInfoDetail(HttpServletRequest request, Long id) {
        try {
            ComponentVo vo=iComponentService.getInfo(id);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
    @ApiOperation(value = "刷新redis")
    @GetMapping("/Refresh")
    public Result refresh() {
        DictCache.toData();
        dictRedisService.DictToRedis();
        return Result.SUCCESS();

    }
}
