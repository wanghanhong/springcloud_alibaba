package com.smart.card.sys.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.card.sys.system.domain.po.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findUserRole(String userName);

}