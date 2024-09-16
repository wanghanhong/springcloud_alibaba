package com.smart.brd.sys.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.brd.sys.system.domain.po.RoleMenu;

import java.util.List;

public interface RoleMenuServie extends IService<RoleMenu> {

    void deleteRoleMenusByRoleId(String[] roleIds);

    void deleteRoleMenusByMenuId(String[] menuIds);

    List<RoleMenu> getRoleMenusByRoleId(String roleId);
}
