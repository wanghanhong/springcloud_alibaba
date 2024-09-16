package com.smart.system.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageResult;
import com.smart.system.manage.entity.SysRole;
import com.smart.system.manage.mapper.SysRoleMapper;
import com.smart.system.manage.service.ISysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author sanduo
 * @since 2020-07-10
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Resource
    private SysRoleMapper roleMapper;

    /**
     * 查询列表分页
     * 角色名称
     * 角色key
     * 角色状态
     *
     * @param page
     * @param role
     * @return
     */
    @Override
    public PageResult list(Page page, SysRole role) {
        LambdaQueryWrapper<SysRole> condition = Wrappers.lambdaQuery(SysRole.class)
                .like(Objects.nonNull(role.getRoleName()), SysRole::getRoleName, role.getRoleName())
                .like(Objects.nonNull(role.getRoleKey()), SysRole::getRoleKey, role.getRoleKey())
                .eq(Objects.nonNull(role.getStatus()), SysRole::getStatus, role.getStatus());
        Page pageResult = this.roleMapper.selectPage(page, condition);

        return new PageResult(pageResult);
    }
}
