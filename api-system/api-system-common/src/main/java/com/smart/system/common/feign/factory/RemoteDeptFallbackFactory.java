package com.smart.system.common.feign.factory;


import com.smart.system.common.entity.SysDept;
import com.smart.system.common.feign.RemoteDeptService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 熔断重试
 * @author: SanDuo
 * @date: 2020/5/23 17:09
 * @version: 1.0
 */
@Slf4j
@Component
public class RemoteDeptFallbackFactory implements FallbackFactory<RemoteDeptService> {
    /* (non-Javadoc)
     * @see feign.hystrix.FallbackFactory#create(java.lang.Throwable)
     */

    @Override
    public RemoteDeptService create(Throwable throwable) {
        log.error(throwable.getMessage());
        return new RemoteDeptService() {

            @Override
            public SysDept selectSysDeptByDeptId(long deptId) {
                return null;
            }
        };
    }
}
