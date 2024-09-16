package com.smart.card.sys.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.card.sys.system.domain.po.RoleMenu;

import java.util.List;

public interface RoleMenuServie extends IService<RoleMenu> {

    void deleteRoleMenusByRoleId(String[] roleIds);

    void deleteRoleMenusByMenuId(String[] menuIds);

    List<RoleMenu> getRoleMenusByRoleId(String roleId);
}
