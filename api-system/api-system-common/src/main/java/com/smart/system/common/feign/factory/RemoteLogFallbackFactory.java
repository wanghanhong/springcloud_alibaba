package com.smart.system.common.feign.factory;

import com.smart.system.common.entity.SysLoginInfo;
import com.smart.system.common.entity.SysOperLog;
import com.smart.system.common.feign.RemoteLogService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService> {
    @Override
    public RemoteLogService create(Throwable throwable) {
        log.error(throwable.getMessage());
        return new RemoteLogService() {
            @Override
            public void insertOperlog(SysOperLog operLog) {
            }

            @Override
            public void insertLoginlog(SysLoginInfo logininfor) {
            }
        };
    }
}
