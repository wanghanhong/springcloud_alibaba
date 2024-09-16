package com.smart.brd.sys.system.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smart.brd.sys.common.annotation.Log;
import com.smart.brd.sys.common.controller.BaseController;
import com.smart.brd.sys.common.domain.FebsResponse;
import com.smart.brd.sys.common.domain.QueryRequest;
import com.smart.brd.sys.common.exception.FebsException;
import com.smart.brd.sys.system.domain.po.Role;
import com.smart.brd.sys.system.domain.po.RoleMenu;
import com.smart.brd.sys.system.service.RoleMenuServie;
import com.smart.brd.sys.system.service.RoleService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Pano
 */
@Slf4j
@Validated
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {
    private Integer error_code = 10001;
    @Resource
    private RoleService roleService;
    @Resource
    private RoleMenuServie roleMenuServie;

    private String message;

    @GetMapping
//    @RequiresPermissions("role:view")
    public FebsResponse roleList(HttpServletRequest request, QueryRequest queryRequest, Role role) {
        Map<String, Object> dataTable = getDataTable(roleService.findRoles(request,role, queryRequest));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    @GetMapping("check/{roleName}")
    public FebsResponse checkRoleName(@NotBlank(message = "{required}") @PathVariable String roleName) {
        Role result = this.roleService.findByName(roleName);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(ObjectUtil.isNull(result));
    }

    @GetMapping("menu/{roleId}")
    public FebsResponse getRoleMenus(@NotBlank(message = "{required}") @PathVariable String roleId) {
        List<RoleMenu> list = this.roleMenuServie.getRoleMenusByRoleId(roleId);
        List<String> result = list.stream().map(roleMenu -> String.valueOf(roleMenu.getMenuId())).collect(Collectors.toList());
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(result);
    }

    @Log("新增角色")
    @PostMapping
//    @RequiresPermissions("role:add")
    public FebsResponse addRole(HttpServletRequest request,@RequestBody Role role) throws FebsException {
        try {
            this.roleService.createRole(request,role);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "新增角色失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @Log("删除角色")
    @DeleteMapping("/{roleIds}")
//    @RequiresPermissions("role:delete")
    public FebsResponse deleteRoles(@NotBlank(message = "{required}") @PathVariable String roleIds) throws FebsException {
        try {
            String[] ids = roleIds.split(StringPool.COMMA);
            for(int i=0;i<ids.length;i++){
                Long id = Long.parseLong(ids[i]);
                if(id <= 1){
                    return new FebsResponse().code(HttpStatus.HTTP_OK).message("包含系统角色，请联系管理员删除");
                }
            }
            this.roleService.deleteRoles(ids);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "删除角色失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @Log("修改角色名称")
    @PostMapping("/updateRoleName")
    public FebsResponse updateRoleName(HttpServletRequest request,@RequestBody Role role) throws FebsException {
        try {
            this.roleService.updateRoleName(request,role);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改角色失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @Log("修改角色")
    @PutMapping
//    @RequiresPermissions("role:update")
    public FebsResponse updateRole(HttpServletRequest request,@RequestBody Role role) throws FebsException {
        try {
            this.roleService.updateRole(request,role);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改角色失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }

    @PostMapping("excel")
//    @RequiresPermissions("role:export")
    public FebsResponse export(HttpServletRequest request,QueryRequest queryRequest, Role role, HttpServletResponse response) throws FebsException {
        try {
            List<Role> roles = this.roleService.findRoles(request,role, queryRequest).getRecords();
            ExcelKit.$Export(Role.class, response).downXlsx(roles, false);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            return new FebsResponse().code(error_code).message(message);
        }
    }
}
