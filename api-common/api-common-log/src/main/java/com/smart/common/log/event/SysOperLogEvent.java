package com.smart.common.log.event;

import com.smart.system.common.entity.SysOperLog;
import org.springframework.context.ApplicationEvent;

/**
 * @description: 系统日志事件
 * @author: SanDuo
 * @date: 2020/5/23 16:18
 * @version: 1.0
 */
public class SysOperLogEvent extends ApplicationEvent {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 8905017895058642111L;

    public SysOperLogEvent(SysOperLog source) {
        super(source);
    }
}