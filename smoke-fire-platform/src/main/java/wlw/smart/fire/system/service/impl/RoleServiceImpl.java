package wlw.smart.fire.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wlw.smart.fire.common.domain.FebsConst;
import wlw.smart.fire.common.domain.QueryRequest;
import wlw.smart.fire.common.utils.SortUtil;
import wlw.smart.fire.system.dao.RoleMapper;
import wlw.smart.fire.system.dao.RoleMenuMapper;
import wlw.smart.fire.system.domain.po.Role;
import wlw.smart.fire.system.domain.po.RoleMenu;
import wlw.smart.fire.system.manager.UserManager;
import wlw.smart.fire.system.service.RoleMenuServie;
import wlw.smart.fire.system.service.RoleService;
import wlw.smart.fire.system.service.UserRoleService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Pano
 */
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

    @Override
    public IPage<Role> findRoles(Role role, QueryRequest request) {
        try {
            LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();

            if (StringUtils.isNotBlank(role.getRoleName())) {
                queryWrapper.eq(Role::getRoleName, role.getRoleName());
            }
            if (StringUtils.isNotBlank(role.getCreateTimeFrom()) && StringUtils.isNotBlank(role.getCreateTimeTo())) {
                queryWrapper
                        .ge(Role::getCreateTime, role.getCreateTimeFrom())
                        .le(Role::getCreateTime, role.getCreateTimeTo());
            }
            Page<Role> page = new Page<>();
            SortUtil.handlePageSort(request, page, true);
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
    public void createRole(Role role) {
        role.setCreateTime(new Date());
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
    public void updateRole(Role role) throws Exception {
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
