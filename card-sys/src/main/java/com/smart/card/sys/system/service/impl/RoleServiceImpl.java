package com.smart.card.sys.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.card.sys.common.domain.FebsConst;
import com.smart.card.sys.common.domain.QueryRequest;
import com.smart.card.sys.common.utils.SortUtil;
import com.smart.card.sys.system.dao.RoleMapper;
import com.smart.card.sys.system.dao.RoleMenuMapper;
import com.smart.card.sys.system.domain.po.Role;
import com.smart.card.sys.system.domain.po.RoleMenu;
import com.smart.card.sys.system.manager.UserManager;
import com.smart.card.sys.system.usertoken.entity.UserBean;
import com.smart.card.sys.system.usertoken.service.UserTokenService;
import com.smart.card.sys.system.service.RoleMenuServie;
import com.smart.card.sys.system.service.RoleService;
import com.smart.card.sys.system.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Service("roleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleMenuServie roleMenuService;
    @Resource
    private UserManager userManager;
    @Resource
    private UserTokenService userTokenService;

    @Override
    public IPage<Role> findRoles(HttpServletRequest request, Role role, QueryRequest queryRequest) {
        try {
            UserBean userBean = userTokenService.getUserByToken(request);

            LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();

            if (StringUtils.isNotBlank(role.getRoleName())) {
                queryWrapper.eq(Role::getRoleName, role.getRoleName());
            }
            if (StringUtils.isNotBlank(role.getCreateTimeFrom()) && StringUtils.isNotBlank(role.getCreateTimeTo())) {
                queryWrapper
                        .ge(Role::getCreateTime, role.getCreateTimeFrom())
                        .le(Role::getCreateTime, role.getCreateTimeTo());
            }
            if (userBean.getDeptId() != null) {
                queryWrapper.eq(Role::getCreateDept,userBean.getDeptId());
            }else{
                queryWrapper.eq(Role::getCreateDept,0);
            }
            queryWrapper.ge(Role::getRoleId, 0);
            Page<Role> page = new Page<>();
            SortUtil.handlePageSort(queryRequest, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取角色信息失败", e);
            return null;
        }
    }

    @Override
    public List<Role> findUserRole(String userName) {
        if (ObjectUtil.equal(userName, FebsConst.ADMIN_ACCOUNT)) {
            return this.list();
        }
        return baseMapper.findUserRole(userName);
    }

    @Override
    public Role findByName(String roleName) {
        return baseMapper.selectOne(new LambdaQueryWrapper<Role>().eq(Role::getRoleName, roleName));
    }

    @Override
    public void createRole(HttpServletRequest request,Role role) {
        UserBean userBean = userTokenService.getUserByToken(request);
        role.setCreateTime(new Date());
        if(userBean.getDeptId() != null ){
            role.setCreateDept(userBean.getDeptId());
        }else{
            role.setCreateDept(0L);
        }
        this.save(role);
        if (StringUtils.isNotBlank(role.getMenuId())) {
            String[] menuIds = role.getMenuId().split(StringPool.COMMA);
            setRoleMenus(role, menuIds);
        } else {
            setRoleMenus(role, new String[]{});
        }
    }

    @Override
    public void deleteRoles(String[] roleIds) throws Exception {
        // 查找这些角色关联了那些用户
        List<String> userIds = this.userRoleService.findUserIdsByRoleId(roleIds);
        List<String> list = Arrays.asList(roleIds);

        baseMapper.deleteBatchIds(list);

        this.roleMenuService.deleteRoleMenusByRoleId(roleIds);
        this.userRoleService.deleteUserRolesByRoleId(roleIds);

        // 重新将这些用户的角色和权限缓存到 Redis中
        this.userManager.loadUserPermissionRoleRedisCache(userIds);
    }
    @Override
    public void updateRole(HttpServletRequest request,Role role) throws Exception {
        UserBean userBean = userTokenService.getUserByToken(request);
        role.setCreateDept(userBean.getDeptId());

        // 查找这些角色关联了那些用户
        String[] roleId = {String.valueOf(role.getRoleId())};
        List<String> userIds = this.userRoleService.findUserIdsByRoleId(roleId);

        role.setModifyTime(new Date());
        baseMapper.updateById(role);
        try {
            roleMenuMapper.delete(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, role.getRoleId()));
            String[] menuIds = role.getMenuId().split(StringPool.COMMA);
            setRoleMenus(role, menuIds);
            // 重新将这些用户的角色和权限缓存到 Redis中
            this.userManager.loadUserPermissionRoleRedisCache(userIds);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoleName(HttpServletRequest request,Role role) throws Exception {
        try {
            UserBean userBean = userTokenService.getUserByToken(request);
            role.setCreateDept(userBean.getDeptId());
            role.setModifyTime(new Date());
            baseMapper.updateById(role);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setRoleMenus(Role role, String[] menuIds) {
        if(menuIds != null  ){
            Arrays.stream(menuIds).forEach(menuId -> {
               if(StringUtils.isNotBlank(menuId)){
                   RoleMenu rm = new RoleMenu();
                   rm.setMenuId(Long.valueOf(menuId));
                   rm.setRoleId(role.getRoleId());
                   this.roleMenuMapper.insert(rm);
               }

            });
        }
    }
}
