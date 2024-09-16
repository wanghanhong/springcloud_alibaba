package com.smart.device.install.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.device.common.entity.UserBean;
import com.smart.device.common.install.entity.TBaseFirehydrant;
import com.smart.device.common.service.UserService;
import com.smart.device.install.service.ITBaseFirehydrantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author f
 */
@Api(tags = "")
@RestController
@RequestMapping("/api/v2/install/base/firehydrant")
public class TBaseFirehydrantController {

    /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBaseFirehydrantService iTBaseFirehydrantService;
    @Resource
    private UserService userService;

    @ApiOperation(value = "消防栓查询接口")
    @ApiImplicitParam
    @GetMapping("/baseFirehydrantList")
    public Result baseFirehydrantList(HttpServletRequest request, PageDomain page,TBaseFirehydrant entity) {
        try {
            userService.setDataAuth(request,entity);
            IPage iPage = iTBaseFirehydrantService.baseFirehydrantList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_FIREHYDRANT);
        }
    }

    @ApiOperation(value = "消防栓添加接口")
    @ApiImplicitParam
    @PostMapping("/baseFirehydrantAdd")
    public Result baseFirehydrantAdd(@RequestBody TBaseFirehydrant entity) {
        try {
            iTBaseFirehydrantService.baseFirehydrantAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_FIREHYDRANT);
        }
    }

    @ApiOperation(value = "消防栓删除接口")
    @ApiImplicitParam
    @PostMapping("/baseFirehydrantDel")
    public Result baseFirehydrantDel(@RequestBody TBaseFirehydrant entity) {
        try {
            iTBaseFirehydrantService.baseFirehydrantDel(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_FIREHYDRANT);
        }
    }

    @ApiOperation(value = "修改消防栓接口")
    @ApiImplicitParam
    @PostMapping("/baseFirehydrantUpdate")
    public Result baseFirehydrantUpdate(@RequestBody TBaseFirehydrant entity) {
        try {
            iTBaseFirehydrantService.baseFirehydrantUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_FIREHYDRANT);
        }
    }

    @ApiOperation(value = "根据ID查询消防栓详情")
    @ApiImplicitParam
    @GetMapping("/baseFirehydrantDetail")
    public Result selectbaseFirehydrantByID(Long id) {
        try {
            TBaseFirehydrant vo = iTBaseFirehydrantService.selectBaseFirehydrantByID(id);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_FIREHYDRANT);
        }
    }
    /**------基本方法结束-----------------------------------------*/



}
