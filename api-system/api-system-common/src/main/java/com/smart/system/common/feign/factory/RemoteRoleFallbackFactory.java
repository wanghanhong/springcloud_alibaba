package com.smart.system.common.feign.factory;


import com.smart.system.common.entity.SysRole;
import com.smart.system.common.feign.RemoteRoleService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 回调
 * @author: SanDuo
 * @date: 2020/5/23 17:14
 * @version: 1.0
 */
@Slf4j
@Component
public class RemoteRoleFallbackFactory implements FallbackFactory<RemoteRoleService> {
    /* (non-Javadoc)
     * @see feign.hystrix.FallbackFactory#create(java.lang.Throwable)
     */

    @Override
    public RemoteRoleService create(Throwable throwable) {
        log.error(throwable.getMessage());
        return new RemoteRoleService() {
            @Override
            public SysRole selectSysRoleByRoleId(long roleId) {
                return null;
            }
        };
    }
}
