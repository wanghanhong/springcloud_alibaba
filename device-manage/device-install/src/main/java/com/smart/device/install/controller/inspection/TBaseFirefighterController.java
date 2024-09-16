package com.smart.device.install.controller.inspection;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.device.common.entity.UserBean;
import com.smart.device.common.install.entity.TBaseFirefighter;
import com.smart.device.common.service.UserService;
import com.smart.device.install.service.inspection.ITBaseFirefighterService;
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
@RestController
@RequestMapping("/api/v2/install/base/firefighter")
public class TBaseFirefighterController {
    /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBaseFirefighterService iTBaseFirefighterService;
    @Resource
    private UserService userService;

    @ApiOperation(value = "消防维保人员查询接口")
    @ApiImplicitParam
    @GetMapping("/baseFirefighterList")
    public Result baseFirefighterList(HttpServletRequest request, PageDomain page, TBaseFirefighter entity) {
        try {
            UserBean user = userService.getUserByToken(request);
            if(user != null  && user.getDeptId() != null){
                entity.setCompanyId(user.getDeptId());
            }
            IPage iPage = iTBaseFirefighterService.baseFirefighterList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_FIREFIGHTER);
        }
    }

    @ApiOperation(value = "消防维保人员添加接口")
    @ApiImplicitParam
    @PostMapping("/baseFirefighterAdd")
    public Result baseFirefighterAdd(@RequestBody TBaseFirefighter entity) {
        try {
            iTBaseFirefighterService.baseFirefighterAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_FIREFIGHTER);
        }
    }

    @ApiOperation(value = "消防维保人员删除接口")
    @ApiImplicitParam
    @GetMapping("/baseFirefighterDel")
    public Result baseFirefighterDel(Long id) {
        try {
            iTBaseFirefighterService.baseFirefighterDel(id);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_FIREFIGHTER);
        }
    }

    @ApiOperation(value = "修改消防维保人员接口")
    @ApiImplicitParam
    @PostMapping("/baseFirefighterUpdate")
    public Result baseFirefighterUpdate(@RequestBody TBaseFirefighter entity) {
        try {
            iTBaseFirefighterService.baseFirefighterUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_FIREFIGHTER);
        }
    }

    @ApiOperation(value = "根据ID查询消防维保人员详情")
    @ApiImplicitParam
    @GetMapping("/baseFirefighterDetail")
    public Result selectbaseFirefighterByID(Long id) {
        try {
            TBaseFirefighter vo = iTBaseFirefighterService.selectBaseFirefighterByID(id);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_FIREFIGHTER);
        }
    }
    /**------基本方法结束-----------------------------------------*/

    @ApiOperation(value = "查询消防维保人员详情")
    @ApiImplicitParam
    @GetMapping("/selectBaseFirefighter")
    public Result selectBaseFirefighter(String phone) {
        try {
            TBaseFirefighter vo = new TBaseFirefighter();
            vo.setPhone(phone);
            vo = iTBaseFirefighterService.selectBaseFirefighter(vo);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_FIREFIGHTER);
        }
    }


}
