package com.smart.system.common.feign;


import com.smart.common.constant.ServiceNameConstants;
import com.smart.system.common.entity.SysRole;
import com.smart.system.common.feign.factory.RemoteRoleFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description: 角色 Feign服务层
 * @author: SanDuo
 * @date: 2020/5/23 17:12
 * @version: 1.0
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteRoleFallbackFactory.class)
public interface RemoteRoleService {
    @GetMapping("role/get/{roleId}")
    public SysRole selectSysRoleByRoleId(@PathVariable("roleId") long roleId);
}
