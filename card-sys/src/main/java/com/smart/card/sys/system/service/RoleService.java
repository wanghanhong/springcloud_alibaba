package com.smart.card.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.card.sys.common.domain.QueryRequest;
import com.smart.card.sys.system.domain.po.Role;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RoleService extends IService<Role> {

    IPage<Role> findRoles(HttpServletRequest request, Role role, QueryRequest queryRequest);

    List<Role> findUserRole(String userName);

    Role findByName(String roleName);

    void createRole(HttpServletRequest request, Role role);

    void deleteRoles(String[] roleIds) throws Exception;

    void updateRole(HttpServletRequest request, Role role) throws Exception;

    void updateRoleName(HttpServletRequest request, Role role) throws Exception;
}
