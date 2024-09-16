package com.smart.card.sys.system.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.smart.card.sys.common.annotation.Log;
import com.smart.card.sys.common.controller.BaseController;
import com.smart.card.sys.common.domain.QueryRequest;
import com.smart.card.sys.system.domain.po.Role;
import com.smart.card.sys.system.domain.po.RoleMenu;
import com.smart.card.sys.system.service.RoleMenuServie;
import com.smart.card.sys.system.service.RoleService;
import com.smart.common.core.domain.Result;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/role")
public class RoleController extends BaseController {
    private Integer error_code = 10001;
    @Resource
    private RoleService roleService;
    @Resource
    private RoleMenuServie roleMenuServie;

    private String message;

    @GetMapping("/list")
//    @RequiresPermissions("role:view")
    public Result roleList(HttpServletRequest request, QueryRequest queryRequest, Role role) {
        Map<String, Object> dataTable = getDataTable(roleService.findRoles(request,role, queryRequest));
        return Result.SUCCESS(dataTable);
    }

    @GetMapping("/check/{roleName}")
    public Result checkRoleName(@NotBlank(message = "{required}") @PathVariable String roleName) {
        Role result = this.roleService.findByName(roleName);
        return Result.SUCCESS(ObjectUtil.isNull(result));
    }

    @GetMapping("/menu/{roleId}")
    public Result getRoleMenus(@NotBlank(message = "{required}") @PathVariable String roleId) {
        List<RoleMenu> list = this.roleMenuServie.getRoleMenusByRoleId(roleId);
        List<String> result = list.stream().map(roleMenu -> String.valueOf(roleMenu.getMenuId())).collect(Collectors.toList());
        return Result.SUCCESS(result);
    }

    @Log("新增角色")
    @PostMapping("/add")
//    @RequiresPermissions("role:add")
    public Result addRole(HttpServletRequest request,@RequestBody Role role){
        try {
            this.roleService.createRole(request,role);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "新增角色失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @Log("删除角色")
    @GetMapping("/del/{roleIds}")
//    @RequiresPermissions("role:delete")
    public Result deleteRoles(@NotBlank(message = "{required}") @PathVariable String roleIds){
        try {
            String[] ids = roleIds.split(StringPool.COMMA);
            for(int i=0;i<ids.length;i++){
                Long id = Long.parseLong(ids[i]);
                if(id <= 1){
                    return Result.FAIL("包含系统角色，请联系管理员删除");
                }
            }
            this.roleService.deleteRoles(ids);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "删除角色失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @Log("修改角色名称")
    @PostMapping("/update")
    public Result updateRoleName(HttpServletRequest request,@RequestBody Role role){
        try {
            this.roleService.updateRoleName(request,role);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "修改角色失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @Log("修改角色")
    @PostMapping("/set")
//    @RequiresPermissions("role:update")
    public Result updateRole(HttpServletRequest request,@RequestBody Role role){
        try {
            this.roleService.updateRole(request,role);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "修改角色失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @PostMapping("/excel")
//    @RequiresPermissions("role:export")
    public Result export(HttpServletRequest request, QueryRequest queryRequest, Role role, HttpServletResponse response){
        try {
            List<Role> roles = this.roleService.findRoles(request,role, queryRequest).getRecords();
            ExcelKit.$Export(Role.class, response).downXlsx(roles, false);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }
}
