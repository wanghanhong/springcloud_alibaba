package wlw.smart.fire.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wlw.smart.fire.system.domain.po.RoleMenu;

import java.util.List;

public interface RoleMenuServie extends IService<RoleMenu> {

    void deleteRoleMenusByRoleId(String[] roleIds);

    void deleteRoleMenusByMenuId(String[] menuIds);

    List<RoleMenu> getRoleMenusByRoleId(String roleId);
}
