package com.smart.common.log.listen;

import com.smart.common.log.event.SysLoginInfoEvent;
import com.smart.common.log.event.SysOperLogEvent;
import com.smart.system.common.entity.SysLoginInfo;
import com.smart.system.common.entity.SysOperLog;
import com.smart.system.common.feign.RemoteLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @description: 异步监听日志事件
 * @author: SanDuo
 * @date: 2020/5/23 16:23
 * @version: 1.0
 */
@Slf4j
@AllArgsConstructor
public class LogListener {
    private final RemoteLogService remoteLogService;

    /**
     * 监听操作
     *
     * @param event
     */
    @Async
    @Order
    @EventListener(SysOperLogEvent.class)
    public void listenOperLog(SysOperLogEvent event) {
        SysOperLog sysOperLog = (SysOperLog) event.getSource();
        remoteLogService.insertOperlog(sysOperLog);
        log.info("远程操作日志记录成功：{}", sysOperLog);
    }

    /**
     * 监听登录
     *
     * @param event
     */
    @Async
    @Order
    @EventListener(SysLoginInfoEvent.class)
    public void listenLoginInfo(SysLoginInfoEvent event) {
        SysLoginInfo sysLoginInfo = (SysLoginInfo) event.getSource();
        remoteLogService.insertLoginlog(sysLoginInfo);
        log.info("远程访问日志记录成功：{}", sysLoginInfo);
    }
}