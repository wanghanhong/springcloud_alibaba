package wlw.smart.fire.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wlw.smart.fire.system.domain.po.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findUserRole(String userName);

}