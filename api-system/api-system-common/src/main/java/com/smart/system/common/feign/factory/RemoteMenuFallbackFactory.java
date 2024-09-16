package com.smart.system.common.feign.factory;

import com.smart.system.common.feign.RemoteMenuService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @description: 回调
 * @author: SanDuo
 * @date: 2020/5/23 17:13
 * @version: 1.0
 */
@Slf4j
@Component
public class RemoteMenuFallbackFactory implements FallbackFactory<RemoteMenuService> {
    @Override
    public RemoteMenuService create(Throwable throwable) {
        log.error(throwable.getMessage());
        return new RemoteMenuService() {

            @Override
            public Set<String> selectPermsByUserId(Long userId) {
                return null;
            }
        };
    }
}
