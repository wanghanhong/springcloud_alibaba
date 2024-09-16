package wlw.smart.fire.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import wlw.smart.fire.system.domain.po.UserRole;

import java.util.List;

public interface UserRoleService extends IService<UserRole> {

    void deleteUserRolesByRoleId(String[] roleIds);

    void deleteUserRolesByUserId(String[] userIds);

    List<String> findUserIdsByRoleId(String[] roleIds);
}
