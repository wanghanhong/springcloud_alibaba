package com.smart.system.manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import com.smart.common.core.page.PageResult;
import com.smart.common.valids.Update;
import com.smart.system.manage.entity.SysRole;
import com.smart.system.manage.service.ISysRoleService;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.management.relation.Role;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author sanduo
 * @since 2020-07-10
 */
@RestController
@RequestMapping("/v1/sys/role")
public class SysRoleController extends BaseController {

    @Resource
    private ISysRoleService roleService;

    /**
     * @param roleId 角色ID
     * @return Result
     */
    @ApiOperation("根据角色ID查询用户")
    @GetMapping("/get/{roleId}")
    public Result queryByRoleId(@PathVariable("roleId") @NotNull(message = "角色Id不能为空") String roleId) {
        SysRole role = roleService.getById(roleId);
        return Result.SUCCESS(role);
    }

    /**
     * 查询列表分页
     * 角色名称
     * 角色key
     * 角色状态
     *
     * @param pageSize
     * @param pageNum
     * @return
     */
    @ApiOperation("查询角色列表")
    @GetMapping("/list")
    public Result list(SysRole role, @RequestParam(name = "pageSize", required = false) String pageSize, @RequestParam(name = "pageNum", required = false) String pageNum) {
        return Result.SUCCESS(roleService.list( startPage(),role));
    }

    /**
     * 创建角色
     *
     * @param role
     * @return
     */
    @ApiOperation("创建角色")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody SysRole role) {
        //手动设置ID
        role.setRoleId(IdWorker.getId());
        role.setCreateTime(LocalDateTime.now());
        role.setCreateBy(this.userId + this.loginName);

        boolean result = roleService.save(role);
        return Result.SUCCESS(result);
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @ApiOperation("修改角色")
    @PutMapping("/update")
    public Result update(@Validated(Update.class) @RequestBody SysRole role) {
        role.setUpdateBy(this.userId + this.loginName);
        role.setUpdateTime(LocalDateTime.now());
        if (roleService.updateById(role)) {
            return Result.SUCCESS();
        }
        return Result.FAIL("修改角色失败");

    }

    /**
     * 角色
     *
     * @param ids
     * @return
     */
    @ApiOperation("删除角色")
    @DeleteMapping("/delete")
    public Result delete(String[] ids) {
        if (roleService.removeByIds(Arrays.asList(ids))) {
            return Result.SUCCESS();
        }
        return Result.FAIL("删除角色失败");

    }

}
