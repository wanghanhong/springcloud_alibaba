package com.smart.common.log;

import com.smart.common.log.aspect.OperLogAspect;
import com.smart.common.log.listen.LogListener;
import com.smart.system.common.feign.RemoteLogService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @description: 日志自动配置
 * @author: SanDuo
 * @date: 2020/5/23 17:22
 * @version: 1.0
 */
@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
public class LogAutoConfiguration {
    private final RemoteLogService logService;

    @Bean
    public LogListener sysOperLogListener() {
        return new LogListener(logService);
    }

    @Bean
    public OperLogAspect operLogAspect() {
        return new OperLogAspect();
    }
}