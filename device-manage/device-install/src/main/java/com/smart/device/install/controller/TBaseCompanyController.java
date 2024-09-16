package com.smart.device.install.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.PageResult;
import com.smart.device.common.entity.UserBean;
import com.smart.device.common.install.entity.TBaseCompany;
import com.smart.device.common.service.UserService;
import com.smart.device.install.service.ITBaseCompanyService;
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
import java.util.List;

/**
 * @author f
 */
@Api(tags = "")
@RestController
@RequestMapping("/api/v2/install/base/company")
public class TBaseCompanyController {

    /**------基本方法开始-----------------------------------------*/
    @Resource
    private ITBaseCompanyService iTBaseCompanyService;
    @Resource
    private UserService userService;

    @ApiOperation(value = "单位查询接口")
    @ApiImplicitParam()
    @GetMapping("/baseCompanyList")
    public Result baseCompanyList(HttpServletRequest request,PageDomain page,TBaseCompany entity) {
        try {
            userService.setDataAuth(request,entity);
            IPage iPage = iTBaseCompanyService.baseCompanyList(page,entity);
            return Result.SUCCESS(new PageResult(iPage));
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_COMPANY);
        }
    }

    @ApiOperation(value = "单位添加接口")
    @ApiImplicitParam
    @PostMapping("/baseCompanyAdd")
    public Result baseCompanyAdd(@RequestBody TBaseCompany entity) {
        try {
            iTBaseCompanyService.baseCompanyAdd(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_COMPANY);
        }
    }

    @ApiOperation(value = "单位删除接口")
    @ApiImplicitParam
    @GetMapping("/baseCompanyDel")
    public Result baseCompanyDel(Long id) {
        try {
            int res = iTBaseCompanyService.baseCompanyDel(id);
            if(res > 0){
                return Result.SUCCESS();
            }else{
                return new Result(54101,"该单位已关联建筑物，请先解除关联。",false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_BUILDING);
        }
    }

    @ApiOperation(value = "修改单位接口")
    @ApiImplicitParam
    @PostMapping("/baseCompanyUpdate")
    public Result baseCompanyUpdate(@RequestBody TBaseCompany entity) {
        try {
            iTBaseCompanyService.baseCompanyUpdate(entity);
            return Result.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_COMPANY);
        }
    }

    @ApiOperation(value = "根据ID查询单位详情")
    @ApiImplicitParam
    @GetMapping("/baseCompanyDetail")
    public Result selecBaseCompanyByWhree(HttpServletRequest request,TBaseCompany entity,String username) {
        try {
            userService.setDataAuth(request,entity);
            TBaseCompany vo = iTBaseCompanyService.selecBaseCompanyByWhree(entity);
            return Result.SUCCESS(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_COMPANY);
        }
    }
    /**------基本方法结束-----------------------------------------*/


    @ApiOperation(value = "查询单位名称-下拉选择")
    @ApiImplicitParam
    @GetMapping("/selectCompanys")
    public Result selectCompanys(HttpServletRequest request,TBaseCompany entity) {
        try {
            userService.setDataAuth(request,entity);
            List<TBaseCompany> list = iTBaseCompanyService.selectCompanys(entity);
            return Result.SUCCESS(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_COMPANY);
        }
    }



}
